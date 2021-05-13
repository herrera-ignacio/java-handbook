# Nested Classes

* Inner classes
  * Nonstatic member classes
  * Anonymous classes
  * Local classes
* Static member classes

## Visibility

* Nested classes: all access levels.
* Top-level classes: only `public` & `default`.
* __Mutual accessibility of members including `private`__.

## Why not have nested classes as top-level classes?

* __Class pollution__: probably only a few classes need it (`HashMap` example with nested `EntrySet`, `EntryIterator`, `Node`).
* Functionality kept closer to original class.
* Better design.