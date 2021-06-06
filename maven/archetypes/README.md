# Maven Archetypes

> Archetypes contain template files ("starter files") that can be used to create new Maven projects.

You can find a list of Maven Archetypes here: https://maven.apache.org/archetypes.

## What is Archetype?

Archetype is a Maven *project templating toolkit*. An archetype is defined as an *original pattern or model from which all other things of the same kind are made*.

Maven Archetypes provide users with the means to generate parameterized versions of project templates. This provides a great way to enable developers quickly in a way consistent with best practices employed by your project or organization.

Archetypes mechanism attempts to be *additive*, by allowing portions of a project to be captured in aan aarchetype so that pieces  or asspects o f a project can be addeed to existing projects.

## Using an Archetype

To create a new project based on an Archetype you use the following blueprint:

```
mvn archetype:generate
```

Some IDEs such as Eclipse, INtelliJ, and NetBeans have this integrated.

> In Eclipse: `File > New > Other > Maven Project`. Some versions may require manual installation of the *m2e (Maven Integration for Eclipse)* and/or *m2e-wtp* plugin `Help > Install New Software`.

## What makes up an Archetype

Archetypes are packaged up in a JAR and they consist of the archetype metadata which describes its contents, and a set of *Velocity* (Java-based template engine) templates which make up the prototype project.
