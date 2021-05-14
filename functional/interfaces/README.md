# Functional Interfaces

* With Semantics
  * Comparator & Comparable
  * Runnable & Callable
* Without Semantics
  * `java.util.function` 

```java
// Boolean expression that uses object of type T
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}

// Inputs object of type T, outputs object of type R
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t)
}

@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);
}

// example
BiFunction<String, String, Boolean> = (a, b) => a.contains(b);
```
