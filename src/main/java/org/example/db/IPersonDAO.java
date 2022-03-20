package org.example.db;

import org.example.api.Person;

import java.util.UUID;

public interface IPersonDAO {
    void insertEntity(Person person);
    Person getEntity(UUID personId);
    void updateEntity(UUID existingPersonId, Person updatedPerson);
    void deleteEntity(UUID personId);
}