# 78: Synchronize access to shared mutable data

* **When multiple threads share mutable data, each thread that reads or writes the data must perform synchronization**.

> The `synchronized` keyword ensures that only a single thread can execute a method or block at one time.

* Not only does synchronization prevent threads from observing an object in an inconsistent state, but it ensures that each thread entering a synchronized method or block sees the effects of all previous modifications that were guarded by the same lock.

> The language specification guarantees that reading or writing a variable is *atomic* unless the variable is of type `long` or `double`.

* **Synchronization is required for reliable communication between threads as well as for mutual exclusion**. While the language specification guarantees that a thread will not see an arbitrary value when reading a field, it does not guarantee that a value written by one thread will be visible to anoter.

> This is due to a part of the language specification known as the *memory model*, which specifies when and how changes made by a thread become visible to others.

* Do not use `Thread.stop`, it has been deprecated because it is inherently *unsafe* (its use may result in data corruption). A recommended way to stop one thread from another is to have the first thread poll a `boolean` field that is initially `false` but can be set to `true` by the second thread to indicate that the first thread is to stop itself. This field should use `synchronized` keyword, otherwise, in the absence of synchronization, there is no guarantee as to when, if ever, the background thread will see changes in the value of the boolean flag made by another thread.

* Synchronization is **not guaranteed to work unless both read and write operations are synchronized**.

* The best way to avoid problems discussed in this item is not to share mutable data. Either **share immutable data** or don't share at all. In other words, **confine mutable data to a single thread**.
