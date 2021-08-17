# 52: Use overloading judiciously

* Overview
* Example

## Overview

* The **choice of which overloading to invoke** is made at **compile time**.

* Behavior of program that uses **overloading may be counterintuitive** because **selection among overloaded methods is static, while selection among overriden methods is dynamic**. The correct version of an *overridden* method is chosen at runtime, based on the runtime type of the object on which the method is invoked. Therefore you should **avoid confusing uses of overloading**.

> A method is overriden when a subclass contains a method declaration with the same signature as a method declaration in an ancestor. If an instance method is overridden in a subclass and this method is invoked on an instance of the subclass, the subclass's *overriding method* executes, regardless of the compile-time type of the subclass instance.

* A safe, conservative policy is **never to export two overloadings with the same number of parameters**. If a method uses varargs, a conservative policy is not to overload it at all. These restrictions are not terribly onerous because **you can always give methods different names instead of overloading them**.

* Do not overload methods to take different functional interfaces in the same argument position.

## Example

```java
// Broken! 
// This prints "Unknown Collection" three times
public class CollectionClassifier {
  public static String classify(Set<?> s) {
    return "Set";
  }

  public static String classify(List<?> lst) {
    return "List";
  }

  public static String classify(Collection<?> c) {
    return "Unknown Collection";
  }

  public static void main(String[] args) {
    Collection<?>[] collections = {
      new HashSet<String>(),
      new ArrayList<BigInteger>(),
      new HashMap<String, String>().values()
    };

    for (Collection<?> c : collections)
      System.out.println(classify(c));
  }
}
```

You might expect this program to print `Set`, followed by `List` and `Unknown Collection`, but it doesn't. It prints `Unknown Collection` three times. This happens because the `classify` method is *overloaded*, and **the choice of which overloading to invoke is made at compile time**. For all three iterations of the loop, the compile-time type of the paramter is the same: `Collection<?>`. The runtime type is different in each iteration, but this does not affect the choice of overloading.

Assuming a static method is required, the best way to fix this program is to replace all three overloadings of `classify` with a single method that does explicit `instanceof` tests:

```java
public static String classify(Collection<?> c) {
  return c instanceof Set ? "Set" :
         c instanceof List ? "List" : "Unknown Collection";
}
```
