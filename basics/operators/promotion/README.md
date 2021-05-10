# Operand Promotion

Operands smaller than `int` are promoted to `int`.

> 127 (byte) + 1 (byte) -> 127 (int) + 1 (int) -> 128 (int)

> 'a' + 'b' -> 195 (int)

## Same-Type Operations

If __both__ operands are `int`, `long`, `float` or `double`, then operations are carried in that type and evaluated to a value of that type.

> 5+6 -> 11

> 1/2 -> 0 (not 0.5)

## Mixed-Type Operations

If operands belong to different types, then smaller type is promoted to larger type.

> int -> long -> float -> double

> Example: char + float -> int + float -> float + float -> float

