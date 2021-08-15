# 38: Emulate extensible enums with interfaces

* Overview
* A Solution

## Overview

Using the language feature (enum types pattern) it is not supported to have one enumerated type extend another. This is no accident. For the most part, extensibility of enums turns out to be a bad idea.

That said, there is at least **one compelling use case for extensible enumerated types**, which is *operation codes*, also known as *opcodes*. Sometimes it is desirable to let the users of an API provide their own operations, effectively extending the set of operations provided by the API.

## A Solution

The basic idea is to take advantage of the of the fact that enum types can implement arbitrary interfaces by defining an interface for the opcode type and an enum that is the standard implementation of the interface. While the enum type is not extensible, the interface type is, and it is the interface that is used to represent operations in APIs. You can define another enum type that implements this interface and you use instances of this new type in place of the base type.

In summary, **while you cannot write an extensible enum type, you can emulate it by writing an interface to accompany a basic enum type that implements the interface**. This allows lients to write their own enums (or other types) that implement the interface. Instances of these types can then be used wherever instances of the basic enum type can be used, assuming APIs are written in terms of the interface.
