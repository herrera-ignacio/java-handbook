# Grouped Streams Operations

*Group By* operation is used to group touples by fields. *The stream is repartitioned using the group by fields*.

```java
gStream = Stream.groupBy(new Fields("Ticker"));
gStream.persistentAggregate(StateFactory state, new Fields("quantity"), new Sum(), new fields("sum"));
```
