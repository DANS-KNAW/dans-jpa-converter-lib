Description
===========

Library with JPA attribute converters.

This library provides a set of ready-to-use JPA `AttributeConverter` implementations.

### JPA

These `AttributeConverter`s are used on classes that represent entities in a JPA (Java Persistence API) context, typically used in databases.

Example usage:

```java
@Entity(name = "MyEntity")
@Table(name = "my_entity")
public class MyEntity {
    @Convert(converter = UriConverter.class)
    private URI uri;
}
```

The JPA converters are located in the `nl.knaw.dans.convert.jpa` package.

This allows you to store a URI in a database as a string instead of as a serialized object.

Using the library
-----------------

To use this library in a Maven-based project, add the following to your `pom.xml`.

### 1. Declare the DANS maven repository

```xml

<repositories>
    <!-- possibly other repository declarations here ... -->
    <repository>
        <id>DANS</id>
        <releases>
            <enabled>true</enabled>
        </releases>
        <url>https://maven.dans.knaw.nl/releases/</url>
    </repository>
</repositories>
```

### 2. Include a dependency on this library

```xml

<dependency>
    <groupId>nl.knaw.dans</groupId>
    <artifactId>dans-jpa-converter-lib</artifactId>
    <version>{version}</version> <!-- <=== FILL LIBRARY VERSION TO USE HERE -->
</dependency>
```
