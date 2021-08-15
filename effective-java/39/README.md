# 39: Prefer annotations to naming patterns

> For example, prior to release 4, the JUnit framework required its users to designate test methods by beginning their names with the characters `test`.

## Naming patterns disadvantages

* Typographical errors result in silent failures.

* There is no way to ensure that they are used only on appropriate program elements.

* They provide no good way to associate parameter values with program elements.

## Annotations

> JUnit adopted annotations starting with release 4.

Annotations solve all naming patterns problems nicely. There is simply no reason to use naming patterns when you can use annotations instead.

That said, most programmers will have no need to define annotation types. But all programmers should use the predefined annotation types that Java provides ([Item 40](../40)).

```java
// Example: marker annotation type declaration
import java.lang.annotation.*;

/**
 * Indicates that the annotated method is a test method.
 * Use only on parameterless static methods.
 * /
 @Retention(RetentionPolicy.RUNTIME)
 @Target(ElementType.METHOD)
 public @interface Test {

 }
```
