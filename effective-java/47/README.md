# 47: Prefer collection to Stream as a return type

* If an API returns only a stream and some users want to iterate over the returned sequence of elements with a for-each loop, those users will be justifiable upset.

* If you know that your method that returns a sequence of objects will only be used in a stream pipeline, then of course you should feel free to return a stream. Similarly, a method returning a sequence that will only be used for interation should return an `Iterable`.

* If you are writing a public API that returns a sequence, you should provide for users who want to write stream pipelines as well as those who want to write for-each statements.

* The `Collection` interface is a subtype of `Iterable` and has a `stream` method, so **it provides for both iteration and stream access**. Therefore, `Collection` or an appropriate subtype is **generally the best return type for a public, sequence-returning method**.

* Arrays also provide for easy iteration and stream access with the `Arrays.asList` and `Stream.of` methods. If the sequence you're returning is small enough to fit easily in memory, you're probably best off returning one of the standard collection implementations, such as `ArrayList` or `HashSet`. But **do not store a large sequence in memory just to return it as a collection**.

* If the sequence you're returning is large but can be represented concisely, consider implementing a special-purpose collection.

* If, in a future Java release, the `Stream` interface declaration is modified to extend `Iterable`, then you should feel free to return streams because they will allow for both stream processing and iteration.
