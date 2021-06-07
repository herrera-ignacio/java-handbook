# Dependency Injection

* Spring case
* Injection Types
  * Constructor Injection
  * Setter Injection
* Properties File

## Spring case

> Implementation of __Dependency Inversion Principle__.

The client delegates the responsibility of providing an object's dependencies to another object (a _factory_).

## Injection Types

There are many types of injection with Spring, the most common oines are __Constructor Injection__ and __Setter Injection__.

Injection can be configured using:

1. Full XML config for beans.
2. XML component scan & Java Annotations.
3. Java Configuration Class

### Constructor Injection

1. Define the dependency interface and class.
2. Create a constructor in your class for injections.
3. Configure the dependency injection in Spring config file.

### Setter Injection

1. Create setter method(s) in your class for injections.
2. Configure dependency injection in Spring config file.

You can inject dependencies and literal values too.

## Properties File

1. Create properties file.
2. Load properties file in spring config file.
3. Reference values from properties file.
