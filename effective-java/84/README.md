# 84: Don't depend on the thread scheduler

* The thread scheduler determines which threads get to run and for how long. Any reasonable OS will try to make this determination fairly, but the policy can vary. Therefore **any program that relies on the thread scheduler for correctness or performance is likely to be nonportable**.

* Ensure that the average number of *runnable* threads is not significantly greater than the number of processors. This leaves the thread scheduler with little choice: it simply runs the runnable threads till they're no longer runnable.

* **Threads should not run if they aren't doing useful work**. In erms of the Executor Framework, this means sizing thread pools appropriately and keeping tasks short, but not *too* short, or dispatching overhead will harm performance.

* **Threads should not _busy-wait_**, repeatedly checking a shared object waiting for its state to change.

* When faced with a program that barely works because some threads are getting enough CPU time relative to others, **resist the temptation to "fix" the program by putting in calls to `Thread.yield`**. This will make your program not portable. The same `yield` invocations that improve performance on one JVM implementation might make it worse on a second and have no effect on a third. `Thread.yield` **has no testable semantics**. A better course of action is to restructure the application to reduce the number of concurrently runnable threads.

* **Thread priorities are among the least portable features of Java**. it is rarely necessary to use it and should be avoided for attempting to solve a serious liveness problem which is likely to return until you find and fix the underlying cause.
