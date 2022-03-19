package org.example.health;

import com.codahale.metrics.health.HealthCheck;

public class DropwizardJDBIExampleHealthCheck extends HealthCheck {
    public DropwizardJDBIExampleHealthCheck() {
        super();
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}