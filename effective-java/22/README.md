# 22: Use interfaces only to define types

* That a class implements an interface should say something about what a client can do with instances of the class.

* A *constant interface* pattern is a poor use of interfaces; it contains no methods; it consists solely of `static final` fields, each exporting a constant. That a class uses some constants internally is an implementation detail.
