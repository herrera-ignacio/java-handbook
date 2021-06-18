# 4: Enforce noninstantiability with a private constructor

**Utility classes that are just a grouping of static methods and static fields** can be used to group related metods on primitive values or arrays, in the manner of `java.lang.Math` or `java.utils.Arrays`, or to group static methods like factories, for objects that implement some interface, in the manner of `java.util.Collections`. Lastly, such classes can be used to group methods on a final class, since you can't put them in a subclass.

Such *utility classes* were **not designed to be nstantiated**: an instance would be nonsensical.

> In the absence of explicit constructors, however, the compiler provides a public, paramterless *default constructor*.

**Attempting to enforce noninstantiability by making a class abstract does not work**. The class can be subclassed and the subclass instantiated. Furhermore, it misleads the user into thinking the class was designed for inheritance ([Item 19](../19)).

**A class can be made noninstantiable by including a private constructor**:

```java
// Noninstantiable utility class
public class UtilityClass {
  private UtilityClass() {
    throw new AssertionError();
  }

  // ...
}
```

As a side effect, this idiom also prevents the class from being subclassed. All constructors must invoke a superclass constructor, explicitly or implicitly, and a subclass would have no accessible superclass constructor to invoke.
