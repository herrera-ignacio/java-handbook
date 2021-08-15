# 37: Use `EnumMap` instead of ordinal indexing

> Some old implementations uses the enum's `ordinal` method to index into an array or list.

The `java.util.EnumMap` is designed for use with enum keys. Using this class protects you from unsafe cast, and no possibility for error in computing array indices.

`EnumMap` combines the richness and type safety of a `Map` with the speed of an array.
