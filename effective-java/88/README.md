# 88: Write `readObject` methods defensively

* If your class implements `Serializable`, the `readObject` method is effectively another public constructor. Just as a constructor must check its arguments for validity ([Item 49](../49)) and make defensive copies of parameters where appropriate ([Item 50](../50)), so must a `readObject` method. If a `readObject` method fails to do either of these things, it is a relatively simple matter for an attacker to violate the class's invariants.

* When an object is deserialized, **it is critical to defensively copy any field containing an object reference that a client must not possess**. Mutable components of immutable classes fall into this category.

* Check any invariants and throw an `InvalidObjectException` if a check fails. The checks should follow any defensive copying.

* If an entire object graph must be validated after it is deserialized, use the `ObjectInputValidation` interface.

* **Do not invoke any overridable methods in the class**, directly or indirectly.
