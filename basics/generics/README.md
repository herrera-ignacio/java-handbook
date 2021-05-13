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
