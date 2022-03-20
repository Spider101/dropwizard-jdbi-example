package org.example.db;

import org.example.api.Person;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindPojo;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class PersonDAO implements IPersonDAO {

    private final IPersonJdbiDAO personJdbiDAO;
    public PersonDAO(Jdbi jdbi) {
        this.personJdbiDAO = jdbi.onDemand(IPersonJdbiDAO.class);
        this.personJdbiDAO.createTable();
    }

    public void insertEntity(Person person) {
        this.personJdbiDAO.insert(person);
    }

    public Person getEntity(UUID personId) {
        Person person = this.personJdbiDAO.findById(personId.toString());
        if (person == null) {
            throw new EntityNotFoundException();
        }
        return person;
    }

    public void updateEntity(UUID existingPersonId, Person updatedPerson) {
        this.personJdbiDAO.update(existingPersonId.toString(), updatedPerson);
    }

    public void deleteEntity(UUID personId) {
        this.personJdbiDAO.delete(personId.toString());
    }

    private interface IPersonJdbiDAO {
        @SqlUpdate("CREATE TABLE person (id VARCHAR PRIMARY KEY, firstName VARCHAR(100), lastName VARCHAR(100))")
        void createTable();

        @SqlUpdate("INSERT INTO PERSON (id, firstName, lastName) values (:id, :firstName, :lastName)")
        void insert(@BindPojo Person newPerson);

        @SqlQuery("SELECT id, firstName, lastName FROM PERSON where id = :id")
        Person findById(@Bind("id") String id);

        @SqlUpdate("UPDATE PERSON SET firstName = :firstName, lastName = :lastName WHERE id = :id")
        void update(@Bind("id") String id, @BindPojo Person updatedPerson);

        @SqlUpdate("DELETE FROM PERSON where id = :id")
        void delete(@Bind("id") String id);
    }
}