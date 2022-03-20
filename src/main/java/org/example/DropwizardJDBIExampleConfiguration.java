package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class DropwizardJDBIExampleConfiguration extends Configuration {
    @Valid
    @NotNull
    @JsonProperty
    private Long appId;

    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory database;

    public Long getAppId() {
        return appId;
    }

    public DataSourceFactory getDatabase() {
        return database;
    }
}