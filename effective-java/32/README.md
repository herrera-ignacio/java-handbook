# 32: Combine generics and varargs judiciously

* The purpose of varargs is to allow clients to pass a variable number of arguments to a method, but it is a *leaky abstraction*: when you invoke a varargs method, an array is created to hold the varargs parameter; that array, which should be an implementation detail, is visible. As a consequence, you get confusing compiler warnings when varargs parameters have generic or parameterized types.

* If a method declares its varargs parameter to be a *non-reifiable type*, the compiler generates a warning on the declaration.

> Non-refiable type is one whose runtime representation has less information than its compile-time representation.

* If a method is invoked on varargs whose inferred type is non-reifiable, the compiler generates a warning on the invocation too.

```
warning: [unchecked] Possible heap pollution from
  paramaterized vararg type List<String>
```

> *Heap* pollution occurs when a variable of a parameterized type refers to an object that is not of that type. It can cause the compiler's automatically generated casts to fail, violating the fundamental guarantee of the generic type system.

* The `SafeVarargs` annotation allows the author of a method with generic varargs parameter to suppress client warnings automatically. It constitutes a **promise** by the author of a method **that it is typesafe**.

  * **It is unsafe to store a value in a generic varargs array parameter**.

  * **It is unsafe to give another method access to a generic varargs array parameter**.
