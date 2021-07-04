# Exporting Libraries

* Gradle
  * Export to Maven Repository
* IntelliJ IDEA
* Maven
  * Importing from local Maven repository

## Gradle

> You need to do this step for being able to build the project using gradle.

* Export your library to a JAR file and place it in your project `libs` folder (if it doesn't exist, create it).

* Add the following to your `build.gradle` file.

```yml
buildscript {
    ...
}
...

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
}
```

* Reload Gradle Project.

### Export to Maven Repository

See the latest docs on how to publish to maven: https://docs.gradle.org/current/userguide/publishing_maven.html

1. Modiy your `build.gradle` for publishing to local maven repository. The following is an example working by the time this document got created:

```groovy
plugins {
    id 'java-library'
    id 'maven-publish'
}

group = 'irh.datastructures'
version = '1.0'

repositories {
    mavenCentral()
}

publishing {
    publications {
        maven(MavenPublication) {
            groupId = 'irh.datastructures'
            artifactId = 'minheap'
            version = '1.0'

            from components.java
        }
    }
}

dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
}

test {
    useJUnitPlatform()
}
```

2. You should then execute `Gradle -> Tasks -> publishing -> publishToMavenLocal` task, and the artifact will be saved in your local repository (usually `~/.m2`).

3. From the project you want to import the library from, add this to the `build.gradle` file:

```groovy
  repositories {
    mavenCentral()
    mavenLocal()
  }

  dependencies {
    ...
    implementation 'irh.datastructures:minheap:1.0' 
  }
```

## InteliJ IDEA

> You need to this step for IntelliJ to be able to resolve symbols.

* Right click your `libs` folder with your JARs in it.

* Click *"Add as Library"*. This is equivalent to adding the library in `File -> Project Structure -> Library`, then right click on it and `Add to Module`.

* Reload Gradle Project. 

## JAR with Gradle and Maven Local Repository

* Export your library to Maven Local Repository ([walktrough](https://www.jetbrains.com/help/idea/add-a-gradle-library-to-the-maven-repository.html#4b97311e)).

* Add the local Maven cache as a repository in `build.gradle`

```yml
repositories {
  mavenLocal()
}
```

## Maven and Local Repository

1. Install manually the JAR into a local Maven repository.

```
mvn install:install-file -Dfile=<path-to-file>

mvn install:install-file –Dfile=C:\dev\app.jar -DgroupId=com.roufid.tutorials -DartifactId=example-app -Dversion=1.0
```

2. Add the dependency to your Maven project in your `pom.xml` file.

```xml
    <dependency>

        <groupId>com.roufid.tutorials</groupId>

        <artifactId>example-app</artifactId>

        <version>1.0</version>

    </dependency>
```

Alternatively, you could directly the dependency as system scope, considering that the library lis located in `<project_root>/libs`:

```xml
    <dependency>

        <groupId>com.roufid.tutorials</groupId>

        <artifactId>example-app</artifactId>

        <version>1.0</version>

        <scope>system</scope>

        <systemPath>${basedir}/libs/yourJar.jar</systemPath>

    </dependency>
```

### Importing from local Maven repository

1. Deploy JARs in the new local maven repository

```
mvn deploy:deploy-file -Dfile=<path-to-file> -DgroupId=<group-id> 
```

2. Add dthe repository and dependency to your `pom.xml` file.

```xml
    <repositories>

        <repository>

            <id>maven-repository</id>

            <url>file:///${project.basedir}/maven-repository</url>

        </repository>

    </repositories>

    <dependency>

        <groupId>com.roufid.tutorials</groupId>

        <artifactId>example-app</artifactId>

        <version>1.0</version>

    </dependency>
```
