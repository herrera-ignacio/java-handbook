# 85: Prefer alternatives to Java serialization

* A fundamental problem with serialization is that **its _attack surface_ is too big to protect, and constantly growing**: Object graphs are deserialized by invoking the `readObject` method on an `ObjectInputStream`. This method is essentially a magic constructor that can be made to instantiate objects of almost any type on the class path, so long as the type implements the `Serializable` interface. In the process of deserializing a byte stream, this method can execute code from any of these types, so the code for *all* of these types is part of the attack surface.

> "Java deserialization is a clear and present dangar as it is widely used both directly by applications and indirectly by Java subsystems such as RMI (Remote Method Invocation), JMX (Java Management Extension), and JMS (Java Messaging System). Deserialization of untrusted streams can result in remote code execution (RCE), denial-of-service (DoS), and a range of other exploits. Applications can be vulnerable to these attacks even if they did nothing wrong." - Robert Seacord, technical manager of the CERT Coordination Center

* The best way to avoid serialization exploits is never to deserialize anything. **There is no reason to use Java serialization in any new system you write**. There are other mechanisms for translating between objects and byte sequences that avoid many of the dangers of Java serialization, while offering numerous advantages, such as cross-platform support, high performance, a large ecosystem of tools, and a broad community of expertise.

  * The leading cross-platform structured data representations are JSON (text-based and human-readable) and Protocol Buffers (binary and more efficient), also known as protobuf.

* If you can't avoid Java serialization entirely, perhapse because you're working in the context of a legacy system that requires it, your next best alternative is to **never deserialize untrusted data**.

* Use the object deserialization filtering added in Java 9 and backported to earlier releases (`java.io.ObjectInputFilter`). This facility lets you specify a filter that is applied to data streams before they're deserialized. **Prefer whitelisting to blacklisting**, as blacklisting only protects you against known treats.
