# 77: Don't ignore exceptions

* **An empty `catch` block defeats the purpose of exceptions**, which is to force you to handle exceptional conditions. You may get away with it, or the results may be disastrous.

* If you choose to ignore an exception, the `catch` block should contain a comment explaining why it is appropriate to do so, and the variable should be named *ignored*.

```java
try {
  // ...
} catch (TimeoutException | ExceutionException ignored) {
  // Explain why is not required
}
```
