# 72: Favor the use of standard exceptions

* Overview
* Commonly Reused Exceptions

## Overview

> The Java libraries provide a set of exceptiosn that covers most of the exception-throwing needs of most APIs.

* Reusing standard exceptions makes your API easier to learn and use because it matches the established convetions that programmers are already familiar with.

* Fewer exception classes means a smaller memory footprint and less time spent loading classes.

* **Do _not_ reuse `Exception`, `RuntimeException`, `Throwable`, or `Error` directly**. Treat these classes as if they were abstract. You can't reliably test fo these exceptions because they are superclasses of other exceptions that a method may throw.

## Commonly Reused Exceptions

| Exception                         | Occasion for Use                                                              |
|-----------------------------------|-------------------------------------------------------------------------------|
| `IllegalArgumentException`        | Non-null parameter value is inappropriate                                     |
| `IllegalStateException`           | Object state is inappropriate for method invocation                           |
| `NullPointerException`            | Parameter value is null where prohibited                                      |
| `IndexOutOfBoundsException`       | Index parameter value is out of range                                         |
| `ConcurrentModificationException` | Concurrent modification of an object has been detected where it is prohibited |
| `UnsupportedOperationException`   | Object does not support method                                                |

* **Throw `IllegalStateException` if no argument values would have worked, otherwise throw `IllegalArgumentException`**. For example, consdier the case of an object representing a deck of card, and suppose there were a method to deal a hand from the deck that took as an argument the size of the hand. If the caller passed a value larger than the number of cards remaining in the deck, it could be construed as an `IllegalArgumentException` (the `handSize` parameter value is too high) or an `IllegalStateException` (the deck contains too few cards).

* `ArithmeticException` and `NumberFormatException` would be appropriate if you were implementing arithmetic objects such as complex numbers or rational numbers.
