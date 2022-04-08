# Skeleton Project for Spring Boot Microservices

It's a minimal project reference for new Spring Boot microservices projects. Components can be subtracted/added
depending on the real project requirements.

## Requirements

- JDK 17
- Your Favorite IDE

## Building & Running Application

### IDE (Dev Profile)

First import project as maven project into IDE.

Then run the main class. It should be up and running with its internal tomcat server.

You can set the active profile via VM arguments;

```shell
-Dspring.profiles.active=dev
```

### Docker (Dev Profile)

Make sure your Docker Deamon up and running.

Create jar file by running this maven command;

```shell
mvn clean package
```

Create image by running this docker command in the directory where Dokerfile resides;

```shell
docker build --tag=carpediem-skeleton:v1 .
```

After image builds you can run this docker command to run the application in a container;

```shell
docker run -e "SPRING_PROFILES_ACTIVE=dev" -p 8080:8080 -t carpediem-skeleton:v1
```

### Docker Compose (Test Profile)

There is MariaDB connectivity within the test profile. Please follow preceding instructions in order to run the
application.

Create jar file by running this maven command;

```shell
mvn clean package
```

Build images by running the command;

```shell
docker compose build
```

Run applicaiton and mariadb containers by running the command;

```shell
docker compose up -d
```

### Access running application

#### Health Check

Visit the path below;

http://localhost:8080/skeletonapp/actuator/health

#### API Documentation

After running the application visit the url below;

http://localhost:8080/skeletonapp/swagger-ui/index.html

### Used Libraries

- Spring Boot

    - Web, Actuator, Data JPA, Validation, Test, Dev tools, Spring Doc
- H2 database
- Mariadb
- Lombok
- Mapstruct

### Roadmap

- [x] Package structure
- [x] Maven dependency management
- [x] Application properties with yaml file
- [x] Spring profile
- [x] Logback configuration
- [x] Dockerization
- [x] Readme file
- [x] Checkstyle configuration
- [x] Jacoco configuration
- [x] Configuring Api Doc (spring-doc)
- [x] Exception handling
- [x] H2 Database
- [x] MariaDB
- [ ] Spock testing
- [ ] Flyway database management
- [x] Additional Examples
    - [x] Creating a use case with sample classes and components
    - [ ] Flyway migration use case

### Possible Extensions

- KeyCloak
- Redis
- Message Queue
- ELK integration
- Config Client (Spring cloud config)
- OpenFeign (Rest calls)
- Sleuth / Zipkin (Distributed tracing)
- Eureka Client (Spring cloud discovery)
- Resilience4j (Spring cloud circuit breaker)