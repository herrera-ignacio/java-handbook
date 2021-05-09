# Platform

> https://en.wikipedia.org/wiki/Java_(software_platform)

* Platform
* JVM
* Class Libraries
* Languages
* History

## Platform

The Java platform is a __suite of programs that facilitate developing and running programs written in the Java programming language__. 

A Java platform includes:

* Execution engine/runtime environment (_virtual machine_)
* Compiler
* Set of libraries
* (Optional) Additional servers and alternative libraries.

Java platforms have been implemented for a wide variety of hardware and operating systems with a view to enable Java programs to run identically on all of them. Different platforms target different classes of devices and application domains:

* __Java Card__: Technology that allows small Java-based applications (_Applets_) to be run securely on smart cards and similar small-memory devices.

* __Java ME__ (Micro Edition): Specifies several different sets of libraries (known as profiles) for devices with limited storage, display, and power capacities. It is often used to develop applications for mobile devices, PDAs, TV set-top boxes, and printers.

* __Java SE (Standard Edition)__: For general-purpose use on desktop PCs, servers and similar devices.

* __Jakarta EE (Enterprise Edition)__: Java SE plus various APIs which are useful for mutlti-tier client-server enterprise applications.

> The Java platform consists of several programs, each of which provides a portion of its overall capabilities. For example, the Java compiler, which converts Java source code into Java bytecode (an intermediate language for the JVM), is provided as part of the JDK. The JRE complementing the JVM with a JIT compiler, converts intermediate bytecode into native machine code on the fly.

## JVM

The heart of the Java platform is the "virtual machine" that executes Java bytecode programs. This bytecode is the same no matter what hardware or operating system the program is running under. However, new versions, such as for Java 10, have made small changes, meaning the __bytecode is general only forward compatible__.

Since JRE 1.2, there is a __JIT (Just In Time) compiler within the JVM__. The JIT compiler translates the Java bytecode into native processor instructions at run-time and caches the native code in memory during execution.

The use of bytecode as an intermediate language permits Java programs to run on any platform that has a virtual machine available. The use of a JIT compiler means that Java applications, after a short delay during loading and once they have "warmed up" by being all or mostly JIT-compiled, tend to run about as fast as native programs.

## Class Libraries

In most modern OSs, a large body of reusable code is provided to simplify the programmer's job. This code is typically provided as a set of __dynamically loadable libraries__ that applications can call at runtime. Because Java platform is not dependent on any specific OS, applications cannot rely on any of the pre-existing OS libraries. Instead, the __Java platform provides a comprehensive set of its own standard class libraries__ containing many of the same reusable functions commonly found in modern OS. __Most of the system library is also written in Java__.

> For instance, the _Swing_ library paints the user interface and handles the events itself, eliminating many subtle differences between how different platforms handle components.

The Java class libraries serve three purposes within the Java platform:

1. __Provide a well-known set of functions to perform common tasks__.

2. __Provide an abstract interface to tasks that would normally depend heavily on the hardware and OS__. Tasks such as network access and file access are often heavily intertwined with the distinctive implementations of each platform. The `java.net` and `java.io` libraries implement an abstraction layer in native OS code, and provide a standard intetrface for the Java applications.

3. __When some underlying platform does not support all of the features a Java application expects, the class libraries work to gracefully handle the absent components__, either by emulation to provide a substitute, or at least by providing a consistent way to check for the presence of a specific feature.

## Languages

The word "Java", alone, usually refers to Java programming language that was designed for use with the Java platform. Programming languages are typically outside of the scope of the phrase "platform". An effort was made with the Java 7 specification to more clearly __treat the Java language and the Java Virtual Machine as separate entities__.

Third parties have produced many compilers or interpreters that target the JVM. Some of these are for existing languages, while others are extensions to the Java language (such as _Kotlin_, _Scala_, _Jython_, _Jruby_, _Clojure_).

## History

> https://en.wikipedia.org/wiki/Java_(software_platform)
