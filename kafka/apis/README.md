# Kafka APIs

* *Admin API*: To manage and inspect topics, brokers, and other Kafka objects.

* *Producer API*: To publish (write) a stream of events to one or more Kafka topics.

* *Consumer API*: To subscribe to (read) one or more topics and to process the stream of events produced to them.

* *Kafka Streams API*: To implement stream processing applications and microservices. It provides higher-level functions to process event streams, inclduing transformations, stateful operations like aggregations and joins, windowing, processing based on event-time, and more. Input is read from one or more topics in order to generate output to one or more topics, effectively transformingt the input streams to output streams.

* *Kafka Connect API*: To bun and run reusable data import/export connectors that consume (read) or produce (write) streams of events from and to external systems and applications so they can integrate with Kafka. However, you typically don't need to implement your own connectors because the Kafka community already provides hundred of ready-to-use connectors.
