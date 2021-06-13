# Java Handbook

* Introduction
* Basics
* OOP
* Functional Programming
* JVM
* Java SE
* Maven
* Spring
* Sprint Boot
* Apache Kafka

## Introduction

> Useful resources: [resources](resources/introduction)

* [Java Overview](intro/overview)
* [Principles](intro/principles)
* [JRE: Java Runtime Environment](intro/jre)
* [JDK: Java Development Kit](intro/jdk)
* [Java Platform](intro/platform)
* [Criticism](intro/criticism)

## Basics

* [Data Types](basics/data_types)
* [Methods](code/basics/methods)
* [Operators](code/basics/operators)
  * [Operator Precedence](basics/operators/precedence)
  * [Operator Promotion](basics/operators/promotion)
* [Control Flow](code/basics/control-flow)
* [Java API](basics/java_api/packages)
* [Strings](code/basics/strings)
* [Packages](basics/packages)
* [Exceptions](basics/exceptions)
* [I/O](basics/io)
* [Serialization](basics/serialization)
* [Built-in Data Structures](basics/data_structures)
* [Generics](basics/generics)
* [Enums](basics/enums)
* [Date & Time](basics/time)
* [JBDC API](basics/jbdc)

## OOP

* [Object](oop/object)
* [Class](oop/class)
* [Inheritance](code/basics/oop)
* [Access modifiers](oop/access-modifiers)
* [Polymorphism](code/basics/polymorphism)
* [Method overriding](oop/method-overriding)
* [`super` keyword](oop/super)
* [Constructor chaining](oop/constructor-chaining)
* [Preventing Inheritance](oop/preventing-inheritance)
* [Private Constructor](oop/private-constructor)
* [Abstract Class](oop/abstract-class)
* [Interface](oop/interface)
* [Mixins](oop/mixins)
* [Default methods](oop/default-methods)
* [Nested Classes](oop/nested-classes)

## Functional Programming

* [Lambdas](functional/lambdas)
* [Functional Interfaces](functional/interfaces)
* [Method References](functional/method-references)
* [Streams](functional/streams)

## JVM: Java Virtual Machine

* [JVM Overview](jvm/overview)

## Java SE

* [Java Platform SE Overview](jse/overview)

## Maven

* [Maven overview](maven/overview)
* [Standard Directory Structure](maven/dir-structure)
* [Dependency Coordinates](maven/dep-coord)
* [Maven Archetypes](maven/archetypes)
* [Project Java Version](maven/java-ver)
* [Private Repositories](maven/private-repos)

## Spring

> [Demos](spring/demos)

* [Why Spring](spring/why)
* [Core Framework](spring/core)
* [Spring Platform](spring/platform)
* [Inversion of Control, Container & Beans](spring/inversion-of-control)
* [Dependency Injection](spring/dependency-injection)
* [Bean Lifecycle & Scopes](spring/bean-scopes)
* [Java Annotations](spring/annotations)
* [Spring configuration with Java Code](spring/config-no-xml)
* [Spring initialier](http://start.spring.io)

### Spring Boot

> [Demos](spring-boot/demos)

* [What is Spring Boot](spring/spring-boot/overview)
* [Spring & Spring Boot](spring/spring-boot/differences)
* [TDD](spring/spring-boot/tdd)
* [Spring Boot Initializr](spring/spring-boot/initializr)
* Annotations
  * [@SpringBootApplication](spring/spring-boot/annotations/SpringBootApplication)
* [Spring Boot Dev Tools](spring/spring-boot/dev-tools)
* [Spring Boot Actuator](spring/spring-boot/actuator)
* [Running from Command Line](spring/spring-boot/command-line)
* [Injecting Properties](spring/spring-boot/injecting-properties)

## Hibernate

* [Hibernate Overview](hibernate/overview)
* [Annotations](hibernate/annotations)

## Apache Zookeeper

* [Zookeper Overview](zookeper/overview)

## Apache Kafka

> [Demos](kafka/demos), [Spring for Apache Kafka docs](https://docs.spring.io/spring-kafka/docs/current/reference/html/)

* [Kafka Overview](kafka/overview)
* [How it works](kafka/how-it-works)
* [Kafka as Distributed System](kafka/distributed)
  * Leader/Follower Partitions
  * Consumer Offset & Group Coordinator
* Terminology
  * [Event](kafka/event)
  * [Producers & Consumers](kafka/producers-consumers)
  * [Topics & Partitions](kafka/topics)
  * [Consumer Groups](kafka/consumer-groups)
* [Kafka APIs](kafka/apis)
  * [KafkaTemplate.send()](kafka/apis/send)
* [Setting up and interacting with Kafka locally](kafka/local)
* [Retention Policy](kafka/log-policies)
* [Recovery/Retry models](kafka/recovery-retry)
* [Retain/Recover Failed Records](kafka/retain-failed)

## Apache Storm

* [Storm Overview](storm/overview)
* [Architecture Critical Components](storm/critical-components)
* [Data Model](storm/data-model)
  * Tuples
  * Streams
  * Topology
    * Spout
    * Bolt
* [Environment setup](storm/setup)
* [Bolt Parallelism](storm/bolt-parallelism)