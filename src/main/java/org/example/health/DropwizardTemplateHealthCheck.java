package org.example.health;

import com.codahale.metrics.health.HealthCheck;

public class DropwizardTemplateHealthCheck extends HealthCheck {
    public DropwizardTemplateHealthCheck() {
        super();
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}