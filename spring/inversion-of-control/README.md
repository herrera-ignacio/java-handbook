# Inversion of Control

* IoC Definition
* Spring Container
* Configuring Spring Container
* Beans
* Spring Development Process

## IoC Definition

IoC is the approach of __outsourcing the construction and management of objects__ (object factory).

See [example](../demos/ioc-demo).

Spring provides an _Object Factory_, that accepts a configuration metadata to decide which implementation it should privde.

## Spring Container

> Generally known as __ApplicationContext__.

* Create and manage objects (__Inversion of Control__).
* Inject object's dependencies (__Dependency Injection__).

### Configuring Spring Container

* XML Configuration file (legacy)
* Java Annotations
* Java Source Code

## Beans

In Spring, the objects that form the backbone of your application and that are managed by the Spring IoC _container_ are called __beans__.

> A bean is an object that is instantiated, assembled, and otherwise managed by a Spring IoC container.

Beans, and the _dependencies_ among them, are reflected in the _configuration metadata_ used by a container.

## Spring Development Process

1. Setup Spring Beans
2. Create Spring Containers
3. Retrieve Beans from Spring Containers
