# 66: Use native methods judiciously

> The *Java Native Interface (JNI)* allows Javfa programs to call *native methods*, which are written in *native programming languages* such as C or C++. 

* Historically, native methods have had three main uses:
  * Provide access to platform-specific facilities such as registries.
  * Provide access to existing libraries of native code, including legacy libraries that provide access to legacy data.
  * Write performance-critical parts of applications.

* It is seldom necessary to use native methods to access platform-specific facilities: as the Java platform matured, it provides access to many features previously found only in host platforms.

* It is **rarely advisable to use native methods for improved performance**. JVMs have gotten *much* faster, and for most tasks, it is now possible to obtain comparable performance in Java.l

* The use of native methods has **serious disadvantages**:

  * Native languages are not *safe* ([Item 50](../50)), so applications using native methods are no longer immune to memory corruption errors.
  * Applications become less portable and harder to debug.
  * Can't decrease performance because the garbage collector can't automate, or even track, native memory usage, and there is a cost associated with going into and out of native code.
  * Native methods require "glue code" that is difficult to read and tedious to write.
