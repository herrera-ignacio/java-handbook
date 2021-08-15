# 31: Use bounded wildcards to increase API flexiblity

* For maximum flexibility, use wildcard types on input parameters that preresent producers or consumers.

  * If an input parameter is both a producer and a consumer, then wildcard types will do you no good: you need an exact type match, which is what you get without any wildcards.

* Parameterized types are *invariant*. In other words, for any two distinct types `Type1` and `Type2`, `List<Type1>` is neither a subtype nor a supertype of `List<Type2>`. Loosely

* Sometimes you need more flexibility than invariant typing can provide. *Bounded wildcard type* `Iterable<? extends E>` means "*Iterable of some subtype of E*".

* Do not use bounded wildcard types as return types.

* If the user of a class has to think about wildcard types, there is probably something wrong with its API.

* If a tpye parameter appears only once in a method declaration, replace it with a wildcard.
