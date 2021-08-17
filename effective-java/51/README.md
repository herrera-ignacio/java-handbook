# 51: Design method signatures carefully

> This item is a grab bag of API design hints that don't quite deserve items of their own. Taken together, they'll help you make your API easier to learn and use and less prone to errors.

* **Choose method names carefully**.
  * Names should always obey the standard naming conventions.
  * Names should be understandable and consistent with other names in the same package.

* **Don't go overboard in providing conveniente methods**.
  * Consider providing a "shorthand" only if it will be used often. When in doubt, leave it out.

* **Avoid long parameter lists**.
  * Aim for four parameters or fewer.
  * Otherwise API won't be usable without constant reference to its documentation.
  * **Long sequences of identically typed parameters are specially harmful**. Not only won't users be able to remember the order of the parameters, but when they transpose parameters accidentally, their programs will still compile and run but not as intended.
  * **Techniques for shortening overly long parameter lists include**: break up the method up into multiple methods, each of which requires only a subset of the parameters; create *helper classes* to hold groups of parameters; and *Builder pattern*.

* **For parameter types, favor interfaces over classes**.
  * Otherwise you restrict your class to a particular implementation and force an unnecessary and potentially expensive copy operation if the input data hgappens to exist in some other form.
  * For example, there is no reason to ever write a method that takes a `HashMap` on input, use `Map` instead.

* **Prefer two-element enum types to `boolean` parameters**, unless the meaning of the boolean is clear from the method name.
  * Enums make your code easier to read and to write.
  * Enums make it easy to add more options later.
