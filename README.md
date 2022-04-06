## Skeleton Project for Spring Boot Microservices

It's a minimal project reference for new Spring Boot microservices projects.
Components can be subtracted/added depending on the real project requirements.

### Requirements

- JDK 17 
- Your Favorite IDE

### Building & Running Application

#### Using IDE 

First import project as maven project into IDE.

Then run the main class. It should be up and running with its internal tomcat server.

You can set the active profile via VM arguments;
```shell
-Dspring.profiles.active=dev
```

#### Using Docker
Make sure your Docker Deamon up and running.

Create image by running this maven command;
```shell
mvn spring-boot:build-image
```

After image builds you can run this docker command to run the application in a container;
```shell
docker run -e "SPRING_PROFILES_ACTIVE=dev" -p 8080:8080 -t carpediem/skeleton:0.0.1-SNAPSHOT
```

### Used Libraries

- Spring Boot

  - Web, Actuator, Data JPA, Validation, Config, Test, Dev tools
- H2 database
- Lombok
- Logback
- Mapstruct

### Roadmap

- [x] Package structure
- [x] Maven dependency management
- [x] Application properties with yaml file
- [x] Spring profile
- [x] Logback configuration
- [x] Dockerization
- [x] Readme file
- [ ] Checkstyle configuration
- [ ] Jacoco configuration
- [ ] Configuring Api Doc (spring-doc || swagger ui)
- [ ] Spock testing
- [ ] Exception handling
- [ ] Flyway dependency
- [ ] Additional Examples
  - [ ] Creating a use case with sample classes and components
  - [ ] Flyway

### Possible Extensions

- MariaDB
- KeyCloak
- Redis 
- Message Queue 
- OpenFeign (Rest calls)
- Sleuth / Zipkin (Distributed tracing)
- Eureka Client (Spring cloud discovery )
- Resilience4j (Spring cloud circuit breaker)