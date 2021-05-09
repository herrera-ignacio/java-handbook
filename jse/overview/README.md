# Java Platform SE

* Definition
* General Purpose Packages
    * java.lang
    * java.lang.ref
    * java.io
    * java.nio
    * java.math
    * java.net
    * java.text
    * java.util
* Special Purpose Packages
  * java.applet
  * java.beans
  * java.awt
  * java.rmi
  * java.security
  * java.sql
  * javax.rmi
  * javax.swing

## Definition

_Java SE_ is a computing platform for development and deployment of portable code for desktop and server environments.

> Java SE was formerly known as __Java 2 Platform, Standard Edition (J2SE)__.

The platform uses Java programming language and is part of the Java software-platform family. Java SE defines a range of general-purpose APIs (such as Java APIs for the _Java Class Library_) and also includes the _Java Language Specification_ and _Java Virtual Machine Specification_.

__OpenJDK__ is the reference implementation since version 7.

## General Purpose Packages

### `java.lang`

> Classes in `java.lang` are automatically imported into every source file.

Contains fundamental classes and interfaces closely related to the language and runtime system.

* `Object`: root of every class hierarchy.
* `Enum`
* `Class`
* `Throwable`
* `Error`, `Exception` and `RuntimeException`
* `Thread`
* `String`
* `StringBuffer` and `StringBuilder`
* `Comparable`
* `Iterable`
* `ClassLoader`, `Process`, `Runtime`, `SecurityManager` and `System`.
* `Math` and `StrictMath`
* Primitive wrapper classes that encapsulate primitive types as objects.
* Basic exception classes thrown for language-level and other common exceptions.

### `java.lang.ref`

Provides more expressive and flexible system of of references than the otherwise available, permitting special behavior for garbage collection. A normal reference in Java is known as a "_strong reference_". This package defines three other types of references:

* `SoftReference`: can be used to implement a cache.
* `WeakReference`: used to implement weak maps.
* `PhanthomReference`: used to reference objects that have been marked for garbage collection and have been finalized, but have not yet been reclaimed.

It also defines the class `ReferenceQueue`, which can be used to keep track of objects that have changed reference type. When a `Reference` is created it is optionally registered with a reference queue. The application polls the reference queue to get references that have changed reachability state.

### `java.lang.reflect`

__Reflection__ is one of the building blocks of the Java API and lets Java code examine and "reflect" on Java components at runtime and use the reflected members.

Classes in the `java.lang.reflect` package, along with `java.lang.Class` and `java.lang.Package` accommodate applications such as debuggers, interpreters, object inspectors, class browsers, and services such as object serialization and JavaBeans that need access to either public members of a target object or the members declared by a given class.

Reflection is used to instantiate classes and invoke methods using their names, a concept that allows for dynamic programming. Classes, interfaces, methods, fields, and constructors can all be discovered at runtime.

### `java.io`

The `java.io` package contains classes that support __input and output__. The classes in the package are primarily __stream-oriented__; however, a class for _random access files_ is also provided.

The central classes are `InputStream` and `OutputStream`, which are abstract base classes for reading from and writing to byte streams, respectively. The related classes `Reader` and `Writer` are abstract classes for reading from and writing to character streams respectively. The package also has a few miscellaneous classes to support interactions with the host file system.

### `java.nio`

The package for _non-blocking I/O_ was added to __support memory-mapped I/O__, facilitating I/O operations closer to the underlying hardware with sometimes dramatically better performance.

This package provides support for a number of buffer types, for example `java.nio.charset` for different character encodings for character data, and `java.nio.channels` for _channels_ which represent connections to entities that are capable of performing I/O operations, such as files and sockets.

### `java.math`

Supports __multiprecision arithmetic__ (including modular arithmetic operations) and provdes __multiprecision prime number generators used for cryptographic key generation__.

The main classes are:

* `BigDecimal`
* `BigInteger`
* `MathContext`
* `RoundingMode`

### `java.net`

Provides special IO routines for networks, allowing HTTP requests, as well as other common transactions.

### `java.text`

Implements parsing routines for strings and supports various human-readable languages and locale-specific parsing.

### `java.util`

Data structrures that aggregate objects are the focus of the `java.util` package. Included in the package is the _Collectiosn API_, an organized data structure hierarchy influenced heavily by the design patterns considerations.
