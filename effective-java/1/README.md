# 1: Consider static factory methods instead of constructors

A class can provide its clients with static factory methods instead of, or in addition to, public constructors.

```java
public static Boolean valueOf(boolean b) {
  return b ? Boolean.TRUE : Boolean.FALSE;
}
```

> Note that a static factory method is not the same as the *Factory Method* pattern from *Design Patterns*.

## Advantages

1. Unlike constructors, **they have names**. It's easier to read and use.

2. Unlike constructors, **they are not required to create a new object each time they're invoked**. This allows classes to maintain strict control over what instances exist at any time (*instance-controlled classes*).

3. Unlike constructors, **they can return an object of any subtype of their return type**. You could return objects without making their classes public, hiding implementation classes and leading to *interface-based* frameworks and very compact APIs. 

4. **Class of the returned object can vary from call to call as a function of the input parameters**. Clients neither know nor care about the class of the object they get back from the factory, they only care that it is some subclass.

5. **Class of returned object need not exist when the class containing the method is written**. Such flexible static factory methods form the basis of *service provider frameworks* like JDBC, which providers implement a service, and the system makes the implementations available to clients, decoupling clients from the implementations.

## Disadvantages

1. The main limitation is that **classes without public or protected constructors cannot be subclassed**.

2. **Hard for programmers to find**. They do not stand out in API documentation in the way that constructors do.

## Conventions

Some common names for static factory methods:

* **from**: A *type-conversion method* that takes a single parameter and returns a corresponding instance of this type.

```java
Date d = Date.from(instant);
```

* **of**: An *aggregation method* that takes multiple parameters and returns an instance of this type that incorporates them.

```java
Set<Rank> faceCards = EnumSet.of(JACK, QUEEN, KING);
```

* **valueOf**: A more verbose alternative to `from` and `of`.

```java
BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);
```

* **instance** or **getInstance**: Returns an instance that is described by its parameters (if any) but cannot be said to have the same value.

```java
StackWalker luke = StackWalker.getInstance(options);
```

* **create** or **newInstance**: Like `instance` or `getInstance`, except that the method guarantees that each call returns a new instance.

```java
Object newArray = Array.newInstance(classObject, arrayLen);
```

* **getType**: Like `getInstance`, but used if the factory method is in a different class. `Type` is the type of object returned by the factory method.

```java
FileStore fs = Files.getFileStory(path);
```

* **newType**: Like `newInstance`, but used if the factory method is in a different class.

```java
BufferedReader br = Files.newBufferedReader(path);
```

* **type**: A concise alternative to `getType` and `newType`.

```java
List<Complaint> litany = Collections.list(legacyLitany);
```
