# Merges and Joins

## Merge

Used to combine multiple streams into one stream. Resultant stream will have fields of all streams.

```java
newStream = topology.merge(stream1, stream2, stream3);
```

## Join

Used to combine two streams into one stream after matching the fields from two streams. Joins are applied within batches.

```java
joinedStream = topology.join(stream1, new fields("key"), stream2, new fields("key"), new fields("key", "col11", "col12", "col21", "col22"));
```
