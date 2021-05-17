# Java Annotations

> See [demo example](../demos/annotations). 

* Definition
* Spring Configuration Usage
  * Development Process
* Default Component Names
* AutoWiring
  * Constructor Injection
  * Setter/Method Injection
  * Field Injection
* Qualifiers
* Bean Scopes and Lifecycle Methods

## Definition

* Special labels/markers added to Java classes.
* Provide metadata about the class.
* Processed at compile time or run-time for pspecial processing.

## Spring Configuration Usage

* XML configuration can be verbose.
* Annotations minimizes the XML configuration.

### Development Process

1. Enable component scanning in Spring config file.
2. Add the `@Component` annotation to your Java classes.
3. Retrieve bean from Spring container.

```xml
<beans>
    <!-- Spring will scan this package recursivelly -->
    <context:component-scan base-package="com.demo" />
</beans>
```

## Default Component Names

Besides specifying an specific bean id, you can also set default bean ids (with class names).

```
@Component
@Component("beanIdentifier")
```

## AutoWiring

Spring can use __AutoWiring__, it will look for a class that _matches_ the property (by _type_) and inject it automatically.

* Constructor Injection
* Setter/Method Injection
* Field Injections

### Constructor Injection

1. Define dependency interface and class.
2. Create a constructor in your class for injections
3. Configure dependency injection with `@Autowired` annotation.

> As of Spring Framework 4.3, an @Autowired annotation on such a constructor is no longer necessary if the target bean only defines one constructor to begin with. However, if several constructors are available, at least one must be annotated to teach the container which one to use.

### Setter and Method Injection

> Method injections works the same as with setter injection, spring will basically scan your component and invoke those methods with AutoWire for you.

```java
// Method injection
@Autowired
public void injectSupplementQuanitity(SupplementService ss) {
    this.supplementQuantity = ss.getQuantity();
}
```

### Field Injection

```java
@Autowired
private SupplementService supplementService;
```

## Qualifiers

`@Qualifer` lets you be specific of which bean you want to inject when there's multiple ones complying to the type Spring is trying to inject.

## Bean Scopes and Lifecycle Methods

