# 62: Avoid strings where other types are more appropriate

Avoid the natural tendency to represent objects as strings when better data type exist or can be written. Used inappropriately, strings are more cumbersome, less flexible, slower, and more error-prone than other types.

* Strings are poor substitutes for other value types (e.g., enums, aggregates).

* Strings are poor substitutes for capabilities. It's better to use an unforgeable key (sometimes called a *capability*).
