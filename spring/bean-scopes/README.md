# Bean Lifecycle && Scopes

## Lifecycle

1. Container Started
2. Bean instantiated
3. Dependencies Injected
4. Internal Spring Processing
5. Custom Init Method
6. Bean is ready for use
7. Container shutdown
8. Container is shutdown

### Hooks

You can add custom code (__no-arg methods__ of the bean) during __bean initialization and destruction__.

* Custom business logic methods.
* Set/clean up handles to resources (db, sockets, file, etc).

```xml
<bean id="myCoach"
    class="foo.bar"
    scope="prototype"
    init-method="myStartUpLogic"
    destroy-method="myCleanUpLogic"
>
    <!-- code -->
</bean>
```

> For "prototype" scoped beans, Spring does not call the destroy method.

## Scope

Bean Scope means to __the lifecycle of a bean__.

```xml
<brean id="myCoach" class="foo.bar" scope="prototype">
    <!-- code -->
</bean>
```

### Default Scope: Singleton

Default scope for a bean is _Singleton_, meaning that Spring Container creates only one instance of the bean, it is cached in memory, thus all requests for the brean will return a shared reference to the same bean.

### Additional Scopes

* __singleton__: Default scope.
* __prototype__: Creates a new bean instance for each container request.
* __request__: Scoped to an HTTP web request.
* __session__: Scoped to an HTTP web session.
* __global-session__: Scoped to a global HTTP web session.
