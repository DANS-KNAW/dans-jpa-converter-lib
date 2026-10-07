Getting started
===============

Adding the dependency
----------------------

To use this library in a Maven project, add the following to your `pom.xml`:

```xml

<dependency>
    <groupId>nl.datastations</groupId>
    <artifactId>dans-jpa-converter-lib</artifactId>
    <version>{{ project_version }}</version>
</dependency>
```

Using a converter
------------------

The converters in this library are used on entity fields annotated with JPA's `@Convert`. For example, `UriConverter` allows you to store a `URI` in a
database as a string instead of as a serialized object.

```java
import java.net.URI;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

import nl.knaw.dans.convert.jpa.UriConverter;

@Entity(name = "MyEntity")
@Table(name = "my_entity")
public class MyEntity {

    @Convert(converter = UriConverter.class)
    private URI uri;
}
```
