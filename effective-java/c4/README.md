# Generics

With generics, you tell the compiler what types of objects are permitted in each collection. The compiler inserts casts for you automatically and tells you *at compile time* if you try to insert an object of the wrong type. This benefit, which is not limited to collections, come at a price

This items tell you how to maximize the benefits and minimize the complications.

## Quick Reference

| Term                    | Example                            |
|-------------------------|------------------------------------|
| Parameterized type      | `List<String>`                     |
| Actual type parameter   | `String`                           |
| Generic type            | `List<E>`                          |
| Formal type parameter   | `E`                                |
| Unbounded wildcard type | `List<?>`                          |
| Raw type                | `List`                             |
| Bounded type parameter  | `<E extends Number>`               |
| Recursive type bound    | `<T extends Comparable<T>>`        |
| Bounded wildcard type   | `List<? extends Number>`           |
| Generic method          | `static <E> List<E> asList(E[] a)` |
| Type token              | `String.class`                     |
