# Data Structures

* Interfaces
  * Collection
  * List
  * Iterable
  * Queue
  * Dequeue
* ArrayList
* LinkedList
* ArrayDeque

## Interfaces

### Collection

```java
public interface Collection<E> extends Iterable<E> {
    /* Basic ops */
    boolean add(E element); // optional
    boolean remove(object element); // optional
    boolean contains(Object element);
    int size();
    boolean isEmpty();
    Iterator<E> iterator();

    /* Bulk ops */
    boolean addAll(Collection<? extends E> c); // optional
    boolean removeAll(Collection<?> c); // optional
    boolean retainAll(Collection<?> c); // optional
    boolean containsAll(Collection<?> c);
    void clear(); // optional

    /* Array */
    Object[] toArray();
    <T> T[] toArray(T[] a); // e.g., String[] a = c.toArray(new String[0]);
}
```

### List

* Useful when __sequence/positioning__ matters.
* Models __resizable linear array__ with indexed access.
* Zero-based.
* Can have duplicates.

```java
public interface List<E> extends Collection<E> {
    /* Positional */
    E get(int index);
    E set(int index, E element); // optional
    void add(int index, E element); // optional
    boolean add(E element); // optional
    E remove(int index); // optional
    boolean addAll(int index, Collection<? extends E> c); // optional

    /* Search */
    int indexOf(Object o);
    int lastIndexOf(Object o);

    /* Iteration */
    ListIterator<E> listIterator();
    ListIterator<E> listIterator(int index);

    /* Range view */
    List<E> subList(int fromIndex, int toIndex);

}
```

### Iterable & Iterator

Iterator allows for __element removal during iteration__.

```java
public interface Iterable<T> {
    Iterator<T> iterator();
    default void forEach(Consumer<? super T> action);
    default Spliterator<T> spliterator();
}

public interface Iterator<E> {
    boolean hasNext();
    E next();
    void remove();
    default void forEachRemaining(Consumer<? super E> action);
}
```

### Queue (FIFO)

Useful when manipulating _head_ (delete with `remove()` or `poll()`/inspect with `element()` or `peek()`) & _tail_ (`add(e)`).

### Dequeue (FIFO & LIFO): Double-Ended Queue

* Extends Queue
* `ArrayDeque`, `LinkedList`, `ConcurrentLinkedDequeue`, `LinkedBlockingDeque`.

---

## Array List

A resizable __array implementation of a List interface__.

* Default capacity 10, increases by 50%.
* Allows duplicates & nulls.
* Fast random access, appending elements and deleting last element ~ O(1).
* `size()`, `isEmpty()`, `set()`, `get()`, `iterator()` and `listIterator()` are O(1).
* `add(index, element)`: following elements _shifted right_ by one position ~ O(n).
* `remove(index)`: following elements _shifted left_ by one position ~ O(n).
* `removeAll(collection)` and `retainAll()`: O(n^2) (traversing list + `contains()` call + element shift on remove).

## Linked List

Useful for __frequent add/remove during iteration__.

`java.util.LinkedList` is an implementation of `List` & `Deque` interfaces.

* Allows duplicates & nulls.
* Better for `removeAll()` & `retainAll()`.
* `get(i)`, `add(i, e)`, `remove(i)`, `indexOf(Object)` and `lastIndexOf(object)` are O(n).
* LIFO & FIFO operations in O(1)

## `ArrayDeque`

* Resizable __array implementation of `Deque` interface__.
* Does not implement `List` and nulls are prohibited.
* In case of `Deque` prefer `ArrayDeque` over `LinkedList` as it's considered faster.
* Most methods run in __amortized constant time__.
* `remove`, `removeFirstOccurrence`, `removeLastOccurrence` and `contains` run in O(n).