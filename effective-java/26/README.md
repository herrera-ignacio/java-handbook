# 26: Don't use raw types

> Generic classes and interfaces are collectively known as *generic types*, those whose declaration has one or more *type parameters*.

* Overview
* Exceptions

## Overview

If you use raw types, **you lose all the safety and expressiveness benefits of generics**.

* Each generic type defines a *raw type*, which is the name of the generic type used without any accompanying type parameters. For example, the raw type corresponding to `List<E>` is `List`.

* Raw types behave as if all of the generic type information were erased from the type declaration. They exist primarily for compatibility with pre-generics code.

* Raw types **don't let you discover casting errors at compile time**. With generics, the compiler inserts invisible casts for you when retrieving elements from collections and guarantees that they won't fail.

* While you shouldn't use raw types such as `List`, **it is fine to use types that are parameterized to allow insertion of arbitrary objects**, such as `List<Object>`. Loosely speaking, raw types opted out of the generic type system, while the latter has explicitly told the compiler that is capable of holding objects of any type.

## Exceptions

* **You must use raw types in class literals**. The specification does not permit the use of parameterized types.

* It is illegal to use `instanceof` operator on parameterized types othat than unbounded wildcard types.

```java
// Legititmate use of raw type - instanceof operator
if (o instanceof Set) {  // Raw type
  Set<?> s = (Set<?>) o; // Wildcard type
}
```
