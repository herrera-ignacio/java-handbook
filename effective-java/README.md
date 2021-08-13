# Effective Java

> These are my notes from the *Effective Java Third Edition* book by *Joshua Bloch*.

* [Creating and Destroying Objects](c1)
  * [1: Consider static factory methods instead of constructors](1)
  * [2: Consider a builder when faced with many constructor parameters](2)
  * [3: Enforce the singleton property with a private constructor or an enum type](3)
  * [4: Enforce noninstantiability with a private constructor](4)
  * [5: Prefer dependency injection to hardwiring resources](5)
  * [6: Avoid creating unnecessary objects](6)
  * [7: Eliminate obsolete object references](7)
  * [8: Avoid finalizers and cleaners](8)
  * [9: Prefer try-wioth-resources to try-finally](9)
* [Methods Common to All Objects](c2)
  * [10: Obey the general contract when overriding `equals`](10)
  * [11: Always override `hashCode` when you override `equals`](11)
  * [12: Always override `toString`](12) 
  * [13: Override `clone` judiciously](13)
  * [14: Consider implementing `Comparable`](14)
* [Classes and Interfaces](c3)
  * [15: Minimize the accessibility of classes and members](15)
  * [16: In public classes, use accessor methods, not public fields](16)
  * [17: Minimize mutability](17)