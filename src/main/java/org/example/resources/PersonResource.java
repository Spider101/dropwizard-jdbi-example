package org.example.resources;

import org.eclipse.jetty.http.HttpStatus;
import org.example.api.ImmutablePerson;
import org.example.api.Person;
import org.example.db.IPersonDAO;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.UUID;

@Path("dropwizard-jdbi-example/v1/person")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final IPersonDAO personDAO;

    public PersonResource(IPersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GET
    @Path("/{id}")
    public Response getPerson(@PathParam("id") UUID personId) {
        try {
            Person person = this.personDAO.getEntity(personId);
            return Response.ok().entity(person).build();
        } catch (EntityNotFoundException entityNotFoundException) {
            return Response.status(HttpStatus.NOT_FOUND_404, "Person not found!").build();
        }
    }

    @POST
    public Response createPerson(@Valid @NotNull Person incomingPerson, @Context UriInfo uriInfo) {
        this.personDAO.insertEntity(incomingPerson);
        URI location = uriInfo.getAbsolutePathBuilder().path("").build();
        return Response.created(location).entity(incomingPerson).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePerson(@PathParam("id") UUID personId, @Valid @NotNull Person incomingPerson) {
        Person existingPerson = this.personDAO.getEntity(personId);
        Person updatedPerson = ImmutablePerson.builder()
                .from(existingPerson)
                .firstName(incomingPerson.getFirstName())
                .lastName(incomingPerson.getLastName())
                .build();
        this.personDAO.updateEntity(existingPerson.getId(), updatedPerson);
        return Response.ok().entity(incomingPerson).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") UUID personId) {
        this.personDAO.deleteEntity(personId);
        return Response.noContent().build();
    }
}