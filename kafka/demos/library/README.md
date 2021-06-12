# Library Kafka Demo

> This is a demo based on the following Udemy's course: https://www.udemy.com/course/apache-kafka-for-developers-using-springboot

## Running Projects

Any of the projects can be run by:

1. Building `.jar` file: `./gradlew build`
2. Running jar file: `java -jar -D server.port=8081 build/libs/<file>.jar`

## Kafka Producer

### Creating project

1. Create a new Java 11 project using [Spring Initializr](https://start.spring.io/).
2. Add dependencies: Spring Web, Spring for Apache Kafka, Lombok, `org.springframework.boot:spring-boot-starter-validation`.
3. Enable `Compile` > `Annotations Processors` in IntelliJ.

### Configuration

* *KafkaTemplate* configuration (`resources/application.yml`)

![](2021-06-10-09-07-37.png)

* Setup for creating topics programatically using *Kafka Admin* (`com.learnkafka.config.AutoCreateConfig` and `resources/application.yml`)

> Make sure to have ZooKeeper and Kafka Brokers running locally, also validate topic creation from CLI.

* Setup default topic in `resources/application.yml` if you are going to use `KafkaTemplate.sendDefault()`.

```yml
  kafka:
    template:
      default-topic: library-events
```

### Project structure

* Create domain objects.
* Create a producer for domain objects that you want to trigger events with.
* Create a controller that uses producer for sending events.

### Testing

We'll separate unit tests from integration tests in our `build.gradle` file:

```
sourceSets {
	test {
		java.srcDirs = ['src/test/java/unit', 'src/test/java/intg']
	}
}
```

## Kafka Consumer

### Creating Project

1. Create a new Java 11 project using [Spring Initializr](https://start.spring.io/).
2. Add dependencies: Spring Data JPA, H2 Database, Kafka, Spring for Apache Kafka, Lombok, Spring Boot Starter Web
3. Enable `Compile` > `Annotations Processors` in IntelliJ.

### Configuration 

1. Setup concurrent consumers

```java
// consumer/LibraryEventsConsumer
@Component
@Slf4j
public class LibraryEventsConsumer {

    @KafkaListener(topics = {"library-events"})
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord) {

        log.info("ConsumerRecord : {}", consumerRecord);
    }
}

// config/LibraryEventsConsumerConfig.java
@Configuration
@EnableKafka
public class LibraryEventsConsumerConfig {

    @Bean
    ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory<Object, Object> kafkaConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, kafkaConsumerFactory);
        factory.setConcurrency(3);
        return factory;
    }
}

```

2. Setup H2 Database

```yml
// resources/application.yml
  datasource:
    url: jdbc:h2:mem:test
    driver-class-name: org.h2.Driver
  jpa:
    database: h2
    database-platform: org.hibernate.dialect.H2Dialect
    generate-ddl: true
  h2:
    console:
      enabled: true
```

> You should be able to access H2 Console in `localhost:PORT/h2-console`