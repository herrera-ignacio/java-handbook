# Trident State

Trident state enables **stateful "exactly once" processing of streams**. State information is maintained either *in memory* or *in external databases like Cassandra or Redis*. A problem with this approach is that more storage is needed for maintaining state.

> "Exactly once" processing means **each tuple is processed only once**.

* Tuples are processed in small batches.
* Each batch is assigned an unique id called *transaction id*.
* State updates are ordered among batches. That is, the state updates for batch2 won't be applied until state updates for batch1 have succeeded.

## Exactly One Processing Example

* Spout replays tuples till the state update is done for the tuple.
* A class for implementing `IBackMap` is created that:
  * Uses the type `OpaqValue` for the values.
  * Creates in memory or database mechanism for storing value.
  * Defines the method `multiGet` to get a list of values given a list of keys.
  * Defines the method `multiPut` to store the state given a list of keys and values.
* A factory class for creating a map state with the above class.
* A `persistentAggregate` method on the stream to aggregate the values.

```java
public class myBackingMap implements IBackingMap ... {
  public List<T> multiGet(...);
  public void multiPut(...);
}
```

## Case Study

### Problem

An investment firm receives the stock trading data of a customer as comma separated streaming data. This has to be processed to calculate the balance of stocks for each stock symbol as the data is arriving and store it in a state in a fault-tolerant and exactly one processing.

### Solution

* A Spout generates a batch of tuples with `ticker` and `quantity`.
* A `Map` of `ticker` to `OpaqValue` is used to store the state. The base type of `OpaqValue` is made double to store the `quantity`.
* A class defines `multiGet` and `multiPut` methods to get/store `OpaqValues` from/to the state.
* A factory class creates the above class.
* Group by is done on the stream with field `ticker`.
* A `persistentAggregate` is used to sum the values with state.


