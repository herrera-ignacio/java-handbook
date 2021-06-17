# 3: Enforce the singleton property with a private constructor or an enum type

A *singleton* is simply a class that is instantiated exactly once. Their typically represent either a stateless object such as a function ([Item 24](../24)) or a system component that is instrisically unique.

> Making a class a singleton can make it difficult to test its clients because it's impossible to substitute a mock implementation for a singleton unless it implements an interface that serves as its type.

There are two common ways to implement singletons. Both are based on **keeping the constructor private and exporting a public static memeber to provide saccess to the sole instance**

## Approach 1: member as final field

```java
// Singleton with public final field
public class Elvis {
  public static final Elvis INSTANCE = new Elvis();

  private Elvis() { ... }

  public void rockAndRoll() { ... }
}
```

The private constructor is called only once, to initialize the public static final field `Elvis.INSTANCE`. There's one caveat: a privileged client can invoke the private constructor reflectively ([Item 65](../65)) with the aid of the `AccessibleObject.setAccessible` method. If you need to defend against this attack, modify the constructor to make it throw an exception if it's asked to create a second instance.

## Aproach 2: static factory

```java
// Singleton with static factory
public class Elvis {
  private static final Elvis INSTANCE = new Elvis();

  private Elvis() { ... }

  public static Elvis getInstance() { return INSTANCE; }

  public void rockAndRoll() { ... }
}
```

This present the same caveat as the first approach. The main advantage is that the API makes it clear that the class is a singleton, and the second advantage is that it's simplier.

This approach gives you the flexibility to change your mind about whether the class is a singleton without changing its API. The factory method returns the sole instance, but it could be modified to return, say, a separate instance for each thread that invokes it.

Another advantage is that you can write a **generic singleton factory** if your application requires it ([Item 30](../30)).

A final advantage of using a static factory is that a **method reference can be used as a supplier**, for example `Elvis::getInstance` is a `Supplier<Elvis>`.

> Unless one of these advantages is relevant, the public field approach is preferable.

## Approach 1 and 2 *Serializables*

To make a singleton class that uses either of these approaches *serializable*, it is not sufficient merely to add `implements Serializable` to its declaration. To maintain the singleton guarantee, you need to declare all instance fields `transient` and provide a `readResolve` method ([Item 89](../89)). Otherwise, each time a serialized instance is deserialized, a new instance will be created.

```java
public class Elvis {
  public static final transient Elvis INSTANCE = new Elvis();

  private Elvis() { ... }

  // readResolve method to preserve singleton property
  private Object readResolve() {
    // Returon the one true Elvis and let the garbage collector
    // take care of the Elvis impersonator.
    return INSTANCE;
  }

  public void rockAndRoll() { ... }
}

```

## Approach 3 (prefered): Enum

This approach is similar to the public field approach, but it is more concise, provides the serialization machinery for free, and provides an ironclad guarantee against multiple instantiation, even in the face of sophisticated serialization or reflection attacks.

This approach may feel a bit unnatural, but **a single-element enum type is often the best way to implement a singleton**.

```java
public enum Elvis {
  INSTANCE;

  public void rockAndRoll() { ... }
}
```

Note that you can't use this approach if your singleton must extend a superclass other than `Enum` (though you *can* declare an enum to implement interfaces).
