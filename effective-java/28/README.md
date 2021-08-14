# 28: Prefer lists to arrays

* Overview
  * Arrays are covariant
  * Arrays are reified

## Overview

Arrays differ from generic types in two important ways. Because of these fundamental differences, arrays and generics do not mix well.

## Arrays are covariant

First, arrays are *covariant*. This means that if `Sub` is a subtype of `Super` then the array type `Sub[]` is a subtype of the array type `Super[]`.

Generics, by contrast, are *invariant*. For any two distinct types `Type1` and `Type2`, `List<Type1>` is neither a subtype nor a supertype of `List<Type2>`.

You might think this means that generics are deficient, but arguably it is arrays that are deficient. This code fragment is legal:

```java
// Fails at runtime!
Object[] objectArray = new Long[1];
objectArray[0] = "I don't fit in"; // Throws ArrayStoreException[]
```

But this one is not:

```java
// Won't compile
List<Object> ol = new ArrayList<Long>(); // Incompatible types
ol.add("I don't fit in");
```

## Arrays are reified

This means that arrays know and enforce their element type at runtime. Generics, by contrast, are implement by *erasure*, meaning that they enforce their type constraints only at compile time and discard (or *erase*) their element type information at runtime.

> Erasure is what allowed generic types to interoperate freely with legacy code that didn't use generics.
