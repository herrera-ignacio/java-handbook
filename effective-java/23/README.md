# 23: Prefer class hierarchies to tagged classes

Ocasionally you may run across a class whose instances come in two or more flavors and contain a *tag* field indicating the flavor of the instance. **Tagged classes are verbose, error-prone, and inefficient**, it is just a pallid imitation of a class hierarchy.

* Readibility is harmed because multiple implementations are jumbled together in a single class.

* Memory footprint is increased because instances are burdened with irrelevant fields belonging to other flavors.
 
* Fields can't be made `final` unless constructors initialize irrelevant fields.

* Constructors must set the tag field and initialzie the right data fields with no help from the compiler: if you initialize the wrong fields, the program will fail at runtime.
