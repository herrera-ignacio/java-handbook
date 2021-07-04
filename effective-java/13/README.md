# 13: Override `clone` judiciously

* `Cloneable` Overview
  * Flaws
  * Features
* `clone` general contract
* Primitive/Immutable Fields Case Study
* Mutable Fields Case Study (Stack)
* Mutable Fields Case Study (Hash Table)
* Other Best Practices
* The Copy Constructor/Factory


## `Cloneable` Overview

The `Cloneable` interface was intended as a **mixin interface** ([Item 20](../20)) for classes to advertise that they permit cloning.

Given all the problems associated with `Cloneable`, new interfaces should not extend it, and new extendable classes shoujld not implement it. While it's less harmful for final classes to implement `Cloneable`, this should be viewed as a performance optimization, reserved for the rare cases where it is justified ([Item 67](../67)).

### Flaws

Unfortunately, it fails to serve this purpose. Its primary flaw is that it lacks a `clone` method, and `Object`'s `clone` method is protected. You cannot, without resorting to *reflection* ([Item 65](../65)), invoke `clone` on an object merely because it implements `Cloneable`. Even a reflective invocation may fail, because there is no guarantee that the object has an accessible `clone` method.

Though the specification doesn't say it, **in practice, a class implementing `Cloneable` is expected to provide a properly functioning public `clone` method**.

> In order to achieve this, the class and all of its superclasses must obey a complex, unenforceable, thinly documented protocol. The resulting mechanism is fraigle, dangerous, and *extralinguistic*: it creates objects without calling a constructor.

Like serialization, the `Cloneable` architecture is **incompatible with normal use of final fields referring to mutable objects** (`clone` would be prohibited from assigning a new value to the field), except in cases where the mutable objects may be safely shared between an object and its clone. 

### Features

`Cloneable` determines the behavior of `Object`'s protected `clone` implementation: if a class implements `Cloneable`, `Object`'s `clone` method returns a field-by-field copy of the object; otherwise it throws `CloneNotSupportedException`. This is a **highly atypical use of interfaces** and not one to be emulated. Normally implementing an interface says something about what a class can do for its clients, in this case, it modifies the behavior of a protected method on a superclass.

## `clone` general contract

> The general contract for the `clone` method is weak. The following is from the `Object` specification.

Creates and returns a copy of this object. The precise meaning of "copy" may depend on the class of the object. The general intent is that, for any object `x`, the following expressions will be `true`:

```java
x.clone() != x
x.clone().getClass() == x.getClass()
x.clone().equals(x)
```

But these are *not* absolute requirements.

By convention, the object returned by this method should be obtained by calling `super.clone`. If a class and all of its superclasses (except `Object`) obey this convention, it will be the case that `x.clone().getClass() == x.getClass()`.

By convention, the returned object should be independent of the object being cloned. To achieve this independence, it may be necessary to modify one or more fields of the object returned by `super.clone` before returning it. If a class that overrides `clone` is final, this convention may be safely ignored, but if a final class has a `clone` method that does not invoke `super.clone`, there is no reason for the class to implement `CLoneable`, as it doesn't rely on the behavior of `Object`'s clone implementation

> This mechanism is vaguely similar to *constructor chaining*, except that it isn't enforced: if a class's `clone` method returns an instance that is *not* obtained by calling `super.clone` but by calling a constructor, the compiler won't complain, but if a subclass of that class calls `super.clone`, the resulting object will have a wrong class, preventing the subclass from `clone` method from working properly.

## Primitive/Immutable Fields Case Study

Suppose you want to implement `Cloneable` in a class whose superclass provides a well-behaved `clone` method. First call `super.clone`. If every field contains a primitive value or a reference to an immutable object, the returned object may be exactly what you need, but note that **immutable classes should never provide a `clone` method** because it would merely encourage wasteful copying.

```java
class PhoneNumber extends Cloneable {
  // ...

  @Override public PhoneNumber clone() {
    try {
      return (PhoneNumber) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(); // can't happen
    }
  }
}
```

> The need for this boilerplate indicates that `CloneNotSupportedException` should have been unchecked ([Item 71](../71)).

## Mutable Fields Case Study (Stack)

If an object contains fields that refer to mutable objects, the simple `clone` implementation shown earlier could be disastrous.

In this case, we should **use the `clone` method as a constructor; you must ensure that it does no harm to the original object and that it properly establishes invariants on the clone**.

Consider the `Stack` example from [Item 7](../7):

```java
// Clone method for class with references to mutable state
@Override public Stack clone() {
  try {
    Stack result = (Stack) super.clone();
    result.elements = elements.clone();
    return result;
  } catch (CLoneNotSupportedException e) {
    throw new AssertionError();
  }
}
```

> Calling `clone` on an array returns an array whose runtime and compile-time types are identical to those of the array being cloned.

## Mutable Fields Case Study (Hash Table)

**It is not always sufficient merely to call `clone` recursively**.

Suppose you are writing a `clone` method for a hash table whose internals consist of an array of buckets, each of which references the first entry in a linked list of key-value pairs.

```java
public class HashTable implements Cloneable {
  private Entry[] buckets = ...;

  private static class Entry {
    final Object key;
    Object value;
    Entry next;

    Entry(Object key, Object value, Entry next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }
  }

  // ...
}
```

Suppose you merely clone the bucket array recursively. Though the clone has its own bucket array, this array references the same linked lists as the original.

```java
// Broken clone method - results in shared mutable state!
@Override public HashTable clone() {
  try {
    HashTable result = (HashTable) super.clone();
    result.buckets = buckets.clone();
    return result;
  } // catch...
}
```

To fix thhis problem, you'll have to copy the linked list that comprises each bucket:

```java
// Recursive clone method for class with complex mutable state
private class HashTable implements Cloneable {
  // ...

  private static class Entry {
    // ...

    // Recursively copy the linked list headed by this Entry
    Entry deepCopy() {
      return new Entry(key, value, next == null ? null : next.deepCopy());
    }
  }

  @Override public HashTable clone() {
    try {
      HashTable result = (HashTable) super.clone();
      result.buckets = new Entry[buckets.length];
      for (int i = 0; i < buckets.length; i++)
        if (buckets[i] != null)
          result.buckets[i] = buckets[i].deepCopy();
      return result;
    } // catch ...
  }
}
```

If the list is long, this could easily cause a stack overflow. To prevent this from happening, you can replace the recursion in `deepCopy` with *iteration*:

```java
Entry deepCopy() {
  Entry result = new Entry(key, value, next);
  for (Entry p = result; p.next != null; p = p.next)
    p.next = new Entry(p.next.key, p.next.value, p.next.next);
  return result;
}
```

## Other Best Practices

* Like a constructor, **a `clone` method nust never invoke an overridable method on the clone under construction** ([Item 19](../19)). If `clone` invokes a method that is overriden in a subclass, this method will execute before the subclass has had a chance to fix its state in the clone, quite possible leading to corruption in the clone and the original.

* Public `clone` methods **should omit the `throws` clause**, as methods that don't throw checked exceptions are easier to use ([Item 71](../71)).

* When designing a class for inheritance ([Item 19](../19)), the class should *not* implement `Cloneable`. You may choose to mimic the behavior of `Object` by implementing a properly functioning `protected clone` method that is declared to throw `CloneNotSupportedException`. This gives subclasses the freedom to implement `Cloneable` or not, just as if they extended `Object` directly.

* Alternatively, you may choose *not* to implement a working `clone` method, and to prevent subclasses from implementing one.

```java
// clone method for extendable class not supporting Cloneable
@Override
protectec final Object clone() throws CloneNotSupportedException {
  throw new CloneNotSupportedException();
}
```

* If you write a **thread-safe class** that implements `Cloneable`, remeber that **its `clone` method must be properly synchronized**, just like any other method ([Item 78](../78)). Object's `clone` method is not synchronized.

## The Copy Constructor/Factory

**A better approach to object copying is to provide a _copy constructor_ or _copy factory_**. A copy constructor is simply a constructor that takes a single argument whose type is the class containing the constructor, for example:

```java
// Copy constructor
public Yum(Yum yum) { ... };
```

A copy factory is the static factory ([Item 1](../1)) analogue of a copy constructor:

```java
// Copy factory
public static Yum newInstance(Yum yum) { ... };
```

### Advantages

* They don't rely on a risk-prone extralinguistic object creation mechanism.
* They don't demand unenforceable adherence to thinly documented conveton.s
* They don't conflict with the proper use of final fields.
* They don't throw unnecessary checkex exceptions.
* They don' require casts.
* Can take an argument whose type is an interface implemented by the class (also known as *conversion constructors* and *conversion factories*).
