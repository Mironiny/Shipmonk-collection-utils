package com.commonutils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SortedLinkedListTest {

    private SortedLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new SortedLinkedList<>();
    }

    @Test
    void testAddElementsNaturalOrder() {
        // Add elements in random order
        list.add(5);
        list.add(2);
        list.add(8);
        list.add(1);
        list.add(3);

        // Verify they are stored in sorted order
        assertEquals(1, list.firstOptional().orElseThrow());
        assertEquals(8, list.lastOptional().orElseThrow());

        // Verify size
        assertEquals(5, list.size());
    }

    @Test
    void testAddElementsWithCustomComparator() {
        // Create list with reverse order comparator
        SortedLinkedList<Integer> reverseList = new SortedLinkedList<Integer>(Comparator.reverseOrder());

        // Add elements
        reverseList.add(5);
        reverseList.add(2);
        reverseList.add(8);
        reverseList.add(1);

        // Verify they are stored in reverse order
        assertEquals(8, reverseList.firstOptional().orElseThrow());
        assertEquals(1, reverseList.lastOptional().orElseThrow());
    }

    @Test
    void testAddDuplicateElements() {
        list.add(5);
        list.add(5);
        list.add(5);

        assertEquals(3, list.size());
    }

    @Test
    void testRemoveElement() {
        // Add elements
        list.add(5);
        list.add(2);
        list.add(8);

        // Remove element
        assertTrue(list.remove(5));
        assertEquals(2, list.size());
        assertFalse(list.contains(5));

        // Try to remove non-existent element
        assertFalse(list.remove(10));
    }

    @Test
    void testContainsElement() {
        list.add(5);
        list.add(2);

        assertTrue(list.contains(5));
        assertTrue(list.contains(2));
        assertFalse(list.contains(10));
    }

    @Test
    void testEmptyList() {
        // Test empty list behavior
        assertTrue(list.firstOptional().isEmpty());
        assertTrue(list.lastOptional().isEmpty());
        assertTrue(list.pollFirstOptional().isEmpty());
        assertTrue(list.pollLastOptional().isEmpty());
        assertEquals(0, list.size());
        assertFalse(list.contains(1));
        assertFalse(list.remove(1));
    }

    @Test
    void testPollFirstOptional() {
        list.add(5);
        list.add(2);
        list.add(8);

        // Poll first element (should be 2)
        Optional<Integer> first = list.pollFirstOptional();
        assertTrue(first.isPresent());
        assertEquals(2, first.get());

        // Verify element was removed
        assertEquals(2, list.size());
        assertFalse(list.contains(2));
    }

    @Test
    void testPollLastOptional() {
        list.add(5);
        list.add(2);
        list.add(8);

        // Poll last element (should be 8)
        Optional<Integer> last = list.pollLastOptional();
        assertTrue(last.isPresent());
        assertEquals(8, last.get());

        // Verify element was removed
        assertEquals(2, list.size());
        assertFalse(list.contains(8));
    }

    @Test
    void testNullHandling() {
        // Adding null should throw NullPointerException
        assertThrows(NullPointerException.class, () -> list.add(null));

        // Other operations with null should also throw NullPointerException
        assertThrows(NullPointerException.class, () -> list.remove(null));
        assertThrows(NullPointerException.class, () -> list.contains(null));
    }

    @Test
    void testSortingOrder() {
        // Add elements in descending order
        list.add(10);
        list.add(8);
        list.add(6);
        list.add(4);
        list.add(2);

        // Verify they are stored in ascending order
        assertEquals(2, list.firstOptional().orElseThrow());
        assertEquals(10, list.lastOptional().orElseThrow());

        // Poll elements one by one and verify they come out in sorted order
        assertEquals(2, list.pollFirstOptional().orElseThrow());
        assertEquals(4, list.pollFirstOptional().orElseThrow());
        assertEquals(6, list.pollFirstOptional().orElseThrow());
        assertEquals(8, list.pollFirstOptional().orElseThrow());
        assertEquals(10, list.pollFirstOptional().orElseThrow());

        // List should be empty now
        assertTrue(list.firstOptional().isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testCustomStringComparator() {
        // Create a list that sorts strings by length (shortest first)
        SortedLinkedList<String> stringList = new SortedLinkedList<String>(
            Comparator.comparingInt(String::length)
        );

        // Add strings of different lengths
        stringList.add("elephant");  // 8 chars
        stringList.add("cat");       // 3 chars
        stringList.add("dog");       // 3 chars
        stringList.add("rhinoceros"); // 10 chars
        stringList.add("ant");       // 3 chars

        // Verify they are sorted by length
        String first = stringList.firstOptional().orElseThrow();
        String last = stringList.lastOptional().orElseThrow();

        // First should be one of the 3-char strings
        assertEquals(3, first.length());
        // Last should be the longest string
        assertEquals("rhinoceros", last);

        // Poll all 3-char strings (there are 3 of them)
        int count3Char = 0;
        for (int i = 0; i < 3; i++) {
            String polled = stringList.pollFirstOptional().orElseThrow();
            assertEquals(3, polled.length());
            count3Char++;
        }
        assertEquals(3, count3Char);

        // Next should be the 8-char string
        String next = stringList.pollFirstOptional().orElseThrow();
        assertEquals(8, next.length());
        assertEquals("elephant", next);

        // Last should be the 10-char string
        String lastPolled = stringList.pollFirstOptional().orElseThrow();
        assertEquals(10, lastPolled.length());
        assertEquals("rhinoceros", lastPolled);

        // List should be empty now
        assertTrue(stringList.firstOptional().isEmpty());
    }

    @Test
    void testWithCustomClass() {
        // Test with a custom class that implements Comparable
        SortedLinkedList<Person> personList = new SortedLinkedList<>();

        Person alice = new Person("Alice", 30);
        Person bob = new Person("Bob", 25);
        Person charlie = new Person("Charlie", 35);

        personList.add(alice);
        personList.add(bob);
        personList.add(charlie);

        // Verify they are sorted by age (as defined in Person.compareTo)
        assertEquals(bob, personList.firstOptional().orElseThrow());
        assertEquals(charlie, personList.lastOptional().orElseThrow());
    }

    // Custom class for testing
    private static class Person implements Comparable<Person> {
        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Person other) {
            return Integer.compare(this.age, other.age);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age && name.equals(person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }
}
