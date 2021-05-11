# Inheritance

> Classes can be derived from classes, and so on, and ultimately derived from the topmost class (`Object` in Java). Such a class is said to be _descended_ from all the classes in the inheritance chain.

The idea of inheritance is simple but powerful: When you want to create a new class and there is already a class that includes some of the code that you want, you can derive your new class from the existing class. In doing this, you can reuse the fields and methods of the existing class witohut having to write (and debug) them by yourself.

A _subclass_ inheritas all the _members_ (fields, methods, and nested classes) from it superclass. Constructors are not members, so they are not inherited by subclasses, but the constructor of the sueprclass can be invoked from the subclass.

> Except for the `Object` class, a class has exactly one direct superclass.

A class inherits fields and methods from all its superclasses, whether direct or indirect. A subclass can override methods that it inherits, or it can hide fields or methods that it inherits.

> The `Object` class is the top of the class hierarchy. All classes are descendants from this class and inherit methods from it. Useful methods inherited from `Object` include: `toString()`, `equals()`, `clone()`, and `getClass()`.

You can prevent a class from being subclassed by using the `final` keyword in the class's declaration. Similarly, you can prevent a method from being overriden by subclasses by declaring it as a `final` method.

## Multiple Inheritance

Java programming language __does not permit you to extend more than one class__ to avoid the issue of _multiple inheritance of state_, which is the ability to inherit fields from multiple classes.

> What if methods or constructors form different superclasses instantiate the same field? Which method or constructor will take precedence?

Java programming language __supports multiple inheritance of type__, which is the ability of a class to implement more than one interface.

Because interfaces do not contain fields, you do not have to worry about problems that result from multiple inheritance of state. Default methods introduce one form of multiple inheritance of implementation. A class can implement more than one interface, which can contain default methods that have the same name. The Java compiler provides some rules to determine which default method a particular class uses.

> _Multiple inheritance of implementation_ is the ability to inherit method definitions from multiple classes. Problems arise with this type of multiple inheritance, such as name conflicts and ambiguity. In addition, a programmer can unwittingly introduce a name conflict by adding a new method to a super class.
