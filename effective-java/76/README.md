# 76: Strive for failure atomicity

* **A failed method invocation should leave the object in the state that it was in prior to the invocation**. A method with this property is said to be *failure-atomic*.

  * The simplest way is to design **immutable objects**, as failure atomicity is free because the state of each object is consistent when it is created and can't be modified after.

  * For mutable objets, the most common way is to check parameters for validity before performing the operation. A second approach is to order the computation so that any part that may fail takes place before any part that modifies the object. A third approach is to perform the operation on a temporary copy of the object and replace the contents of the original once the operation is complete. A last and far less common approach is to write a *recovery code* that intercepts a failure and causes the object to roll back its state.

* While failure atomicity **is generally desirable**, it is not always achievable (e.g., multiple threads attempt to modify the same object concurrently without proper synchronization).

* Failure atomicity **is not always desirable**. For some operations, it would significantly increase the cost or complexity.
