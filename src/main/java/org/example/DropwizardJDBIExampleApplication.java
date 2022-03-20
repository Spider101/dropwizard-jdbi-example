package org.example;

import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.example.api.Person;
import org.example.db.IPersonDAO;
import org.example.db.PersonDAO;
import org.example.health.DropwizardJDBIExampleHealthCheck;
import org.example.resources.PersonResource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.immutables.JdbiImmutables;
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
        JdbiFactory factory = new JdbiFactory();
        Jdbi jdbi = factory.build(environment, dropwizardJDBIExampleConfiguration.getDatabase(), "h2");
        jdbi.getConfig(JdbiImmutables.class).registerImmutable(Person.class);
        IPersonDAO personDAO = new PersonDAO(jdbi);

        environment.jersey().register(new PersonResource(personDAO));
        environment.healthChecks().register(this.getName(), new DropwizardJDBIExampleHealthCheck());
    }

    public static void main(final String[] args) throws Exception {
        new DropwizardJDBIExampleApplication().run(args);
    }
}