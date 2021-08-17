# 50: Make defensive copies when needed

* You must program defensively, with the assumption that clients of your class will do their best to destroy its invariants.

* While it is impossible for another class to modify an object's internal state without some assistance from the object, it is surprisingly easy to provide such assistance without meaning to do so. There are times when you'll have to use mutable types in your APIs and internal representations.

* To protect the internals, **it is essential to make a _defensive copy_ of each mutable paramter to the constructor** and to use the copies as components in place of the originals.

* Note that **defensive copies are made _before_ checking the validity of the parameters, and the validity check is performed on the copies rather than on the originals**. This protects the class against changes to the parameters from another thread during the *window of vulnerability* between the time the parameters are checked and the time they are copied.

> This is known as a *time-of-check/time-of-use* or *TOCTOU* attack.

* Do not use the `clone` method in constructor to make a defensive copy of a parameter whose type is subclassable by untrusted parties.

* Modify the accessors to **return defensive copies of mutable internal fields**.
