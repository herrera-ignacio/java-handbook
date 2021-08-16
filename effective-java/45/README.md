# 45: Use streams judiciously

* Stream API
  * Stream Pipeline
* Overview
  * Iterative advantages
  * Streams advantages

## Stream API

* Streams API was added to ease the task of perfming bulk operations, sequentially or in parallel.

* It provides two key abstractions:

  * The **stream**, which represents a finite or infinite sequence of data elements.

  * The **stream pipeline**, which represents a multistage computation on these elements. 

* It is **fluent**: it is designed to allow all of the calls that comprise a pipeline to be chained into a single expression.

> Common sources include collections, arrays, files, regular epxression pattern matchers, pseudorandom number generator, and other streams. The data elements in a stream can be object references or primitive values `int`, `long`, and `double`.

### Stream Pipeline

* It consists of a source of stream followed by zero or more *intermediate operations* and one *terminal operation*. Intermediate operations transform one stream into another.

* **Evaluated lazily**: it doesn't start until the terminal operation is invoked, and data elements that aren't required in order to complete the terminal operation are never computed.

* **By default** pipelines **run sequentially**. Making a pipeline execute in parallel is as simple as invoking the `parallel` method on any stream in the pipeline, but it is seldom appropriate to do so ([Item 48](../48)).

## Overview

* When used appropriately, streams can make programs shorter and clearer; when used inappropriately, they can make programs difficult to read and maintain. There are no hard and fast rules for when to use streams, but there are **heuristics**.

* Overusing streams makes programs hard to read and maintain.

* As a rule, even moderately complex tasks are best accomplished using some combination of streams and iteration. So refactor existing code to use streams and use them in new code only where it makes sense to do so.

* In the absence of explicit types, careful naming of lambda parameters is essential to the readability of stream pipelines.

* Using helper methods is even more important for readability in stream pipelines than in iterative code.

* Ideally you should refrain from using streams to process `char` values.

### Iterative advantages

* From a code block, you can read or modify any local variable in scope; from a lambda, you can only read final or effectively final variables, and you can't modify any local variables.

* From a code block, you can `return` from the enclosing method, `break` or `continue` an enclosing loop, or throw any checked exception that this method is declared to throw; from a lambda you can do none of these things.

### Streams advantages

* Unfiformly transform sequences of elements.

* Filter sequences of elements.

* Combine sequence of elements using a single operation.

* Accumulate sequences of elements into a collection, perhaps grouping them by some commong attribute.

* Search a sequence of elements for an element satisfying some criterion.
