# 35: Use instance fields instead of ordinals

All enums have an `ordinal` method, which returns the numerical position of each enum constant in its type. You may be tempted to derive an associated `int` value from the ordinal but if constants are reordered, it will create a maintenance nightmare.

The solution is to **never derive a value associated with an enume from its ordinal; store it in an instance field instead**.
