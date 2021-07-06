# 11: Always override `hashCode` when you override `equals`

If you fail to do so, your class will violate the general contract for `hashCode`, which will prevent it from functioning properly in collections such as `HashMap` and `HashSet`.

## `hashCode` contract

* When the `hashCode` method is invoked on an object repeatedly during an execution of an application, it must consistently return the same value, provided no information used in `equals` comparisons is modified.

* If two objects are equal according to the `equals(Object)` method, then calling `hashCode` on the two objects must produce the same integer result.

* If two objects are unequal according to the `equals(Object)` method, it is *not* required that calling `hashCode` on each of the objects must produce distinct results. However, the programmer should be aware that producng distinct results for unequal objects may improve performance of hash tables.
  
## Good Hash Function

A good hash function tends to produce unequal hash codes for unequal instances. Ideally, a hash function should distribute any reasonable collection of unequal instances uniformly across all `int` values.

> The *AutoValue framework* provides a fine alternative to writing `equals` and `hashCode` methods manually.

### A Simple Recipe

1. Declare an `int` variable named `result`, and initialize it to the hash code `c` for the first significant field in your object, as computed in step *2.2* (recall from [item 10](../10) that a significant field is a field that affects equals comparisons).

2. For every remaining signifivant field `f` in your object, do the following:
   1. Compute an `int` hash code `c` for the field:
      1. If the field is of a primitive type, compute `Type.hashCode(f)`, where `Type` is the boxed primitive class corresponding to `f`'s type.
     
      2. If the field is an object reference and this class's `equals` method compares the field by recursively invoking `equals`, recursively invoke `hashCode` on the field. If a more complex comparison is required, compute a "canonical representation" for this field and invoke `hashCode` on the canonical representation. If the value of the field is `null`, use `0` (or some other constant, but `0` is traditional).
      
      3. If the field is an array, treat it as if each significant element were a separate field. That is, compute a hash code for each signifcant element by applying these rules recursively, and combine the values per step *2.2*. If the array has no significant elements, use a constant, preferably not `0`. If all elements are significant, use `Arrays.hashCode`.
   2. Combine the hash code  `c` computed in step *2.1* into `result` as follows: `result = 31 * result + c`.

3. Return `result`.

### Some Guidelines

**Do not be tempted to exclude significant fields from the hash code computation to improve performance**. While the resulting hash function may run faster, its poor quality may degrade hash tables' performance to the point where they become unusable. A given large collection of instances may differ mainly in regions you've chosen to ignore.

**Don't provide da detailed specification for the value returned by `hashCode`, so clients can't reasonably depend on it; this gives you the flexibility to change it**.

## PhoneNumber example

Suppose you have `PhoneNumber` class that is initialized with three `Short` parameters `lineNum`, `prefix` and `areaCode` (`new PhoneNumber(707, 867, 5309)`).

```java
// Typical hashCode method
@Override public int hashCode() {
  int result = Short.hashCode(areaCode);
  result = 31 * result + Short.hashCode(prefix);
  result = 31 * result + Short.hashCode(lineNum);
  return result;
}

// One-line hashCode method - mediocre performance
@Overrid public int hashCode() {
  return Objects.hash(lineNum, prefix, areaCode);
}

// hashCode method with lazily initialized cached hash code
private int hashCode; // Automatically initialized to 0

@Override public int hashCode() {
  int result = hashCode;
  if (result == 0) {
    result = Short.hashCode(areaCode);
    result = 31 * result + Short.hashCode(prefix);
    result = 31 * result + Short.hashCode(lineNum);
    hashCode = result;
  }
  return result;
}
```
