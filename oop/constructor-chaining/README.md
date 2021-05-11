# Construtor chaining

```java
class User {
    public User() {
        // ...
    }

    public User(int id) {
        this();
        this.id = id;
        // ...
    }
}
```

## `super` keyword

* Invokes superclass constructor, e.g., `super()`.
* Must be __first__ statement unless `this()` is used.
* `this()` or `super()`, but never both.
* With overloaded constructors, _last_ invoked will call super.
* If constructor not provided, compiler inserts `super()`.
