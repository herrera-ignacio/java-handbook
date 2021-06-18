# 7: Eliminate obsolte object references

Garbage collectors can lead to the impression that you don't have to think about memory management.

```java
// Can you spot the "memory leak"?
public class Stack {
  private Object[] elements;
  private int size = 0;
  private static final int DEFAULT_INITIAL_CAPACITY = 16;

  public Stack() {
    elements = new Object[DEFAULT_INITIAL_CAPACITY];
  }

  public void push(Object e) {
    ensureCapacity();
    elements[size++] = e;
  }

  public Object pop() {
    if (size == 0)
      throw new EmptyStackException();
    return elements[--size];
  }

  /**
  Ensure sapce for at least one more element, roughly
  doubling the capacity each time the array needs to grow
  */
  private void ensureCapacity() {
    if (elements.length == size)
      elements = Arrays.copyOf(elements, 2 * size + 1);
  }
}
```

There's nothing obviously wrong with this program. Loosely speaking, the program has a "memory leak", which can silently manifest itself as reduced performance due to increased garbage collector activity or increased memory footprint. In extreme cases, such memory leaks can cause disk paging and even program failure with an `OutOfMemoryError`.

If a stack grows and then shrinks, the objects that were popped of the stack will not be garbage collected, even if the program using the stack has no more references to them. This is because the stack maintains **obsolete references** to these objects.

An obsolte reference is **simply a reference that will never be dereferenced again**. In this case, any references outside the "active portion" of the element array are obsolte. The active portion consists of the elements whose index is less than `size`.

Memory leaks in garbage-collected languages (more properly known as **unintentional object retentions**) are insidious. If an object reference is unintentionally retained, not only is that object excluded from garbage collection, but so too are any objects referenced by that object, and so on.

> Even if only a few object references are unintentionally retained, many, many objects may be prevented from being garbage collected, with potentially large effects on perofrmance.

The fix is simple: **null out references once they become obsolete**.

In the case of our `Stack` class, this happens as soon as an object is popped off the stack.

```java
public Object pop() {
  if (size == 0)
    throw new EmptyStackException();
  Object result = elements[--size];
  elements[size] = null; // Eliminate obsolote reference
  return result;
}
```

An added benefit of nulling out obsolete references is that if they are subsequently dereferenced by mistake, the program will immediately fail with a `NullPointerException`, rather than quietly doing the wrong thing.

**Nulling out object references should be the exception rather than the norm**. The best way to eliminate an obsolte reference is to let the variable that contained the reference fall out of scope. This occurs naturally if you **define each variable in the narrowest possible scope** ([Item 57](../57)).
