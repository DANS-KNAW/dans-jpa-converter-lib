Description
===========

Library with JavaBean validator annotations.

This library provides a set of ready-to-use converters for Jackson and JPA.

!!! danger "Two types of converters"
    There are two types of converters in this library: Jackson and JPA. They have been put in one library for convenience, but they are not interchangeable.

### Jackson

These converters are used on classes that represent JSON objects and are serialized/deserialized by Jackson, typically used in configuration files and DTOs
(Data Transfer Objects).

Example usage:

```java
public class MyPojo {
    @JsonProperty("size")
    @JsonDeserialize(converter = StringByteSizeConverter.class)
    private long sizeInBytes;
}
```

This allows you to specify the byte size of a field in a JSON object as a string, e.g. `"1MB"`, `"1GB"`, etc.

The Jackson convert are located in the `nl.knaw.dans.convert.jackson` package.

### JPA

These converters are used on classes that represent entities in a JPA (Java Persistence API) context, typically used in databases.

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
    <artifactId>dans-converter-lib</artifactId>
    <version>{version}</version> <!-- <=== FILL LIBRARY VERSION TO USE HERE -->
</dependency>
```
