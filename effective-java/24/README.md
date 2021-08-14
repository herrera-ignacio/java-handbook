# 24: Favor static members classes over nonstatic

> A *nested class* is a class defined within another class. A nested class should exist only to serve its enclosing class. There are four kinds of nested classes: static member, nonstatic member, anonymous, and local. All but the first kind are known as *inner classes*.

## Static member class

* A *static member class* is the simplest kind of nested class. It is best though of as an ordinary class that happens to be declared inside another class and has access to all of the enclosing class's members, even those declared private.

* A *static member class* can be used as a **public helper class**.

## Nonstatic member class

* Each instance of a *nonstatic member class* is implicitly associated with an *enclosing instance* of its containing class. Within the instance methods of a nonstatic member class, you can invoke methods on the enclosing instance or obtain a reference to it using the *qualified this* construct. If an instance of a nested class can exist in isolation from an instance of its enclosing class, then the nested class *must* be a *static member class*: it is impossible to create an instance of a *nonstatic member class* without an enclosing instance.

* One common use is to define an *Adapter* that allows an instance of the outer class to be viewed as an instance of some unrelated class.

## Anonymous class

* It is simultaneously declared and instantiated at the point of use.

* They have an enclosing instance if and only if they ouccur in a nonstatic context.

* They cannot have any static members other than *constant variables*, which are final primitive or string fields initialized to constant expressions.

* Before lambdas were added, anonymous classes were the preferred means of creating small *function objects* and *process objects* on the fly, but lambas are now preferred ([Item 42](../42)).

* Another common use is in the implementation of *static factory methods*.

## Local classes

* Can be declared practically anywhere a local variable can be declared and obeys the same scoping rules.

* Like anonymous classes, they have enclosing instances only if they are defined in a nonstatic context, and they cannot contain static members.

* assuming the class belongs inside a method, if you need to create instances from only one location and there is a preexisting type that characterizes the class, make it an anonymous class; otherwise, make it a local class.
