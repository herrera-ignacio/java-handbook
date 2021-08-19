# 61: Prefer primitive types to boxed primitives

* Overview
* Primitive Types vs Boxed Primitives
* Example

## Overview

* Java has a two-part type system, consisting or *primitives*, such as `int`, `double` and `boolean`; and *reference types*, such as `String` and `List`. Every primitive type has a corresponding reference type, called a **boxed primitive** (e.g., `Integer`, `Double`, and `Boolean`).

* Autoboxing and auto-unboxing blur but do not erase the distinction between the primitive and boxed primitive types. It's important that you remain **aware of which you are using and that you choose carefully** between them.

* **Use primitives in preference to boxed primitives wheneve you have the choice**. Primitive types are simpelr and faster.

* If you must use boxed primitives, be careful. **Autoboxing reduces the verbosity, but not the danger, of using boxed primitives**.

* Applying the `==` operator to boxed primitives is almost always wrong.

* When you mix primitives and boxed primitives in an operation, the boxed primitive is auto-unboxed. If a null object reference is auto-unboxed, you get a `NullPointerException`.

### Primitive Types vs Boxed Primitives

There are three major differences between primitives and boxed primitives.

* **Primitives have only their values, whereas boxed primitives have identities distinct from their values**. Thus, two boxed primitive instances can have the same value and different identities.

* **Primitive types have only fully funcitonal values**, whereas each boxed type has one nonfunctional value (`null`), in addition to all the functional alues of the corresponding primitive type.

* **Primitives are more time- and space-efficient** than boxed primitives.

## Example

Avoid erroneous identity comparison between two `Integer`:

```java
Comparator<Integer> naturalOrder = (iBoxed, jBoxed) -> {
  int i = iBoxed, j = jBoxed; // Auto-unboxing
  return i < j ? -1 : (i == j ? 0 : 1);
}
```
