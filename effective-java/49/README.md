# 49: Check parameters for validity

* You should **document restrictions** on what values may be passed into parameters a**nd enforce them** with checks at the beginning of the method body.

> This is a special case of the general principle that you should attempt to detect errors as soon as possible after they occur. Failiing to do so makes it less likely that an error will be detected and makes it harder to determine the source of an error once it has been detected.

* If an invalid parameter value is passed to a method and the method checks is parameters before execution, it will **fail quickly and cleanly with an appropriate exception**. Failure to validate parameters, can result in a violation of *failure atomaticity* ([Item 76](../76)).

* For public and protected methods, use the Javadoc `@throws` tag to document the exception that will be thrown if a restriction on parameter values is violated ([item 74](../74)). Typically, it will be `IllegalArgumentException`, `IndexOutOfBoundsException`, or `NullPointerException` ([Item 72](../72)).

* The `Objects.requireNonNull` method is flexible and convenient, so there's no reason to perform null checks manually anymore.

* For an unexported method, you can and should ensure that only valid parameter values are ever passed in. Therefore, nonpublic methods can check their parameters using *assertions* (`assert`). Unlike normal validity checks, assertions throw `AssertionError` if they fail. And unlike normal validity checks, they have no effect and essentially no cost unless you enable them, which you do by passing the `-ea` (or `-enableassertions`) flag to the `java` command.

* An important exception to the rule is the case in which the validity check would be expensive or impractical *and* the check is performed implicitly in the process of doing a computation.

* Ocassionally, a computation implicitly performs a required validity check but throws the wrong exception if the check fails. Under these circumstances, you should use the *exception translation* ([Item 73](../73)) to translate the natural exception into the correct one.
