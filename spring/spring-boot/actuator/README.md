# Spring Boot Actuator

* Overview
* Endpoints configuration
* Security

## Overview

Exposes endpoints to monitor and manage your application out-of-the-box. 

Simply add the dependency to your POM file and REST endpoints are automatically configured.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

## Endpoints configuration

By default only `/actuator/health` and `/actuator/info` are exposed. If you want to expose others, you should configure `src/main/resources/application.properties`.

```py
# Can expose individual endpoints with a comma-delimited list
management.endpoints.web.exposure.include=*

# Can exclude individual endpoints too
management.endpoints.web.exposure.eclude=info
```

* /actuator
  * /health
  * /info
  * /auditevents
  * /beans
  * /mappings
  * ...

## Security

Default user name is **user** and check console logs for password.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

You can override default credentials in `application.properties`.

```
spring.security.user.name=nacho
spring.security.user.password=mypassword
```

You can also customize Spring Security for Spring Boot Actuator (using a database for roles, encrypted passwords, etc...) by following the same techniques as regular Spring Security.