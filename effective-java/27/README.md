# 27: Eliminate unchecked warnings

* If you eliminate all unchecked warnings, you are **assured that your code is typesafe**, meaning that you won't get a `ClassCastException` at runtime.

* If you can't eliminate a warning, but you can prove that the code that provoked the warning is typesafe, then (and only then) supress the warning with an `@SuppressWarnings("unchecked")` annotation. Always use this on the smallest scope posible.

* Every time you use a `@SupressWarnings("unchecked")` annotation, add a comment saying why it is safe to do so.

```java
// Will throw warning: [unchecked] unchecked conversion
Set<Lark> exaltation = new HashSet();

// Compiler will infer the correct actual type parameter
Set<Lark> exaltation = new HashSet<>();
```
