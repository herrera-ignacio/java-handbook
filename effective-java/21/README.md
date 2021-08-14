# 21: Design interfaces for posterity

* Using default methods to add new methods to existing interfaces should be avoided unless the need is critical.

  * It is not always possible to write a default method that maintains all inveriants of every conceivable implementation.

  * In the presence of default methods, existing implementations of an interface may compile without error or warning but fail at runtime.

* While default methods make it *possible* to add methods to existing interfaces, there is a great risk in doing so. Thus, it is still of the utmost importance to design interfaces with great care.
