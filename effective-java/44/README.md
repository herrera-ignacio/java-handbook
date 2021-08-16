# 44: Favor the use of standard functional interfaces

* Overview
* Basic interfaces
* Writing a purpose-built functional interface

## Overview

The `java.util.function` package provides a large collection of standard functional interfaces for your use. **If one of the standard functional interfaces does the job, you should generally use it in preference to a purpose-built functional interface**.

This will make your API easier to learn, and will provide significant interopoerability benefits.

If you remember six basic interfaces, you can derive the rest when you need. The basic interfaces **operate on object reference types**. Most of the rest exist only to provide support for primitive types. **Don't be tempted to use basic functional interfaces with boxed primitives instead of primitive functional interfaces**. While it works, it violates the advice of [Item 61](../61), "*prefer primitive types to boxed primitives*". The performance consequences of using boxed primitives for bulk operations can be deadly.


## Basic Interfaces

| Interface | Function Signature | Example | Details |
|---|---|---|---|
| `UnaryOperator<T>` | T apply(T t) | String::toLowerCase | Function whose result and argument types are the same. |
| `BinaryOperator<T>` | T apply(T t1, T t2) | BigInteger::add | * |
| `Predicate<T>` | boolean test(T t) | Collection::isEmpty | Function that takes an argument and returns a boolean. |
| `Function<T, R>` | R apply(T t) | Arrays::asList | Function whose argument and return types differ. |
| `Supplier<T>` | T get() | Instant::now | Function that takes no arguments and returns (or "supplies") a value. |
| `Consumer<T>` | void accept(T t) | System.out::println | Function that takes an argument and returns nothing, "consuming" its argument. |

## Writing a purpose-built functional interface

You should consider writing a purpose-built functional interface in preference to using a standard one if you need a functional interface that shares one or more of the following characteristics with `Comparator`:

* It will be commonly used and could benefit from a descriptive name.

* It has a strong contract associated with it.

* It would benefit from custom default methods.

Allways **annotate your functional interfaces** with the `@FunctionalInterface` annotation.
