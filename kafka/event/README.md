# Kafka Event

An *event* records the fact that "something happened" in the world or in your business. It is also called *record* or *message* in the documentation.

When you read or write data to Kafka, you do this in the form of events.

Conceptually, an event has:

* Key
* Value
* Timestamp
* Optional Metadata Headers

```
* Event key: "Alice"
* Event value: "Made a payment of $200 to Bob"
* Event timestamp: "Jun. 25, 2020 at 2:06 p.m." 
```
