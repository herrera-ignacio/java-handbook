# Injecting Properties

## Core Properties

![](2021-06-06-21-03-56.png)

## Custom Properties

You only need to set the properties you want in `application.properties` such as:

```
coach.name=Nacho Herrera
```

And then inject them as:

```java
import org.springframework.beans.factory.annotation.Value;

/*
...
*/

@Value("${coach.name}")
```