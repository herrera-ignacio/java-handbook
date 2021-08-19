# 79: Avoid excessive synchronization

> [Item 78](../78) warns of the dangers of insufficient synchronization. This item concerns the opposite problem. Depending on the situation excessive synchronization can cause reduced performance, deadlock, or even nondeterministic behavior.

* **To avoid liveness and safety failures, never cede control to the client within a synchronized method or block**. In other words, inside a synchronized region, do not invoke a method that is designed to be overriden, or one provided by a client in the form of a function object. Depending on what this *alien* method does, calling it from a synchronized region can cause exceptions, deadlocks, or data corruption.

* As a rule, **you should do as little work as possible inside synchronized region**.

* When you are designing a mutable class, think about whether it should do its own synchronization. **Synchronize your class internally only if there is a good reason to do so, and document your decision clearly** ([Item 82](../82)).
