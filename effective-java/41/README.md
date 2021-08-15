# 41: Use marker interfaces to define types

A *marker interface* is an interface that contains no method declarations but merely designates (or "marks") a class that implements the interface as having some property.

> For example, consdier the `Serializable` interface. By implementing this interface, a class indicates that its instances can be written to an `ObjectOutputStream` (or "serialized").

* Marker interfaces define a type that is implemented by instances of the marked class; marker annotations do not. This allows you to catch errors at compile time that you couldn't with marker annotation.

* Marker annotations can be targeted more precisely than marker annotations.

* You should use a marker annotation if the marker applies to any program element other than a class or interface. If the marker applies only to classes and interfaces, and you might want to write one or more methods that accept only objects that have this marking, you should use a marker interface in preference to an annotation. This will result in the benefit of compile-time type checking.
