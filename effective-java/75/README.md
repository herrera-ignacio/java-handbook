# 75: Include failure-capture information in detail messages

* Overview
* Example

## Overview

> When a program fails due to an uncaught exception, the system automatically prints out the exception's stack trace, which contains the exception's *string representation*, the result of invoking its `toString` method.

* To capture a failure, the detail message of an exception should contain the values of all parameters and fields that contributed to the exception.

* One caveat concerns security-sensitive information, so do not include passwords, encryption keys, and the like in detail messages.

## Example

```java
public IndexOutOfBoundsException(int lowerBound, int upperBound, int index) {
  // Generate ad etail message that captures the failure
  super(String.format("Lower bound: %d, Upper bound: %d, Index: %d", lowerBound, upperBound, index));

  // Save failure information for programmatic access
  this.lowerBound = lowerBound;
  this.upperBound = upperBound;
  this.index = index;
}
```
