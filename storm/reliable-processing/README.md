# Reliable Processing

* If a tuple processing has an error:
  * Error message given
  * Processing continues
* If a task aborts
  * Another task is created to continue processing
  * New task will continue with next tuple

## Methods for reliable processing

* Each tuple has a unique *message id*, produced by the *Spout*.

* The `ack` method of *Bolt* is used to indicate that tuple is successfully processed.

* The `fail` method of bolt is used to indicate that tuple processing failed.

* *Spout* tracks each message id:
  * When `ack` is received from all the downstream bolts, Spout's `ack` method is called.
  * If `fail` is received from any of the downstream bolts, Spout's `fail` method is called.
  * If `ack` is not received within a timeout period, Spout's `fail` method is called. Default timeout is *30 seconds* and can be set using `TOPOLOGY_MESSAGE_TIMEOUT_SECS` configuration parameter.

## Anchoring

Connecting the input tuple to the output tuple from a *Bolt* is called *anchoring*.

The first parameter of the `emit` method in a Bolt has to be the input tuple for anchoring. Each output tuple is tracked for success/failure/timeout.

The class `BaseBasicBolt` is provided which automatically takes care of anchoring.
