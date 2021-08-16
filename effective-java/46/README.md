# Prefer side-effect-free functions in streams

* Overview
* `forEach` vs `collector`
* `toMap`
  * `toMap(keyMapper, valueMapper)`
  * `toMap(keyMapper, valueMapper, mergeFunction)`
  * `toMap(keyMapper, valueMapper, mergeFunction, mapFactory)`
* `groupingBy`
* Other collectors

## Overview

* Streams isn't just an API, it's a paradigm based on functional programming. In order to obtain the expresiveness, speed, and in some cases parallelizability that streams have to offer, you have to adopt the paradigm as well as the API.

* The most important part of the streams paradigm is to **structure your computation as a sequence of transformations where the result of each stage is as close as possible to a _pure function_ of the result of the previous stage**. Thus any function objects that you pass into stream operations, both intermediate and terminal, should be free of side-effects.

> A *pure function* is one whose result depends only on its input" it does not depend on any mutable state, nor does it update any state.

* It is customary and wise to statically import all members of `Collectors` because it makes stream pipelines more readable.

* **The essence of programming stream pipelines is side-effect-free function objects**. This applies to all of the many function objects passed to streams and related objects.

* In order to use streams properly, **you have to know about collectors**. The most important collector factories are `toList`, `toSet`, `toMap`, `groupingBy` and `joining`.

## `forEach` vs `collector`

```java
// Uses the streams API but not the paradigm -- Don't do this!
Map<String, Long> freq = new HashMap<>();

try (Stream<String> words = new Scanner(file).tokens()) {
  words.forEach(word -> {
    freq.merge(word.toLowerCase(), 1L, Long::sum);
  });
}
```

This is all iterative code masquerading as streams code. The problem stems from the fact that this code is doing all its work in a terminal `forEach` operation, using a lambda that mutates external state (the frequency table).

**The `forEach` operation should be used only to report the result of a stream computation, not to perform the computation**. Ocasionally, it makes sense to use `forEach` for some other purpsoe, such as adding the results of a stream computation to a preexisting colection.

```java
// Proper use of streams to initialize a frequency table
Map<String, Long> freq;

try (Stream<String> words = new Scanner(file).tokens()) {
  freq = words
    .collect(groupingBy(String::toLowerCase, counting()));
}
```

The improved code uses a `collector`. For starters, you can ignore the `Collector` interface and think of a collector as an opaque object that encapsulates a *reduction* strategy. The object produced by a collector is typically a collection.

> In this context, reduction means combining the elements of a stream into a single object.

The collectors for gathering the elements of a stream into a true `Collection` are straightforward: `toList()`, `toSet()` and `toCollection(collectionFactory)`.

```java
// Pipeline to get a top-ten list of words from a frequency table
List<String> topTen = freq.keySet().stream()
  .sorted(comparing(freq::get).reveresed())
  .limit(10)
  .collect(toList());
```

## `toMap`

Most of `Collector` methods exist to let you **collect streams into maps**, which is far more complicated than collecting them into true collection. Each stream element is associated with a key and a value, and multiple stream elements can be associated with the same key.

### `toMap(keyMapper, valueMapper)`

The simplest map collector is `toMap(keyMapper, valueMapper)`, which takes two functions, one of which maps a stream element to a key, the other, to a value.

```java
// Using a toMap collector to make a map from string form of a enum to the enum "Operation" itself
private static final Map<String, Operation> stringToEnum =
  Stream.of(values()).collect(toMap(Object::toString, e -> e))
```

> If multiple stream elements map to the same key, the pipeline will terminate with an `IllegalStateException`.

### `toMap(keyMapper, valueMapper, mergeFunction)`

The three-argument form of `toMap` lets you provide a *merge function* for dealing with possible collisions. The merge function is a `BinaryOperator<V>`, where `V` is the value type of the map. Any additional values associated with a key are combined with the existing value using the merge function.

> For example, if the merge function is multiplication, you end up with a value that is the product of all the values associated with the key by the value mapper.

This is also useful to make a map from a key to a chosen element associated with that key.

For example, suppose we have a stream of record albums by various artists, and we want a map from recording artist to best-sellign album. This collector will do the job:

```java
// Collector to generate a map from key to chosen element for key
Map<Artist, Album> topHits = albums.collect(
  toMap(Album::artist, a->a, maxBy(comparing(Album::sales)));
)
```

Note that the comparator uses the static factory method `maxBy`, which is statically imported from `BinaryOperator`. This method converts a `Comparator<T>` into a `BinaryOperator<T>` that computes the maxium implied by the specified comparator. In this case, the comparator is returned by the comparator construction method `comparing`, which takes the key extractor function `Album::sales`.

This may seem a bit convoluted, but the code reads nicely. Loosely speaking, it says, "convert the stream of albums to a map, mapping each artist to the album that has the best album by sales", which maps closely to the problem statement.

---

Another use of the three-argument form of `toMap` is to produce a collector that imposes a last-write-wins policy when there are collections. For many streams, the results will be nondeterministic, but if all the values that may be associated with a key by the mapping functions are identical, or if they are all acceptable, this collector's behavior may be just what you want:

```java
// Collector to impose last-write wins policy
toMap(keyMapper, valueMapper, (oldVal, newVal) -> newVal)
```

### `toMap(keyMapper, valueMapper, mergeFunction, mapFactory)`

The third and final version of `toMap` takes a fourth argument, which is a map factory, for use when you want to specify a particular map implementation succh as `EnumMap` or `TreeMap`.

There are also variants forms of the first three versions of `toMap`, named `toConcurrentMap`, that run efficiently in parallel and produce `ConcurrentHashMap` instances.

## `groupingBy`

For all versions of the method, there are variants providing by `groupingByConcurrent` that run efficiently in parallel and produce `ConcurrentHashMap` instances.

### Classifier function

The `Collectors` API provides the `groupingBy` method, which rreturns collectors to produce maps that group elements into categories based on a *classifier function*.

> The classifier function takes an element and returns the category into which it falls. This category serves as the element's map key.

The simplest version of the `groupingBy` method takes only a classifier and returns a map whose values are lists of all the elements in each category.

```java
// Generate a map from alphabetized word to a list of the words sharing the alphabtization (for grouping anagrams)
words.collect(groupingBy(word -> alphabetize(word)))
```

### Downstream collector

If you want `groupingBy` to return a collector that produces a map with values other than lists, you can specify a *downstream collector* in addition to a classifier.

> A downstream collector produces a value from a stream containing all the elements in a category.

The simpelst use of this parameter is to pass `toSet()`, which results in a map whose values are sets of elements rather than lists. Alternatively, you can pass `toCllection(collectionFactory)`, which lets you create the collections into which each category of elements is placed.

Another simple use of the two-argument form of `groupingBy` is to pass `counting()` as the downstream collector. This results in a map that associates each category with the *number* of elements in the category, rather than a collection containing the elements. This is used in the frequency table example shown earlier in this item.

```java
Map<String, Long> freq = words
  .collect(groupingBy(String::toLowerCase, counting()));
```

### Map factory

The third version of `groupingBy` lets you specify a map factory in addition to a downstream collector. This version gives you control over the containing map as well as the contained collections

> Note that this method violates the standard telescoping argument list pattern: the `mapFactory` parameter precedes, rather than follows, the `downStream` parameter.

For example, you can specify a collector that returns a `TreeMap` whose values are `TreeSets`.

## Other collectors

* There is a rarely used relatiive of `groupingBy` called `partitioningBy`. In lieu of a classifier function, it takes a predicate and returns a map whose key is a `Boolean`. There are two overloadings of this method, one of which takes a downstream collector in addition to a predicate.

* The collectors returned by the `counting` method are intended *only* for use as downstream collectors. The same functionality is availble directly on `Stream`, via the `count` method, so **there is never a reason to say `collect(counting())`**. There are fifteen more `Collectors` method with this property, they include the nine methods whose names begin with `summing`, `averaging`, and `summarizing`. They also include all overlaodings of the `reducing`, `filtering`, `mapping`, `flatMapping`, and `collectingAndThen` methods. The

> Most programmers can safely ignore the majority of these methods. From a design perspective, these collectors represent an attempt to partially duplicate the functionaltiy of streams in collectors so that downstream collectors can act as "ministreams".

* There are three `Collectors` methods that don't involve collections.
  * `minBy` and `maxBy` take a comaprator and return the minimum or maximum element in the stream as determined by the comparator. They are minor generalizations of the `min` and `max` method in the `Stream` interface and are the collector analagoues of the binary operators returned by the like-named methods in `BinaryOperator`.

  * `joining` operates only on streams of `CharSequence` instances such as strings. In its parameterless form, it returns a collector that simply concatenes the elements. In its one argument form takes a single `CharSequence` parameter named `delimiter` and returns a collector that joins the stream elements, inserting the delimiter between adjacent elements. The three argument form takes a prefix and suffic in addition the delimiter.
