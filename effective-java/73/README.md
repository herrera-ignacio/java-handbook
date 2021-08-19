# 73: Throw exceptions appropriate to the abstraction

* Overview
* Example: Exception Translation & Chaining

## Overview

* It is disconcerting when a methdod throws an exception that has no apparent connection to the task that it performs.

> This often happens when a method propagates an exception thrown by a lower-level abstraction. Not only is it disconcerting, but it pollutes the API of the higher layer with implementation details.

* **Higher layers should catch lower-level exception and, in their place, throw exceptions that can be explained in terms of the higher-level abstraction**. This idiom is known as *exception translation*.

* A special form of exception translation, called *exception chaining* is called for in casees where the lower-level exception might be helpful to someone debugging the problem that caused the higher-level exception. The lower-level exception (the *cause*) is passed to the higher-level exception, which provides an accessor method (`Throwable`'s `getCause` method) to retrieve the lower-level exception.

* While exception translation is superior to mindless propagation of exceptions from lower layers, it should not be overused.

## Example: Exception Translation & Chaining

```java
// Exception Chaining
try {
  ... // Use lower-level abstraction to do our binding
} catch (LowerLevelException cause) {
  throw new HigherLevelException(cause);
}

// Exception with chaining-aware constructor
class HigherLevelException extends Exception {
  HigherLevelException(Throwable cause) {
    super(cause);
  }
}
```
