# Spring configuration with Java Code

## Development

1. Create a Java class and annotate as `@Configuration`.
2. (optional) Add component scanning support with `@ComponentScan`.
3. Read Spring Java configuration class `AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyClass.class);`.
4. Retrieve bean from Spring container `MyClass obj = context.getBean("myClass", MyClass.class);`.
