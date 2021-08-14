# 18: Favor composition over inheritance

* Overview
* Compostion

## Overview

> This refers to *implementation inheritance* and does not apply to *interface inheritance* (when a class implements an interface or when one interface extends another).

* Safe to use inheritance within a package or for classes specifically designed and documented for extension ([Item 19](../19)).
* **Inheritance violates encapsulation**: A subclass depends on implementation details of its superclass. Subclasses invariants might be corrupted by modifying the superclass directly.
* Inheritance is appropriate only in circumstances where the subclass really is a *subtype* of the superclass (only if an *"is-a"* relationship exists between the two classes).
* Inheritance propagates an API's flaws, while composition lets you design a new API that hides these flaws.

## Composition

Instead of extending an existing class, give your new class a private field that rteferences an instance of the existing class (*composition*). This approach uses *forwarding*, the new class invokes the corresponding method on the contained instance of the existing class. In

The resulting class will have **no dependencies on the implementation details** of the existing class.
