# 82: Document thread safety

* The presence of the `synchronized` modifier in a method declaration is an implementation detail, not a part of its API. It does not reliably indicate that a method is thread-safe.

* To enable safe concurrent use, **a class must clearly document what level of thread safety it supports**:

  * **Immutable**: Instances of this class appear constant. No external synchronization is necessary. Examples include `String`, `Long`, and `BigInteger`.

  * **Unconditionally thread-safe**: Instaces of this class are mutable, but the class has sufficient internal synchronization that its instances can be used concurrently without he need for any external synchronization. Examples include `AtomicLong` and `ConcurrentHashMap`.

  * **Conditionally thread-safe**: Like unconditionally thread-safe, except that some methods require external synchronization for safe concurrent use. Examples include the collections returned by the `Collections.synchronized` wrappers, whose iterators require external synchronization.

  * **Not thread-safe**: Instances of this class are mutable. To use them concurrently, clients must surround each method invocation with external synchronization of the client's choosing. Examples include the general-purpose collections implementations, such as `ArrayList` and `HashMap`.

  * **Thread-hostile**: This class is unsafe for concurrent use even if every method invocation is surrounded by external synchronization. This usually results from modifying static data without synchronization and typically result from the failure to consider concurrency. These methods, when found to be thread-hostile, are usually fixed or deprecated.

> These categories (apart from thread-hostile) correspond roughly to the *thread safety annotations* in *Java Concurrency in Practice*, which are `Immutable`, `ThreadSafe` and `NotThreadSafe`.

* For a thread-safe class, you must document which invocation sequences require external synchronization, and which locks must be acquired to execute these sequences.

* Lock fields should always be declared `final`.
