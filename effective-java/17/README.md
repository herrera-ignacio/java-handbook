# 17: Minimize Mutability

* Overview
* Advantages
* Disadvantages
* Five Rules

## Overview

**Classes should be immutable unless there's a very good reason to make them mutable**. Immutable classes provide many advantages, and their only disadvantage is the potential for performance problems under certain circumstances.

There are some classes for which immutability is impractical. **If a class cannot be made immutable, limit its mutability as much as possible**. Reducing the number of states in which an object can exist makes it easier to eason about the object and reduces the likelihood of errors. Therefore, **make every field `private final` unless there's a good reason to do otherwise**.

**Constructors should create fully initialized objects with all of their invariants established**. Don't provide a public initialization method separate from the constructor or static factory unless there is a compelling reason to do so.

## Advantages

Immutable classes are: 

* Easier to design, implement, and use than mutable classes.
* They are **less prone to error and are more secure**
* **Thread-safe**; they require no synchronization. They cannot be corrupted by concurrent access. Thus, they **can be shared freely**.

## Disadvantages

* They require a separate object for each distinct value. Creating these objects can be costly.

## Five Rules

To make a class immutable, follow these **five rules**:

1. __Don't provide *mutators* (methods that modify the object's state)__.

2. **Ensure that the class can't be extended.**. Prevent subclasses from compromising the immutable behavior of the class by behaving as if the object's state has changed.
   1. Make class `final`
   2. Make all of its constructors *private* or *package-private* and add public static factories in place. This allows the use of multiple package-private implementation classes. To clients that reside outside its package, the immutable class is effectively final because it cannot be extended without a public or protected constructor.

3. **Make all fields final**. Clearly express your intent in a manner that is enforced by the system.

4. **Make all fields private**. Otherwise it precludes changing the internal representation in a later release.

5. **Ensure exclusive access to any mutable components**. Never provide clients with fields that refer to mutable objects nor initialize such a field to a client-provided object reference. Make *defensive copies* in constructors, accessors, and `readObject` methods.
