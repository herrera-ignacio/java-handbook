# Storm Overview

Apache Storm is a free and open source distributed stream processing (realtime) computation framework.

It uses custom created "*spouts*" and "*bolts*" to define information sources and manipulations to allow batch, distributed processing of streaming data.

> Apache Storm is a framework for processing big data in real time and integrates with queueing and database technologies you already use. Doing for realtime processing what Hadoop did for batch processing.

![](2021-06-13-16-04-31.png)

A Storm application is designed as a "*Topology*" in the shape of a *Directed Acyclic Graph (DAG)* with spouts and bolts acting as the graph vertices. Edges on the graph are named streams and direct data from one node to another. Together, **the topology acts as a data transformation pipeline**.

> At a superficial level, the general topology structure is similar to a `MapReduce` job, with the main difference being that data is processed in real time as opposed to in individual batches.

## Use Cases

* Realtime Analytics
* Online Machine LEarning
* Continuous Computation
* Distributed RPC
* ETL
