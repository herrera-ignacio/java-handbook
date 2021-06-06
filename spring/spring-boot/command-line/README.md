# Running from Command Line

Spring Boot apps are self-contained so they can be easily runned from Command Line using the `.jar` file.

To create the `.jar` file, run `./mvnw package` or `mvn package` from project directory, then you can just run the file:

* Option 1: Use `java -jar`

* Option 2: Use Spring Boot Maven plugin: `mvnw spring-boot:run`
