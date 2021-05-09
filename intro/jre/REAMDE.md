# JRE: Java Runtime Environment

> The __Java Runtime Environment (JRE)__ is a software layer that runs on top of a computer's operating system software and provides the class libraries__ and other resources that a specific Java program needs to run.

* Ecosystem
    * Deployment solutions
    * Deployment toolkits
    * Integration libraries
    * Utility libraries
* JRE Architecture
    * ClassLoader
    * Bytecode Verifier
    * Interpreter

## Ecosystem

JRE is one of the three interrelated components for developing and running Java programs. The other two components are:

* __Java Development Kit (JDK)__: Set of tools for developing Java applications. Developers choose JDKs by Java version and by package or edition, Java EE, SE, ME. Every JDK always includes a compatible JRE.

* __Java Virtual Machine (JVM)__: Executes live Java applications. Every JRE includes a default JVM, but developers are free to choose another that meets the specific resource needs of their applications.

> The JRE combines Java code created using the JDK with the necessary libraries required to run it on a JVM and then creates an instance of the JSM that executes the resulting program.

## JRE architecture

JDK and JRE interact with one another to create a sustainable runtine environment that enables the seamless execution of Java-based applications in virtually any operating system.

### ClassLoader

The Java __ClassLoader dynamically loads all classes necessary to run a Java program__. Since Java classes are only loaded into memory when they're required, the JRE uses ClassLoaders to automate this process on demand.

### Bytecode verifier

The __bytecode verifier ensures the format and accuracy of Java code before it passes to the interpreter__. In the event that code violates the system integrity or access rights, the class will be considered corrupted and won't be loaded.

### Interpreter

After the bytecode successfully loads, the Java __interpreter creates an instance of the JVM__ that allows the Java program to be executed natively on the underlying machine.

## Ecosystem

Besides the JVM, JRE is composed of a variety of other supporting software tools and features to get the most out of your Java applications.

### Deployment Solutions

Included as part of the JRE installation are deployment technologies like __Java Web Start__ and __Java Plugin__  that simplify the activation of applications and provide advanced support for future Java updates.

### Development Toolkits

JRE also contains toolkits designed to help developers improve their user interface. Some of these toolkits include:

* __Java 2D__: API used for drawing two-dimensional graphics in Java language. Developers can create rich user interfaces, special effects, game, and animations.

* __Abstract Window Toolkit (AWT)__: A GUI used to create objects, buttons, scroll bars, and windows.

* __Swing__: Another lightweight UI that uses a rich set of widgets to offer flexible, user-friendly customizations.

### Integration Libraries

JRE provides a number of integration libraries to __assist developers in creating seamless data connections between their applications and services__. Some of these libraries include:

* __Java IDL (CORBA)__: Uses Comment Object Request Architecture to support distributed objects.

* __Java Database Connectivity (JDBC)__: Provides tool for developers to write applications with access to remote relationship databases, flat files, and spreadsheets.

* __Java Naming and Directory Interface (JNDI)__: API and directory service that lets clients create portable applications that can fetch information from databases using naming conventions.

### Utility Libraries

Included with the JRE are `java.lang`, and `java.util`, packages that are fundamental for the design of Java applications, package versioning, management, and monitoring. Some of these packages include:

* __Collections Framework__: A unified architecture made up of a collection of interfaces designed to improve the storage and process of application data.

* __Concurrency Utilities__: A powerful framework package with high-performance threading utilities.

* __Preferences API__: A lightweight, cross-platform persistent API that enables multiple users on the same machine to define their own group of application preferences.

* __Logging__: Produces log reports fur further analysis, such as security failrues, configuration errors, and performance issues.

* __Java Archive (JAR)__: A platform-independent file format that enables multiple files to be bundled in JAR format, significantly improving download speed and reducing file size.
