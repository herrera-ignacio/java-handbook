# Generics

## Example

```java
class Store<T> {
    private T a;

    public void set(T a) { this.a = a; }

    public T get() { return a; }
}

Store<String> stringStore = new Store<String>();
Store<List<Date>> dateListStore = new Store<List<Date>>();
```

## Conventions

* `E`: Element (Collections)
* `K`/`V`: Key/Value (Maps)
* `N`: Number
* `T`: Type (usually non-collections)
* `S`,`U`,`V`: more types.

## Restrictions

* Type argument cannot be primitive (`Store<int>` would be invalid).
* Type parameter cannot be used in static context.
* Cannot overload methods that have same signature after type erasure.
* Exceptions & Error Types cannot be generic.

## Bounded Types

> Can access methods defined by bounds

```java
class GenericsBounded<T extends List & ...> {}
```

## Unbounded Type

* Can only be used as a __type argument__.
* Cannot invoke methods that use class-level type parameters with any arguments except null (for example `List.add`).

```java
void demo(Store<?> someStore) {}
```

## Generic Methods

Generic return types can also be used as type parameters.

```java
<T1, T2, ...> returnType methodName(T1 p1, T2 p2, ...) {}
```

Some examples:

```java
<T> T[] toArray(T[] a); // java.util.Collection

static <T> boolean replaceAll(List<T> list, T oldVal, T newVal) { ... }
```
