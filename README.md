# Dropwizard JDBI Example

This is a sample project to see how to work with dropwizard and jdbi with an H2 engine.

How to start the Dropwizard JDBI Example application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/dropwizard-jdbi-example-1.0-SNAPSHOT.jar server dev.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your application's health enter url `http://localhost:8081/healthcheck`