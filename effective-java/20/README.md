# Prefer interfaces to abstract classes

## Overview

A major difference is that to implement the type defined by an abstract class, a class must be a subclass of the abstract class. Becase Java permits only single inheritance, this restriction severely constrains their use as type definitions. Any class that defines all the required methods and obeys the general contract is permitted to implement an interface, regardless of where the class resides in the class hierarchy. 

* Existing classes can easily be retrofitted to implement a new interface.

* Interfaces are ideal for defining mixins.

* Interfaces allow for the construction of nonhierarchical type frameworks.

* You can combine the advantages of both by providng an abstract *skeletal implementation class* to go with an interface. The interface defines the type, perhaps providing some default methods, while the skeletal implementation class implements the remaining non-primitive interface methods atop the primitive interface methods. By convention, skeletal implementation classes are called `AbstractInterface` where *Interface* is the name of the interface they implement.
