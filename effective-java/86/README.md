# 86: Implement `Serializable` with great caution

* A major cost of implementing `Serializable` is that **it decreases the flexibility to change a class's implementation once it has been released**.

* If you don't make the effort to design a *custom serialized form* but merely accept the default, the serialized form will forever be tied to the class's original internal representation. In other words, if you accept the default serialized form, the class's private and package-private instance fields become part of its exported API, and the practice of minimizing access to fields ([Item 15](../15)) loses its effectiveness as a tool for information hiding. If you later change a class's internal representation, an incompatible change in the serialized form will result.

* A second cost of implementing `Serializable` is that **it increases the likelihood of bugs and security holes**.

* A third cost of implementing `Serializable` is that **it increases the testing burden associated with releasing a new version of a class**.

* Implementing `Serializable` is **not a decision to be undertaken lightly**.

* Classes designed for inheritance should rarely implement `Serializable`, and interfaces should rarely extend it.

* Inner classes should not implement `Serializable`. The use compiler-generated *synthetic fields* to store ferences to *enclosing instances* and to store values of local variables from enclosing scopes. How these fields correspond to the class definition is unspecified.
