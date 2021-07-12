# 14: Consider implementing `Comparable`

The `compareTo` method is not declared in `Object`. Rather, it is the sole method in the `Comparable` interface. It is similar to `Object`'s `equals` method, except that it permits order comparisons in addition in addition to simple equality comparisons, and it is generic.

By implementing `Comparable`, a class indicates that its instances have a *natural ordering*.

> Virtually all of the value classes in the Java platform libraries, as well as all enum types ([Item 34](../34)), implement `Comparable`.

If you are writing a value class with an obvious natural ordering, such as alphabetical order, numerical order, or chronological order, you should implement the `Comparable` interface:

```java
public interface Comparable<T> {
  int compareTo(T t);
}
```

## Advantages

**Sorting** an array objects that implement `Comparable` is as simple as `Arrays.sort(a);`. It is similarly easy to **search**, **compute extreme values**, and maintain automatically sorted collections of `Comparable` objects.

By implementing `Comparable`, you allow your class to interoperate with all of the many generic algorithms and collection implementations that depend on this interface.

## `compareTo` contract

Compares this object with the specifie object for order. Returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object. Throws `ClassCastException` if the specified object's type prevents it from being compared to this object.

> The notation `sgn(expression)` is defined to return -1, 0, or 1, according to whether the value of *expression* is negative, zero, or positive.

* The implementor must ensure that `sgn(x.compareTo(y)) == -sgn(y.compareTo(x))` for all `x` and `y`.
* The implementor must ensure that the **relation is transitive**.
* The implementor must ensure that `x.compareTo(y) == 0` implies that `sgn(x.compareTo(z)) = sgn(y.compareTo(z))`, for all `z`.
* It is strongly recommended, but not required, that `(x.compareTo(y) == 0) == (x.equals(y))`. Generally speaking, any class that implements the `Comparable` interface and violates this condition should clearly indicate this fact.

> The recommended language for the last item is: *"Note: This class has a natural ordering that is inconsistent with `equals`"*.

One consequence of these three provisions is that the equality test imposed by a `compareTo` method must obey the same restrictions imposed by the `equals` contract:

* Reflexivity
* Symmetry
* Transitivity

A class that violates the `compareTo` contract can break other classes that depend on comparision (e.g., the sorted collections `TreeSet` and `TreeMap` and the utility classes `Collections` and `Arrays`, which contain searching and sorting algorithms).
