# 25: Limit source files to a single top-level class

**Never put multiple top-level classes or interfaces in a single source file**.

The risk stem from the fact that defining multiple top-level classes in a source file makes it possible to provide multiple definitions for a class. Which definition gets used is affected by compilation details such as the order in which the source files are seen by the compiler. You open yourself up to unpredictable behavior.

Fixing the problem is as simple as splitting the top-level classes into separate source files whose names match the top-level classes they contain.
