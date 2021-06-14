# Windowing

Process tuples in batches which are of same window & emit aggregated results.

> Windowing can be based on time or count (e.g, 5 minutes or 1000 tuples).

There are two types of windowing:

* **Tumbling window**: A tuple belongs to a single window.
  * Example: Apply the aggregate to tuples at each 5 minute interval or each group of 1000 tuples.
* **Sliding window**: Window slides every sliding interval and tuple may belong to multiple sliding windows.
  * Example: Apply the aggregate to tuples at each 1 minute interval for the tuples arrived in the last 5 minutes. Here the tuple can belong to two consecutive sliding windows.
  * Example: Apply the aggregate to each group of 1000 tuples. This is applied every 500 tuples. Here a tuple can belong to two windows.