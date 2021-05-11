# Default Methods

Default methods enable you to add new functionality to the interfaces of your libraries and ensure binary compatibility with code written for older versions of those interfaces.

> If those methods are added to original interfaces, then programmers who have implemented those interfaces would have to rewrite their implementations. If they add them as static methods, then programmers would regard them as utility methods, not as essential, core methods.

## Extending Interfaces That Contain Default Methods

When you extend an interface that contains a default method, you can do the following:

* Not mention the default method at all, which lets your extended interface inherit the default method.
* Redeclare the default method, which makes it `abstract`.
* Redefine the default method, which overrides it.
