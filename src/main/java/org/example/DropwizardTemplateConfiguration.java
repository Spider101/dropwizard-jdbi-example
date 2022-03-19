package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class DropwizardTemplateConfiguration extends Configuration {
    @Valid
    @NotNull
    @JsonProperty
    private Long appId;

    public Long getAppId() {
        return appId;
    }
}