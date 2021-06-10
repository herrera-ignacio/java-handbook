# Library Kafka Demo

> This is a demo based on the following Udemy's course: https://www.udemy.com/course/apache-kafka-for-developers-using-springboot

## Creating project

1. Create a new Java 11 project using [Spring Initializr](https://start.spring.io/).
2. Add dependencies: Spring Web, Spring for Apache Kafka, Lombok.
3. Enable `Compile` > `Annotations Processors` in IntelliJ.

## Configuration

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

## Project structure

* Create domain objects.
* Create a producer for domain objects that you want to trigger events with.
* Create a controller that uses producer for sending events.
