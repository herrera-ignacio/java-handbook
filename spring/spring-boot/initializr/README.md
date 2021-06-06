# Spring Boot Initializr

## REST API example

1. Create a Maven Project with https://start.spring.io/
    * Avoid "Snapshot" versions (alpha/beta)
    * Add *Spring Web Starter* dependency.
2. Eclipse > File > Import > Maven > Existing Maven Projects
    * Eclipse's bug in `pom.xml` can be fixed by modifying `<properties>` section.
3. Eclipse > Right click `src/main/java > main package > App.java` > Run as Java Application.

```xml
<properties>
    <java.version>1.8</java.version>
    <maven-jar-plugin.version>3.1.1</maven-jar-plugin.version>
</properties>
```

## Maven POM file

By creating a Maven project, you can run your project using `mvnw` or `mvwn.cmd` file even if you don't have Maven installed:

```
./mvnw package
./mvnw spring-boot:run
mvn package
mvn spring-boot:run
```
