# 43: Prefer method references to lambdas

* Overview
* Method References
* Example

## Overview

> Where method references are shorter and clearer, use them; where they aren't, stick with lambdas.

* Method references usually result in **shorter, clearer code**.

* **In some lambdas, however, the parameter names you choose provide useful documentation**, making the lambda more readable and maintainable than a method reference, even if the lambda is longer.

* **You can extract the code from a lambda** if it gets too long or complex, **and replace the lambda with a reference to that method**. You can give that method a good name and document it.

## Method References

| __Method Ref Type__ | __Example__ | __Lambda Equivalent__ |
|---|---|---|
| Static | `Integer::parseInt` | `str -> Integer.parseInt(str)` |
| Bound Instance | `Instant.now()::isAfter`, the receiving object is specified in the method reference. | `Instant then = Instant.now(); t -> then.isAfter(t)` |
| Unbound Instance | `String::toLowerCase`, the receiving object is specified when the function is applied, via an additional parameter before the method's declared parameters. Often used as mapping and filter functions in stream pipelines. | `str -> str.toLowerCase()` |
| Class Constructor | `TreeMap<K,V>::new` | `() -> new TreeMap<K,V>()` |
| Array Constructor | `int[]::new` | `len -> new int[len]` |

## Example

Here is a code snippet from a program that maintains a mapfrom arbitrary keys to `Integer` values. If the value is interpreted as a count of the number of instances of the key, then the program is a multiset implementation. The function of the code snippet is to associate the number 1 with the key if it is not in the map and to increment the associated value if the key is already present:

```java
map.merge(key, 1, (count, incr) -> count + incr);
```

If no mapping is present for the given key, the method simply inserts the given value; if a mapping is already present, `merge` applies the given function to the current value and the given value and overwrites the current value with the result.

The parameters `count` and `incr` don't add much value, and they take up a fair amount of space. as of Java 8, `Integer` provdes a static method `sum` that does exactly the same thing. We can simply pass a reference to this method and get the same result with less visual clutter:

```java
map.merge(key, 1, Integer::sum);
```
