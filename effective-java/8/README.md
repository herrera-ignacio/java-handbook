# 8: Avoid finalizers and cleaners

* Overview
* Problems
  * Never do anything time-critical in a finalizer or cleaner
  * Never depend on a finalizer or cleaner to update persistent state
  * There is a *severe* performance penalty for using finalizers and cleaners
  * Finalizers open your class up to *finalizer attacks*
* Alternative
* Legitimate Uses
  * Safety net
  * Objects with *native peers*
* Case Study

## Overview

> "In summary, don't uyse cleaners, or in releases prior to Java 9, finalizers, except as a safety net or to terminate noncritical native resources. Even then, beware the indeterminacy and performance consequences." - Joshua Bloch, Effective Java

**Finalizers are unpredictable, often dangerous, and generally unnecessary**. Their use can cause erratic behavior, poor performance, and portability problems. **Cleaners are less dangerous but still unpredictable, slow, and generally unnecessary**.

> As of Java 9, finalizers have been deprecated, but they are still being used by the Java libraries. The Java 9 replacement for finalizers is *cleaners*. Finalizers have a few valid users, but as a rule, you should avoid them.

In C++, destructors are the normal way to reclaim the resources associated with an object, a necessary counterpart to constructors. In Java, the **garbage collector reclaims the storage associated with an object when it becomes unreachable**, requiring no special effort on the part of the programmer..

C++ destructors are also used to reclaim other nonmemory resources. In Java, a `try-with-resources` or `try-finally` block is used for this purpose.

## Problems

### Never do anything time-critical in a finalizer or cleaner

There is no guarantee they'll be execulted promptly. It can take arbitrarily long between the time that an object becomes unreachable and the time its finalizer or cleaner runs.

> For example, it is a grave error to depend on a finalizer or cleaner to close files because open file descriptors are a limnited resource. If many files are left open as a result of the system's tardiness in running finalizers/cleaners, a program may fail because it can no longer open files.

The promptness is primarily a function of the garbage collection algorithm, which varies widely across implementations. 

### Never depend on a finalizer or cleaner to update persistent state

It is entirely possible, even likely, that a program terminates without running them on some objects that are no longer reachable.

> For example, depending on a finalizer or cleaner to release a persistent lock on a shared resource such as a database is a big no-no.

`System.gc` and `System.runFinalization` may increase the odds of finalizers/cleaners getting executed,  but they don't guarantee it.

Another problem with *finalizers* is that an uncaught exception thrown during finalization is ignored, and finalization of that object terminates. Uncaught exceptions can leave other objects in a corrupt state. If another thread attempts to use such a corrupted object, arbitrary nondeterministic behavior may result. Normally, an uncaught exception will terminate the thread and print a stack trace, but not if it occurs in a finalizer, it won't even print a warning. *Cleaners* do not have this problem because a library using a cleaner has control over its thread.

### There is a *severe* performance penalty for using finalizers and cleaners

*Finalizers* inhibit efficient garbage collection. *Cleaners* are comparable in speed to *finalizers* if you use them to clean all instances of the class, but *cleaners* are much faster if you use them only as a safety net, but you still ned to pay in performance for the safety net if you don't use it.

### Finalizers open your class up to *finalizer attacks*

*Finalizers* have a **serious security problem**: they open your class up to *finalizer attacks*.

If an exception is thrown for a constructor or its serialization equivalents (`readObject` and `readResolve` methods), the *finalizer* of a malicious subclass can run on the partially constructed object that should have "died on the vine". The finalizer can record a reference to the object in a static field, preventing it from being garbage collected.

Once the malformed object has been recorded, it is a simple matter to invoke arbitrary methods on this object that should never have been allowed to exist in the first place.

**Throwing an exceptio nfron a constructor should be sufficient to prevent an object from coming into existence, in the presence of finalizers, it is not**.

> Final classes are immute to finalizer attacks because no one can write a malicious subclass of a final class.

## Alternative

For a class whose objects encapsulate resources that require termination, such as files or threads, you can **have your class implement `AutoCloseable`**, and require its clients to invoke the `close` method on each instance when it is no longer needed, typically using `try-with-resources` to ensure termination even in the face of exceptions.

One detail worth mentioning is that the instance must keep track of whether it has been closed: the `close` method must record in a field that the object is no longer valid, and the other methods must check this field and thrown an `IllegalStateException` if they are called after the object has been closed.

## Legitimate Uses

### Safety Net

*Cleaners* can act as a safety net in case the owner of a resource neglects to call its `close` method. While there's no guarantee that the *cleaner* or *finalizer* will run promptly (or at all), it is better to free the resource late than never if the client fails to do so.

if you're considering writing such a safety-net finalizer, think long and hard about whether the protection is worth the cost.

> Some java library classes, such as *FileInputStream*, *FileOutputStream*, and *ThreadPoolExecutor* have finalizers that serve as safety nets.

### Objects with *native peers*

A native peer is a native (non-Jave) object to which a normal object delegates via native methods.

Because a native peer is not a normal object, the garbage collector doesn't know about it and can't reclaim it when its Java peer is reclaimed. A finalizer or cleaner may be an appropriate vehicle for this task, assuming the performance is acceptable and the native peer holds no critical resources, otherwise that must be reclaimed promptly and the class should have a `close` method.

## Case Study

Below is a simple `Room` class demonstrating *cleaners*. Let's assume that rooms must be cleaned before they are reclaimed. The `Room` class implements `AutoClseable`; the fact that its automatic cleaning safety net uses a *cleaner* is mrely an implementation detail. Unlike *finalizers*, **cleaners do not pollute class's public API**:

```java
// An autocloseable class using a cleaner as a safety net
public class Room implements AutoCloseable {
  private static final Cleaner cleaner = Cleaner.create();

  // Resource that requires cleaning. Must not refer to Room!
  private static class State implements Runnable {
    int numJunkPiles; // junk piles in this room

    State(int numJunkPiles) {
      this.numJunkPiles = numJunkPiles;
    }

    // Invoked by close method or cleaner
    @Override public void run() {
      System.out.println("Cleaning room");
      numJunkPiles = 0;
    }
  }

  // State of this room, shared with our cleanable
  private final State state;

  // Cleanble. Cleans the room when it's eligible for garbage collection
  private final Cleaner.Cleanable cleanable;

  public Room(int numJunkPiles) {
    state = new State(numJunkPiles);
    cleanable = cleaner.register(this, state);
  }

  @Override public void close() {
    cleanable.clean();
  }
}
```

The static nested `State` class holds the resources that are required by the cleaner to clean the room. In this case, it is simple the `int numJunkPiles` field. More realistically, it might be a `final long` that contains a pointer to a native peer. `State` implements `Runnable`, and its `run` method is called at most once, by the `Cleanable` that we get when we register our `State` instance with our cleaner in the `Room` constructor.

The call to the `run` method will be triggered by one of two things:  Usually it is triggered by a call to `Room`'s `close` method calling `Cleanable`'s `clean` method. if the client fails to call the `close` method by the time a `Room` instance is eligble for garbage collection, the cleaner will (hopefully) call `State`'s `run` method.

It is critical that a `State` instance doees not refer to its `Room` instance. If it did, it would create a circularity that would prevent the `Room` instance from becoming eligble for garbage collection. Therefore, `State` must be a `static` nested class because nonstatic nested classes contain references to their enclosing instances. It is similarly inadvisable to use a lambda because they can easily capture references to enclosing objects.

> `Room`'s cleaner is used only as a **safety net**. If clients surrund all `Room` instantiations in `try-with-resource` blocks, automatic cleaning will never be required.

This well-behaved client demonstrates that behavior:

```java
public class Adult {
  public static void main(String[] args) {
    try (Room myRoom = new Room(7)) {
      System.out.println("Goodbye");
    }
  }
}
```

Running the `Adult` program prints `Goodbye` followed by `Cleaning room`.

The following is an ill-behaved program example which never cleans its room:

```java
public class Teenager {
  public static vod main(String[] args) {
    new Room(99);
    System.out.println("Peace out");
  }
}
```

This never prints `Cleaning room` in some machines, because as the `Cleaner` spec says: **"The behavior of cleaners during `System.exit` is implementation specific**. The same holds true for normal program exit.
