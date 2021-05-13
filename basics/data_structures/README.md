# Data Structures

* Collection Interfaces
  * List
  * Iterable
  * Queue
  * Deque
  * Set
  * SortedSet
  * NavigableSet
* Map Interfaces
  * SortedMap
  * NavigableMap
* Implementations
  * ArrayList
  * LinkedList
  * ArrayDeque
  * Hash Table
  * HashSet
  * TreeSet
  * HashMap
  * LinkedHashMap
  * TreeMap
  * Collections

## Collection Interfaces

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

### Deque (FIFO & LIFO): Double-Ended Queue

* Extends Queue
* `ArrayDeque`, `LinkedList`, `ConcurrentLinkedDequeue`, `LinkedBlockingDeque`.

### Set

* Models mathematical set, i.e, __no duplicates__.
* Useful when __uniqueness__ & __fast lookup__ matters.
* `SortedSet` provides sorting.

### SortedSet

```java
public interface SortedSet<E> extends Set<E> {
    /* Range view */
    SortedSet<E> subSet(E fromElement, E toElement);
    SortedSet<E> headSet(E toElement);
    SortedSet<E> tailSet(E fromElement);

    /* Endpoints */
    E first();
    E last();

    /* Comparator access */
    Comparator<? super E> comparator();
    default Spliterator<E> spliterator();
}
```

### NavigableSet

```java
public interface NavigableSet<E> extends SortedSet<E> {
    /* Closest matches */
    E lower(E);
    E floor(E);
    E ceiling (E);
    E higher(E);

    /* Iterators */
    Iterator<E> iterator() & descendingIterator();
    NavigableSet<E> descendingSet();

    /* Endpoints */
    E pollFirst() & pollLast();

    /* Range View */
    NavigableSet<E> headSet(E toElement, boolean inclusive);
}

```

---

## Map Interfaces

* When __fast lookup by key__ matters, e.g., data cache.
* Also called __associative array__.
* No duplicate keys, duplicate values are fine.

```java
public interface Map<K, V> {
    /* Basic ops */
    V put(K key, V value);
    V get(Object key);
    V remove(Object key);
    V remove(Object key);
    boolean containsKey(Object key);
    boolean containsValue(Object value);
    int size();
    boolean isEmpty();

    /* Collection View */
    public interface Map<K, V> {
        Set<K> keySet();
        Collection<V> values();
        Set<Map.Entry<K,V>> entrySet();
        
        public interface Entry {
            K getKey();
            V getValue();
            V setValue(V value);
        }
    }
}
```

### `SortedMap`

* Map capabilities + data __sorted by keys__.
* Natural ordering or Comparator by keys.

### NavigableMap

Provides "closest matches" methods, as well as iterators and range view methods.

---

## Implementations

### Array

* `java.util.Array` contains various methods for manipualting arrays (such as sorting and searching).
* Contains a static factory that allows arrays to be viewed as lists (`new ArrayList(Arrays.asList(Array arr))`).
* In Java 8, a Stream ccan be parallel, so the operations are automatically done in parallel and the methods `stream()` and `parallelStream()` have been written in Collection interface so `Collection.parallelStream()` returns parallel stream instance for calling collection.

### ArrayList

A resizable __array implementation of a List interface__.

* Default capacity 10, increases by 50%.
* Allows duplicates & nulls.
* Fast random access, appending elements and deleting last element ~ O(1).
* `size()`, `isEmpty()`, `set()`, `get()`, `iterator()` and `listIterator()` are O(1).
* `add(index, element)`: following elements _shifted right_ by one position ~ O(n).
* `remove(index)`: following elements _shifted left_ by one position ~ O(n).
* `removeAll(collection)` and `retainAll()`: O(n^2) (traversing list + `contains()` call + element shift on remove).

### Linked List

Useful for __frequent add/remove during iteration__.

`java.util.LinkedList` is an implementation of `List` & `Deque` interfaces.

* Allows duplicates & nulls.
* Better for `removeAll()` & `retainAll()`.
* `get(i)`, `add(i, e)`, `remove(i)`, `indexOf(Object)` and `lastIndexOf(object)` are O(n).
* LIFO & FIFO operations in O(1)

### `ArrayDeque`

* Resizable __array implementation of `Deque` interface__.
* Does not implement `List` and nulls are prohibited.
* In case of `Deque` prefer `ArrayDeque` over `LinkedList` as it's considered faster.
* Most methods run in __amortized constant time__.
* `remove`, `removeFirstOccurrence`, `removeLastOccurrence` and `contains` run in O(n).

### Hash Table

* Implements an __associative array__ (`<key, value>`).
* Duplicate values is fine but __no duplicate keys__.

### `HashSet`

* Hash table implementation of `Set` interface.
* Internally uses `HashMap`.
* Rapid lookup, insertion, and deletion ~ O(1).
* Insertion order is not important.
* Better for `removeAll()` & `retainAll()`.

### `LinkedHashSet`

_Hash table_ & _linked list_ implementation of _Set_ interface.

* __Preserves insertion order__ (_doubly linked list_) and has __faster iteration__.
* Extends `HashSet` (nearly as fast too), rapid lookup, insertion and deletion ~ O(1).
* Permits one `null` element.
* Internally uses `LinkedHashMap`.

### `TreeSet`

Red-black tree-based implementation of `NavigableSet` interface.

Internally uses `TreeMap`.

### `HashMap`

* O(1) for put, get and remove.
* Insertion order is not preserved.
* Permits `null` values and one `null` key.
* Not synchronized unlike Hashtable.
* Beware of mutable keys.

### `LinkedHashMap`

Similar to `HashMap` but __preserves order__ and __has faster iteration__.

### `TreeMap`

Red-Black tree based `NavigableMap` implementation.

* Map is sorted according to naturla ordering of its keys, or by a Comparator provided at map creation time.
* Provides `log(n)` time for cost for the containsKey, get, put, and remove operations.

### `Collections`

This class consists exclusively of __static methods that operate on or return collections__.

It contains polymorphic algorithms that operate on collections, "wrappers", which return a new collection backed by a specified collection, and a few other odds and ends.

> [Docs](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Collections.html)
