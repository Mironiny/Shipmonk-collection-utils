# Common Collection Utils

A small library of reusable Java collection utilities, including sorted-linked-list implementations.

## Features

- **SortedLinkedList**: A generic sorted linked list that maintains elements in sorted order
- Support for natural ordering and custom comparators
- Type-safe operations with null-safety
- Comprehensive test coverage

## Requirements

- Java 21 or higher
- Maven 3.6+

## Installation

Add this dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>cz.commonutils</groupId>
    <artifactId>common-collection-utils</artifactId>
    <version>1.0.0</version>
</dependency>
```

## API Reference

### SortedLinkedList<E>

#### Constructors
- `SortedLinkedList()` - Creates a list with natural ordering
- `SortedLinkedList(Comparator<? super E> comparator)` - Creates a list with custom comparator

#### Methods
- `boolean add(E element)` - Inserts element in sorted position
- `boolean remove(E element)` - Removes first occurrence of element
- `boolean contains(E element)` - Checks if element exists
- `int size()` - Returns number of elements
- `Optional<E> firstOptional()` - Returns first element without removing
- `Optional<E> lastOptional()` - Returns last element without removing
- `Optional<E> pollFirstOptional()` - Returns and removes first element
- `Optional<E> pollLastOptional()` - Returns and removes last element

## Building

```bash
# Compile the project
mvn compile

# Run tests
mvn test

# Create JAR
mvn package
```

## Testing

The library includes comprehensive unit tests covering:
- Natural and custom ordering
- Null safety
- Empty list behavior
- Custom objects
- Edge cases

Run tests with:
```bash
mvn test
```

## License

Licensed under the Apache License, Version 2.0. See [LICENSE](https://www.apache.org/licenses/LICENSE-2.0.txt) for details.
