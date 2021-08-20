# 83: Use lazy initialization judiciously

> *Lazy initialization* is the act of delaying the initialization of a field until its value is needed. if the value is never needed, the field is never initialized.

* While lazy initialization is primarily an optimization, it can also be used to break harmful circularities in class and instance intialization.

* Don't do it unless you need to ([Item 67](../67)).

* It is a **double-edged sword**. It decreases the cost of initializing a class or creating an instance, at the expense of increasing the cost of accessing the lazily intialized field. Depending on what fraction of these fields eventually require initialization, how expensive it is to initialize them, and how often each one is accessed once initialized, lazy initialization can actually harm performance.

* In the presence of multiple threads, lazy initialization is tricky. It is critical that some form of synchronization be employed.

* Under most circumstances, normal initialization is preferable to lazy initialization.

* If you need to use lazy initialization for performance on a static field, ujse the *lazy initialization holder class idiom*.

```java
// Lazy initialization holder class idiom for static fields
private static class FieldHolder {
  static final FieldType field = computeFieldValue();
}

private static FieldType getField() { return FieldHolder.field; }
```

* If you need to use lazy initialization for performance on an instance field, use the *double-check idiom*.

```java
// Double-check idiom for lazy initialization of instance fields
private volatile FieldType field;

private FieldType getField() {
  FieldType result = field;

  if (result != null) // First check (no locking)
    return result;

  synchronized(this) {
    if (field == null) // Second check (with locking)
      field = computeFieldValue();
    return field;
  }
}

// Single check idiom - can cause repeated initialization!
private volatile FieldType field;

private FieldType getField() {
  FieldType result = field;
  if (result == null)
    field = result = computeFieldValue();
  return result;
}
```
