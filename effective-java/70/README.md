# 70: Use checked exceptions for recoverable conditions and runtime exceptions for programming errors

> Java provides three kinds of throwables: *checked exceptions*, *runtime exceptions* and *errors* (the two latter, are *unchecked exceptions*). There are some general rules that provide strong guidance on when it is appropriate to use each kind of throwable.

* **Use checked exceptions for conditions from which the caller can reasonably be expected to recover**. By throwing a checked exception, you force the caller to handle the exception in a `catch` clause or to propage it outward. Each checked exception is therefore a potent indication to the API user that the associated condition is a possible outcome of invoking the method, and it presents a mandate to recover from the condition.

* *Unchecked exceptions* (*runtime exceptions* and *errors*) are throwables that needn't, and generally shoudln't, be caught. It is generally the case that recovery is impossible and continued execution would do more harm than good. If a program does not catch such a throwable, it will cause the current thread to halt with an appropriate error message.

* **Use runtime exceptions to indicate programming errors**. The great majority of runtime exceptions indicate *precondition violations*. This is simply a failure by the client of an API to adhere to the contract established by the API specification.

> For example, the contract for array access specifies that the array index must be between zero and the array length minus one, inclusive. `ArrayIndexOutOfBoundsException` indicates that this precondition was violated.

* There is a strong convention that **_errors_ are reserved for use by the JVM** to indicate resource deficiencies, invariant failures, or other conditions that make it impossible to continue execution. Therefore, **all of the unchecked throwables you implement should subclass `RuntimeException`** (directly or indirectly). Not only you shouldn't define `Error` subclasses, but with the exception of `AssertionError`, you shoudln't throw them either.
