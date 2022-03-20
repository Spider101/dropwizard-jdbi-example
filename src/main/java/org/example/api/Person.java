package org.example.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.validation.Valid;
import java.util.UUID;

@Value.Immutable
@JsonSerialize
@JsonDeserialize(as = ImmutablePerson.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface Person {
    /**
     * club ID
     */
    @Valid
    @Value.Default
    default UUID getId() {
        return UUID.randomUUID();
    }

    /**
     * club name
     */
    @Valid
    String getFirstName();

    @Valid
    String getLastName();
}