# Interface

Interface allows to define non-instantiable __mostly only public abstract methods (no state)__, this way defines a __pure contract__.

```java
interface InterfaceName {
    static final fields;
    abstract methods;
    default methods;
    static methods;
    // Nested types
}
```

* `public` & `abstract` by default.
* Variables are `public`, `static` & `final` by default.
* All members are `public` by default (can't be privated or protected).

> In Java, there's no multiple inheritance, but you can inherit from a concrete class and implement an interface.

```java
public class Guitar extends Instrument implements StringInstrument {
    // ...
}

public class Violin extends Instrument implements StringInstrument {
    // ...
}
```

## Marker Interfaces

Interfaces with __no methods__, that merely "_marks_" a class as having some property.

```java
class CloneableClass implements Cloneable {
    public CloneableClass clone() {
        try {
            return (CloneableClass) super.clone();
        } catch(CloneNotSupportedException e) {}

        return null;
    }
}
```

## Static Methods

In addition to default methods, you can define `static` methods in interfaces. This makes it easier for you to organize helper methods in your libraries; you can keep static methods specific to an interface in the same interface rather than in a separate class.
