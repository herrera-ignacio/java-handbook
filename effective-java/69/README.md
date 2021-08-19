# 69: Use exceptions only for exceptional conditions

* Exceptions are, as their name implies, to be used only for exceptional conditions; they should never be used for ordinary control flow.

* Use standard, easily recognizable idioms in preference to overly clever techniques that purport to offer better performance. Even if the performance advantage is real, it may not remain in the face of steadily improving platform implementations. The subtle bugs and maintenance headaches, however, are sure to maintain.

* A well-designed API must not force its clients to use exceptions for ordinary control flow. 
