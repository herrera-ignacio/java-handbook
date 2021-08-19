# 80: prefer executors, tasks, and streams to threads

> Refer to *Java Concurrency in Practice* book for more.

`java.util.concurrent` contains an *Executor Framework*, which is a flexible interface-based task execution facility.

```java
// Creating a work queue
ExecutorService exec = Executors.newSingleThreadExecutor();
exec.execute(runnable);
exec..shutdown();
```

You can do *many* more things with the executor service. For example, you can wait for a particular task to complete (`get`), wait for any or all of a collection of tasks to complete (`invokeAny` or `invokeAll`), wait for the executor service to terminate (`awaitTermination`), retrieve the results of tasks one by one as they complete (`ExecutorCompletionService`), schedule tasks to run at a particular time or peridocially (`ScheduledThreadPoolExecutor`), and so on.

> If you want more than one thread to process requests from the queue, simply call a different static factory that creates a different kind of executor service called a *thread pool*. You can create a thread pool with a fixed or variable number of threads.

**You should generally refrain from wokring directly with threads**. When you work directly with threads, a `Thread` serves as both a unit of work and the mechanism for executing it. In the executor framework, the unit of work and the execution mechanism are separate. The key abstraction is the unit of work, which is the *task*. There are two kinds of tasks: `Runnable` and `Callable` (which is like `Runnable, except that it returns a value and can throw arbitrary exceptions). The general mechanism for executing tasks is the *executor service*.

If you think in terms of tasks and let an executor service execute them for you, you gain the **flexibility to select an appropriate execution policy and to change it if your needs change**.

> In essence, the Executor Framework does for execution what the Collections Framework did for aggregation.
