# Hibernate Annotations

> Docs: https://docs.jboss.org/hibernate/orm/5.3/userguide/html_single/Hibernate_User_Guide.html

* Domain Model
* Mapping Types
  * Basic Types
  * Embeddable Types
  * Entity Types
* Associations
  * Fetching
* Collections

## Domain Model

It is the model that ultimately describes the problem domain you are working in. Sometimes you will also hear the term _persistent classes_.

They make up the classes you wish to map in an ORM. Hibernate works best if these classes follow the _Plain Old Java Object (POJO_) / _JavaBean_ porgramming model.

Historically, applications using Hibernate would have used its proprietary XML mapping file format for this purpose. With the comming of JPA, most of this information is now defined in a way that is portable across ORM/JPA providers __using annotations__ (and/or standarized XML format).

> For Hibernate mapping features not supported by JPA we will prefer Hibernate extension annotations.

## Mapping Types

Hibernate understands both the Java and JDBC representations of application data. The ability to read/write this data from/to the database is the function of a Hibernate _type_. A type, in this usage, is an implementation of the `org.hibernate.type.Type` interface.

### Basic Types

Basic values types usually map a single database column, to a single, non-aggregated Java type. Hibernate provides a number of built-in basic types, which follow the natural mappings recommended by the JDBC specifications.

### Embeddable Types

> Historically, Hibernate called these _components_. JPA calls them embeddables.

They are a _composition of values_. For example, we might have a `Publisher` class that is a composition of `name` and `country`, or a `Location` class that is a composition of `country` and `city`.

### Entity Types

The entity types describes the mapping between the actual persistance domain model object and a database table row.

## Associations

Associations describe how two or more entities form a relationship based ona database joining semantics.

* `@ManyToOne`
* `@OneToMany`
* `@OneToOne`
* `@ManyToMany`
* `@NotFound`

### Fetching

JPA comes with the fetch option to define lazy loading and fetching modes, however Hibernate has more options.

You can __eagerly or lazily fetch associated entities__. The `fetch` parameter can be set to `FetchType.LAZY` or `FetchType.EAGER`.

`EAGER` will try to use an outer join select to retrieve the associated object, while `LAZY` will only trigger a select when the associated object is accessed for the first time.

`@OneToMany` and `@ManyToMany` associations are defaulted to `LAZY` and `@OneToOne` and `@ManyToOne` are defaulted to `EAGER`.

```
@[One|Many]toOne(fetch=FetchType.LAZY)
@[One|Many]ToOne(fetch=FetchType.EAGER)
@ManyTo[One|Many](fetch=FetchType.LAZY)
@ManyTo[One|Many](fetch=FetchType.EAGER)
```

> The recommended approach is to use `LAZY` on all static fetching definitions and override this choice dynamically when doing a particular query.

## Collections

Persistent collections can contain almost any other Hibernate type, including basic types, custom types, embeddables, and references to other entities.

> The owner of the collection is always an entity.

Hibernate uses its own collection implementations which are enriched with lazy-loading, caching or state change detection semantics. For this reason, persistent collections must be declared as an interface type (`java.util.*`) or even other object types (meaning that you will have to write an implementation of `org.hibernate.usertype.UserCollectionType`).

The persistent collections injected by Hibernate behave like `ArrayList`, `HashSet`, `TreeSet`, `HashMap`, or `TreeMap` depending on the interface type.
