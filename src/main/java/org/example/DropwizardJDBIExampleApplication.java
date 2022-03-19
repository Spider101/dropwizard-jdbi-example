package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.example.health.DropwizardJDBIExampleHealthCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DropwizardJDBIExampleApplication extends Application<DropwizardJDBIExampleConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DropwizardJDBIExampleApplication.class);

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void initialize(Bootstrap<DropwizardJDBIExampleConfiguration> bootstrap) {
        super.initialize(bootstrap);
    }

    public void run(DropwizardJDBIExampleConfiguration dropwizardJDBIExampleConfiguration, Environment environment) {
        LOGGER.info("Initializing server...");
        environment.healthChecks().register(this.getName(), new DropwizardJDBIExampleHealthCheck());
    }

    public static void main(final String[] args) throws Exception {
        new DropwizardJDBIExampleApplication().run(args);
    }
}