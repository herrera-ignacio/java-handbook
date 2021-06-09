# Kafka: Producers & Consumers

**Producers** are those client applications that publish (write) events to Kafka, and **Consumers** are those that subscribe to (read and process) these events.

In Kafka, producers and consumers are fully decoupled and agnostic of each other, which is a key design element to achieve the high scalability that Kafka is known for.

> For example, producers never need to wait for consumers. Kafka provides various guarantees such as the ability to process events exactly-once.
