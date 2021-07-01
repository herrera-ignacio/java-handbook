# `Long` vs `long`

> https://stackoverflow.com/questions/21034955/when-to-use-long-vs-long-in-java, the same difference applies to `Float`-`float`, `Integer`-`integer`, etc...

`long` is a ***primitive***, which *must* have a value. It is slightly more efficient considering memory space and processing speed.

Long` is an ***object***, so:

* It can be `null`. An advantage of this for example is with IDs, if no `Long ID` is supplied, you can quickly detect this, but with *primitives* you'll have to use some special value to indicate missing, which is messy.

* It can be passed to a method that accepts an `Object`, `Number`, `Long` or `long` (last one thanks to auto-unboxing).

* It can be used as a generic parameter type (ie `List<Long>` is ok, but `List<long>` is **not** ok).

* It can be serialized/deserialized via the Java serialization mechanism. So it will be very useful when doing file, database, or network IO.
