# CRUD API Demo Spring Boot + Hibernate

1. Create Project With Spring Initializr
2. Integrating Hibernate and JPA (DAO layer)

## Create Project With Spring Initializr

Add dependencies:

* Spring Web Starter
* Spring Data JPA
* Spring Boot DevTools
* MySQL Driver

## Integrating Hibernate and JPA

1. Update db configs in application.properties
2. Create Employee entity
3. Create DAO interface
4. Create DAO implementation
5. Create REST controller to use DAO

### Data Source Configuration

Spring Boot will configure your data source:

* Based on entries from Mavem *pom* file.
  * JDBC Driver: `mysql-connector-java`
  * Spring Data (ORM): `spring-boot-starter-data-jpa`
  
* DB connection info from `application.properties`