# 10: Obey the general contract when overriding `equals`

* Don't override `equals` unless needed
* When is overriding `equals` needed?
* The contract
* Good practices for `equals` method

## Don't override `equals` unless needed

This is the right thing to do if any of the following conditions apply:

* **Each instance of the class is inherently unique**. This is true for classes such as `Thread` that represent active entities rather than values. The implementation provided by `Object` has exactly the right behavior for these classes.

* **There is no need for the class to provide a _"logical equality"_ test**.

* **A superclass has already overriden `equals`, and the superclass behavior is appropriate for this class**. For example, most `Set` implementations inherit their `equals` implementation from `AbstractSet`, `List` implementations from `AbstractList` and `Map` implementations from `AbstractMap`.

* **The class is private or package-private, and you are certain that its `equals` method will never be invoked**. If you are extremly risk-averse, you can override the `equals` method to ensure that it isn't invoked accidentally:

```java
@Override public boolean equals(Object o) {
  throw new AssertioNError(); // Method is never called
}
```

## When is overriding `equals` needed?

It is when a class has a notion of **logical equality** that differs from mere object identity and a superclass has not already overriden `equals`. This is generally the case for *value classes*.

A *value class* is simply a class that represents a value, such as `Integer` or `String`. A programmer who compares references to value objects using the `equals` method expects to find out whether they are logically equivalent, not whether they refer to the same object. It enables instances to serve as map keys or set elements with predictable, desirable behavior.

> One kind of value class that does *not* require the `equals` method to be overriden in a class that uses instance control ([Item 1](../1)) to ensure that at most one object exists with each value. *Enum* types ([Item 34](../34)) fall into this category.

## The contract

Here is the contract, from the specification for `Object`. The `equals` method implements an *equivalence relation* with the following properties:

* **Reflexive**: For any non-null reference value `x`, `x.equals(x)` must return `true`.

* **Symmetric**: For any non-null reference values `x` and `y`, `x.equals(y)` must return `true` if and only if `y.equals(x)` returns `true`.

* **Transitive**: For any non-null reference values `x`, `y`, `z`, if `x.equals(y)` returns `true` and `y.equals(z)` returns `true`, then `x.equals(z)` must return `true`.

* **Consistent**: For any non-null reference values `x` and `y`, multiple invocations of `x.equals(y)` must consistently return `true` or consistently return `false`, provided no information used in `equals` comparisons is modified. *Don't write an `equals` method that depends on unreliable resources*.

* For any non-null reference value `x`, `x.equals(null)` must return `false`.

> If you violate it, you may well find that your program behaves erratically or crashes, and it can be very difficult to pin down the source of the failure.

Once you've violated the `equals` contract, you simply don't know how other objects will behave when confronted with your object.

## Good practices for `equals` method

1. Don't do explicit null check

```java
// Explicit null check - unnecessary!
@Override public boolean equals(Object o) {
  if (o == null)
    return false;
  ...
}

// Implicit null check - preferred
@Override public boolean equals(Object o) {
  if (!(o instanceof MyType))
    return false;
  MyType mt = (MyType) o;
}
```

2. Use the `==` operator to check if the argument is a reference to this object.

3. Use the `instanceof` operator to check if the argument has the correct type.

4. Cast the argument to the correct type.

5. For each "significant" field in the class, check if that field of the argument matches the corresponding field of this object.

6. Always override `hashCode` when you override `equals` ([Item 11](../11)).

7. Don't substitute another type for `Object` in the `equals` declaration. Consistent use of the `Override` annotation will prevent you from making this mistake ([Item 40](../40)).

```java
// Broken - parameter type must be Object!
// You are overloading here, not overriding
public boolean equals(MyClass o) {
  ...
}

// Still broken, but won't compile
@Override public boolean equals(MyClass o) {

}
```
