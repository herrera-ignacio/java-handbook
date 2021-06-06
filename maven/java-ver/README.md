# Setting up Java Version in Maven

After you created a project using Maven, you might need to change the Java version. For that you need to modify `pom.xml` with the following:

```xml
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <!-- IMPORTANT LINES: Tell Maven to use Java 8 -->
    <maven.compiler.target>1.8</maven.compiler.target> 
    <maven.compiler.source>1.8</maven.compiler.source>
     <!-- IMPORTANT LINES -->
  </properties>
```

And after that, update the project, for example from Eclipse: `Right Click Project -> Maven -> Update Project`.