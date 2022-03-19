# Dropwizard Template

This is a starter template to build dropwizard projects from.
Make sure to replace all instances of the `dropwizard-template` nomenclature with the name of the new application.

How to start the Dropwizard Template application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/dropwizard-template-1.0-SNAPSHOT.jar server dev.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your application's health enter url `http://localhost:8081/healthcheck`