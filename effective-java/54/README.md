# 54: Return empty collections or arrays, not nulls

* Overview
* Collections Example
* Arrays Example

## Overview

* There's usually no reason to special-case the situation where no element is available in a collection. Doing so requires extra code in the client to handle the possibly null return value.

* It is sometimes argued that a `null` return value is preferable to an empty collection or array because it avoids the expense of allocating the empty container.
  * First, it is inadvisable to worry about performance at this level unless measurements have shown that the allocation in question is a real contributor of performance problems ([Item 67](../67)).
  * Second, it *is* possible to return empty collections and arrays without allocating them.


## Collections Example

```java
private final List<Cheese> cheesesInStock = ...;

// The right way to return a possibly empty collection
public List<Cheese> getCheeses() {
  return new ArrayList<>(cheesesInStock);
}
```

You can avoid the allocations by returning the same *immutable* empty collection repeatedly, as immutable objects may be shared freely ([Item 17](../17)). This is an optimization, and it's seldom called for; you should measure performance before and after, to ensure that it's actually helping:

```java
// Optimization - avoids allocating empty collection
public List<Cheese> getCheeses() {
  return new cheesesInStock.isEmpty() ? Collections.emptyList() : new ArrayList<>(cheesesInStock);
}
```

> You can similarly use `Collections.emptySet` and `Collections.emptyMap`.

### Arrays Example

The situation for arrays is identical to that for collection. Never return null instead of a zero-length array.

```java
// The right way to return a possibly empty array
public Cheese[] getCheeses() {
  return cheesesInStock.toArray(new Cheese[0]);
}
```

If you believe that allocating zero-length arrays is harming performance, you can return the same zero-length array repeatedly because all zero-length arrays are immutable:

```java
// Optimization - avoids allocating empty arrays
private static final Cheese[] EMPTY_CHEESE_ARRAY = new Cheese[0];

public Cheese[] getCheeses() {
  return cheesesInStock.toArray(EMPTY_CHEESE_ARRAY);
}
```

Do *not* preallocate the array passed `toArray` in hopes of improving performance. Studies have shown that is counterproductive (Shiplev16):

```java
// Don't do this - preallocating the array harms performance!
return cheesesInStock.toArray(new Cheese[cheesesInStock.size()]);
```
