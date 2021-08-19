# 64: Refer to objects by their interface

* You should favor the use of interfaces over classes to refer to objects. **If appropriate interface types exist, then parameters, return values, variables, and fields should all be declared ussing interface types**.

* If you get into the habit of using interfaces as types, **your program will be much more flexible**.

* The only time you really need to refer to an object's class is when you're creating it with a constructor.

* It is entirely appropriate to refer to an object by a class rather than a interface if no appropriate interface exists (e.g., *value classes*, such as `String` and `BigInteger`).

* **If there is no appropriate interface, use the least specific class** in the class hierarchy that provides the required functionality.
