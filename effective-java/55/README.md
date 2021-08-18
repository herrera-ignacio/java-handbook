# 55: Return optionals judiciously

* Overview
* Example: Returning Optional
* Example: Client Code
  * Provide a default value
  * Throw any appropriated exception
  * Assume nonempty

## Overview

> If you find yourself writing a method that can't always return a value and you believe it is important that users of the method consider this possibility every time they call it, then you should probably return an optional. You should, however, be aware that there are real performance consequences associated with returning optionals; for performance-critical methods, it may be better to return a `null` or throw an exception. Finally, you should rarely use an optional in any other capcity than as a return value.

* In Java 8, there is a third approach to **writing methods that may not be able to return value**, instead of throwing exceptions that should be reserved for exceptional conditions ([Item 69](../69)) or returning `null` ([Item 54](../54)). The `Optional<T>` class represents an **immutable container that can hold either a single non-null T reference or nothing at all**.

* An optional is essentially an immutable collection that can hold at most one element.

* An `Optional`-returning method is **more flexible and easier to use** than one that throws an exception, and it is **less error-prone** that one that returns `null`.

* **Optionals are similar in spirit to checked exceptions** ([Item 71](../71)), in that they *force* the user of an API to confront the fact that there may be no value returned. Throwing an unchecked exception or returning a `null` allows the user to ignore this eventuality, with potentially dire consequences. However, throwing a checked exception requires additional boilerplate code in the client.

* Not all return types benefit from the optional treatment. **Container types, including collections, maps, streams, arrays, and optionals should not be wrapped in optionals**. Rather than returning an empty `Optional<List<T>>`, youi shoud simply return an empty `List<T>` ([Item 54](../54)). Returning the empty container will eliminate the need for client code to process an optional.

* As a rule, **you should declare a method to return `Optional<T>` if it might not be able to return a result _and_ clients will have to perform special processing if no result is returned**.

* **You should never return an optional of a boxed primitive type**, with the possible exception of the "minor primitive types", `Boolean`, `Byte`, `Character`, `Short`, and `Float`. This is prohibitively expensive comparing to returning a primitive type because the optional has two levels of boxing instead of zero. Therefore, library designeds saw fit to provide analogues of `Optional<T>` for the primitives: `OptionalInt`, `OptionalLong`, and `OptionalDouble`.

* If it's expensive to get the default value, and you want to avoid that cost unless it's necessary, `Optional` provides a method that takes a `Supplier<T>` and invokes it only when necessary. This method is called `orElseGet`.

* There are several `Optional` methods for dealing with more specialized use cases: `filter`, `map`, `flatMap`, and `ifPresent`. In Java 9, two more of these methods were added: `or` and `ifPresentOrElse`. Look at the documentation for more advanced methods and see if they do the job.

* `Optional` provides the `isPresent()` method, which may be viewed as a safety value. It returns `true` if the optional contains a value, `false` if it's empty. Many uses of `isPresent` can profitably be replaced by one of the methods mentioned before, resulting in shorter, cleaner, and more idiomatic code.

* It is almost **never appropriate to use an optional as a key, value, or element in a collection or array**. For example, you should never use optionals as map values; if you do, you have two ways of expressing a key's logical absence from the map: either the key can be absent from the map, or it can be present and map to an empty optional. This represents needless complexity with great potential for confusion and errors.

## Example: Returning Optional

```java
// Returns maximum value in collection as an Optional<E>
public static <E extends Comparable<E>> Optional<E> max(Collection<E> c) {
  if (c.isEmpty()) return Optional.empty();

  E result = null;
  for (E e : c)
    if (result == null || e.compareTo(result) > 0)
      result = Objects.requireNonNull(e);
  
  return Optional.of(result);
}
```

## Example: Client code

If a method returns an optional, the client gets to choose what action to take if the method can't return a value.

### Provide a default value

```java
// Using an optional to provide a chosen default value
String lastWordInLexicon = max(words).orElse("No words...");
```

### Throw any appropriated exception

```java
// Using optional to throw a chosen exception
Toy myToy = max(toys).orElseThrow(TemperTantrumException::new);
```

### Assume nonempty

If you can *prove* that an optional is nonempty, you can get the value from the optional without specifying an action to take if the optional is empty, but if you're wrong, your code will throw `NoSuchElementException`:

```java
// Using optional when you know there's a return value
Element lastNobleGas = max(Elements.NOBLE_GASES).get();
```
