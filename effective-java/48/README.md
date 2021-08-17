# 48: Use caution when making streams parallel

* Overview
* Example

## Overview

* Even under the best circumstances, **parallelizing a pipeline is unlikely to increase its performance if the source is from `Stream.iterate` or the intermediate operation `limit` is used**.

* Do not parallelize stream pipelines indiscriminately.

* Not only can parallelizing a stream lead to **poor performance**, including **liveness failures**; it can lead to **incorrect results and unpredictable behavior** (*safety failures*). 

> Safety failures may result from parallelizing a pipeline that uses mappers, filters, and other programmer-supplied function objects that fail to adhere to their specifications.

* Under the right circumstances, it is *possible* to achieve near-linear speedup in the number of processor cores simply by adding a `parallel` call to a stream pipeline.

* Aa a rule, **performance gains from parallelism are best on streams over `ArrayList`, `HashMap`, `HashSet`, and `concurrentHashMap` instances; arrays; `int` ranges; and `long` ranges**.
  * These data structures can all be accurately and cheaply split into subranges of any desired sizes, which makes it easy to divide work among parallel threads.

  * They provide good-to-excellent *locality of reference* when processed sequentially: sequential element references are stored together in memory, which is critically important for parallelizing bulk operations: without it, threads spend much of their time idle, waiting for data to be transferred from memory into the processor's cache.

* The nature of a stream pipeline's terminal operation also affects the effectiveness of parallel execution. If a significant amount of work is done in the terminal operation compared to the overall work of the pipeline, and the operation is inherently sequential, then parallelizing the pipeline will have limited effectiveness.
  * The best terminal operations for parallelism are *reductions*, where all of the elements emerging from the pipeline are combined using one of `Stream`'s `reduce` methods, or prepackaged reductions such as `min`, `max`, `count`, and `sum`. The *short-circuiting* operations `anyMatch`, `allMatch`, and `noneMatch` are also amenable to parallelism.
  * The operations performed by `Stream`'s `collect` method, which are known as *mutable reductions*, are not good candidates for parallelism because the overhead of combining collections is costly.

* If you write your own `Stream`, `Iterable`, or `Collection` implementation and you want decent parallel performance, you must override the `spliterator` method and test the parallel performance of the resulting streams extensively.

## Example

```java
// Primer-counting stream pipeline - improved parallel version
static long pi(long n) {
  return LongStream.rangeClosed(2, n)
    .parallel()
    .mapToObj(BigInteger::valueOf)
    .filter(i -> i.isProbablePrimer(50))
    .count();
}
```
