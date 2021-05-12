# Serialization

See [code example](../code/basics/serialization).

> Primitives & Arrays are by default serializable

* Serialize to disk or perform caching (e.g., memcached).
* Passing Java objects to remote hosts.
* Post-mortem analysis.

## Deserialization Process

* Read serialized object
* Find serialized object's class name & load _Class_ object.
* Fails if object cannot be loaded or _version mismatch_.