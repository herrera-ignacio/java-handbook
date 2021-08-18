# 57: Minimize the scope of local variables

* Overview
* Example: `for` loops

## Overview

> By minimizing the scope of local variables, you increase the readability and maintainability of your code and reduce the likelihood of error.

* The most powerful technique for minimizing the scope of a local variable is to **decalre it where it is first used**.

* Nearly every local variable declaration **should contain an initializer**.

* **Prefer `for`/for-each loops to `while` loops**, as they allow you to declare *loop variables*, limiting their scope to the exact region where they're needed.

* A final technique is to **keep methods small and focused**.

## Example: `for` loops

Here's the preferred idiom for iterating over a colection ([Item 58](../58)):

```java
// Preferred idiom for iterating over a collection or array
for (Element e : c) {
  ... // Do something with e
}
```

If you need access to the iterator, the preferred idiom uses a traditional `for` loop:

```java
for (Iterator<Element> i = c.iterator(); i.hasNext(); ) {
  Element e = i.next();
  ... // Do something with e and i
}
```

Here is another loop idiom that minimizes the scope of local variables:

```java
for (int i = 0; n = expensiveComputation(); i < n; i++) {
  ... // Do something with i
}
```
