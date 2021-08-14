# 29: Favor generic types

* Non-parameterized classes may force clients to cast objects, and those casts might fail at runtime.

* Classes can be *generified* after original implementation, parameterizing it without harming clients of the original non-parameterized version.
