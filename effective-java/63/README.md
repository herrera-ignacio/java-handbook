# 63: Beware the performance of string concatenation

* Overview
* Example

## Overview

* **Don't use the string concatenation operator to combine more than a few strings** unless performance is irrelevant.

* Using the string concatenation operator repeatedly to concatenate `n` strings requires time quadratic in `n`. This is an unfortunate consequence of the fact that strings are *immutable* and their contents need to be copied.

* To achieve acceptable performance, use a `StringBuilder` in place of a `String`. Alternatively, use a character array, or process the strings one at a time instead of combining them.

## Example

```java
public String statement() {
  StringBuilder b = new StringBuilder(numItems() * LINE_WIDTH);
  for (int i = 0; i < numItems(); i++)
    b.append(lineForItem(i));
  return b.toString();
}
```
