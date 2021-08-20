# 89: For instance control, prefer enum types to `readResolve`

> The `readResolve` feature allows you to substitute another instance for the one created by `readObject`. If the class of an object being deserialized defines a `readResolve` method with the proper declaration, this method is invoked on the newly created object after it is deserialized. The object reference returned by this method is then returned in place of the newly created object.

* **Use enum types to enforce instance control invariants wherever possible**.

* **If you depend on `readResolve` for instance control, all instance fields with object reference types *must* be declared `transient`**. Otherwise, it is possible for a determined attacker to secure a reference to the deserialized object before its `readResolve` method is run.

* The accessibility of `readResolve` is significant. If you place a `readResolve` method on a final class, it should be private. If you place it on a normal class, you must carefully consider its accessibility.
