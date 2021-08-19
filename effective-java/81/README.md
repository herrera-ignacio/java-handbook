# 81: Prefer concurrency utilities to `wait` and `notify`

* Overview
* Concurrent Collections
* Synchronizers

## Overview

* Given the difficulty of using `wait` and `notify` correctly, **you should use the higher-level concurrency utilities** (`java.util.concurrent`) instead. **There is seldom, if ever, a reason to use `wait` and `notify` in new code**.
  * Executor Framework ([Item 80](../80))
  * Concurrenct Collections
  * Synchronizers

* For interval timing, always use `System.nanoTime` rather than `System.currentTimeMillis`, because it is more accurate and more precise, and is unaffected by adjustments to the system's real-time clock.

* **Always use the wait loop idiom to invoke the `wait` method; never invoke it outside of a loop**.
  * Testing the condition before waiting and skipping the wait if the condition already holds are **necessary to ensure liveness**. If the condition already holds and the `notify` (or `notifyAll`) method has already been invoked before a thread waits, there is no guarantee that the thread will *ever* wake from the wait.
  * Testing the condition after waiting and waiting again if the condition does not hold are **necessary to ensure safety**. If the thread proceeds with the action when the condition does not hold, it can destroy the invariant guarded by the lock. There are several reasons a thread might wake up when the condition does not hold:
    * Another thread could have obtained the lock and changed the guarded state between the time a thread invoked `notify` and the waiting thread woke up.
    * Another thread could have invoked `notify` accidentally or maliciously when the condition did not hold. Any `wait` in a synchronized method of a publicly accessible object is susceptible to this problem.
    * The notifying thread could be overly "generous" in waking waiting threads. For example, it might invoke `notifyAll` even if only some of the waiting threads have their condition satisfied.
    * The waiting thread could (rarely) wake up in the absence of a notify. This is known as *spurious wakeup*.

## Concurrent Collections

* To provide high concurrency, these implementations manage their own synchronization internally. Therefore, **it is impossible to exclude concurrent activity from a concurrent collection; locking it will only slow the program**. Thus, you can't atomically compose method invocations on them either. Therefore, concurrent collection interfaces were outfitted with *state-dependent modify operations*, which combine several primitives into a single atomic operation.

* These operations proved sufficiently useful on concurrent collections that they were added to the corresponding collection interfaces in Java 8, using default methods. Some examples:
  * `Map`'s `putIfAbsent(key, value)` inserts a mapping for a key if none was present and returns the previous value associated with the key or `null` if there was none. This makes it easy to implement thread-safe canonicalizing maps.

* Concurrent collections make synchronized collections largely obsolte.
  * Use `ConcurrentHashMap` in preference to `Collections.ynchronizedMap`.

* Some of the collection interfaces were extended with *blocking operations*, which wait (or *block*) until they can be successfully performed. For example, `BlockingQueue` adds several methods, including `take`, which removes and returns the head element from the queue, waiting if the queue is empty.

## Synchronizers

* *Synchronizers* are objects that **enable threads to wait for one another, allowing them to coordinate** their activities.

* The most commonly used synchronizers are `CountDownLatch` and `Semaphore`. Less commonly used are `CylcicBarrier` and `Exchanger`. The most powerful synchronizer is `Phaser`.
