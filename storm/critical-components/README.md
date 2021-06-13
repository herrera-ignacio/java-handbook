# Architecture Critical Components

Storm has a **master-slave architecture**.

* Nodes
  * Master Nodes
  * Worker Nodes
* Components
  * Topology
  * Stream
  * Spout

## Nodes

A **Master Node** executes a daemon **Nimbus** which assigns tasks to machines and monitors their performances.

On the other hand, a **Worker Node** runs the daemon called **Supervisor** which spawn *worker processes* on the worker node and assign work to them..

As Storm cannot monitor the state and health of cluster, it deploys *ZooKeeper* to solve this issue which connects *Nimbus* with the *Supervisors*.

## Components

* **Topology**: Network made of Stream and Spout.

* **Stream**: Unbounded pipeline of tuples.

* **Spout**: Source of the data streams which converts the data into the tuple of streams and sends to the *Bolts* to be processed.
