# 42: Prefer lambdas to anonymous classes

* Overview
* Example

## Overview

Java 8 formalized the notion that interfaces with a single abstract method are special and deserve special treatment. These interfaces are now known as *functional interfaces*, and the language allows you to create instances of these interfaces using *lambda expressions*, or *lambdas* for short.

*** Omit the types of all lambda parameters unless their presence makes your program clearer**. Compiler can deduce types from context using *type inference*. If the compiler generates an error telling you it can't infer the type of a lambda parameter, *then* specify it.`

* **Lambdas lack names and documentation**; if a computation isn't self-explanatory, or exceeds a few lines, don't put it in a lambda.

* **You should rarely, if ever, serialize a lambda** (or an anonymous class instance). Lambdas share with anonymous classes the property that you can't reliably serialize and deserialize them across implementations. If you have a function object that you want to make serializable, such as a `Comparator`, use an instance of a private static nested class.

* **Don't use anonymous classes for function objects unless you have to create instances of types that aren't functional interfaces**.

## Example

Here's a code snippet using an anonymous class to create a sort's comparison function which imposes the sort order:

```java
// Anonymous class instance as a function object - obsolete!
Collections.sort(words, new Comparator<String>() {
  public int compare(String s1, String s2) {
    return Integer.compare(s1.length(), s2.length());
  }
});
```

Here's how the code snippet above looks with the anonymous class replaced by a lambda:

```java
Collections.sort(words, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
```

The comparator in the snippet can be made even more succint if a *comparator construction method* is used in palce of a lambda ([Item 43](../43)):

```java
Collections.sort(words, comparingInt(String::length));

// Taking advantage of sort method added to the List interface in Java 8
words.sort(comparingInt(String::length));
```
