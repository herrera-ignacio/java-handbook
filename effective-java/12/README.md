# 12: Always override `toString`

While `Object` provides an implementation of the `toString` method, te string that it returns is generally not what the user of your class wants to see.

> It consists of the class name followed by an "at" sign (`@`) and the unsigned hexadecimal representation of the hash code, for example, `PhoneNumber@adbbd`.

The `toString` method is automatically invoked when an object is passed to `println`, `printf` the string concatenation operator, or `assert`, or is printed by a debugger.

> Even if you never call `toString` on an object, others may.

While it isn't as critical as obeying the `equals` and `hashCode` contracts, **providing a good `toString` implementation makes your class much more pleasant to use and makes systems using the class easier to debug**.

## `toString` contract

The general contract says that the returned string should be *"a concise but informative representation that is easy for a person to read. It is recommendad that all subclasses override this method."*.

When practical, the `toString` method should return *all* of the interesting information contained in the object.

## Documentat your intentions

One important decision is **whether to specify the format** of the return value in the documentation. It is recommendad that you do this for *value classes*.

> If you specify the format, it's usually a good idea to provide a matching static factory or constructor so programmers can easily translate back and forth between the object and its string representation.

The **advantage** is that it serves as a **standard, unambiguous, human-readable representation of the object**.

> This representation can be used for input and output, and in persistent human-readable data objects, such as CSV files.

The **disadvantage** is that once you've specified the `toString` return value, you're stuck with it for life, assuming your class is widely used. Programmers will write code to parse the representation, to generate it, and to embed it into persistent data. If you change the representation in a future release, you'll break their code and data.

By choosing not to specify a format, you **preserve the flexibility** to add information or improve the format in a subsequent release.

**Whether or not you decide to specify the format, you should clearly document your intentions**.

## Programmatic Access

Whether or not your specify the format, **provide programmatic access to the information contained in the value returned by `toString`**.

> For example, a `PhoneNumber` class should contain accessors for the area code, prefix, and line number.

If you fail to do this, you *force* programmers who need this information to parse the string, turning the string format into a *de facto API*, even if you've specified that it's subject to change.

## Common Practices

* It makes no sense to write a `toString` method in a static utility class ([Item 4](../4)).

* Nor should you write a `toString` method in most enum types ([Item 34](../34)) because Java provides a perfectly good one for you.

* You should write a `toString` method in any abstract class whose subclasses share a common string representation.
