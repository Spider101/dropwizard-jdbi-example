package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.example.health.DropwizardTemplateHealthCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DropwizardTemplateApplication extends Application<DropwizardTemplateConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DropwizardTemplateApplication.class);

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void initialize(Bootstrap<DropwizardTemplateConfiguration> bootstrap) {
        super.initialize(bootstrap);
    }

    public void run(DropwizardTemplateConfiguration dropwizardTemplateConfiguration, Environment environment) {
        LOGGER.info("Initializing server...");
        environment.healthChecks().register(this.getName(), new DropwizardTemplateHealthCheck());
    }

    public static void main(final String[] args) throws Exception {
        new DropwizardTemplateApplication().run(args);
    }
}