# 7: Eliminate obsolte object references

Garbage collectors can lead to the impression that you don't have to think about memory management.

## Case Study: Classes

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

You should null out references when you have a class that *manages its own memory*. In our `Stack` class, the *storage pool* consists of the elements of the `elements` array (the object reference cells, not the objects themselves). The elements in the active oportio nof the array are *allocated*, and those in the remainder are *free*. The garbage collector has no way of knowing this, only the programmer knows thne inactive portion of the array is unimportant.

## Case Study: Caches

Generally speaking, **whenever a class manages its own memory, the programmer should be alert for memory leaks**.

Another **common source of memory leaks is caches**. Once you put an object reference int oa cache, it's easy to forget that it's here and leave it in the cache long after it becomes irrelevant.

If you are lucky enough to implement a cache for which an entry is relevant exactly so as long as there are references to its key outside of the cache, represent the cache as a `WeakHashMap`; entries will be removed automatically after they become obsolete.

more commonly, the useful lifetime of a cache entry is less well defined. Under these circumstances, the cache should **ocasionally be cleansed** of entries that have fallen into disuse. This can be done by a background thread (perhaps a `ScheduledThreadPoolExecutor`) or as a side effect of adding new entries to the cache. The `LinkedHashMap` class facilitates the latter approach with its `removeEldestEntry` method. For more sophisticated caches, you may need to use `java.lang.ref` directly.

## Case Study: Listeners/Callbacks

If you implement an API where clients register callbacks but don't deregister them explicitly, they wil accumulate unless you take some action.

One way to ensure that callbacks are garbage collected promptly is to store only *weak references* to them, for instance, by storing themm only as keys in a `WeakHashMap`.

> Memory leaks typically do not manifest themselves as obvious failures, they are typically discovered only as a result of careful code inspection or with the aid of *heap profilers*. Therefore, it is very desirable to learn to anticipate problems like this before they occur.
