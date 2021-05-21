# Hibernate Overview

![architecture](https://docs.jboss.org/hibernate/orm/5.3/userguide/html_single/images/architecture/data_access_layers.svg)

## Hibernate ORM

Hibernate ORM enables developers to more easily write applications whose data outlives the applications process. It applies to relational databases (via JDBC).

> Minimizes amount of JDBC code to develop (uses JDBC under the hood).

### Benefits Overview

* __JPA Provider__: Hibernate implements _Java Persistence API (JPA)_ specification, making it easily used in any environment supporting JPA.

* __Idiomatic persistence__: Develop persistence classes following natural OOP, Hibernete doesn't require interfaces or base classes for persistent classes, it enables any class or data structure to be persisted.

* __High Performance__: Supports lazy initialization, numerous fetching strategies and optimistic locking with automatic versioning and time stamping. Consistently offers superior performance over straight JDBC code, both in terms of developer productivity and runtime performance.

* __Scalability__: Designed to work in an application server cluster and deliver a highly scalable architeture.

* __Reliable__

* __Highly configurable and extensible__
