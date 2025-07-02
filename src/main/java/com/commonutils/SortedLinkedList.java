package com.commonutils;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;

/**
 * A sorted linked list that maintains elements in sorted order.
 * Elements must implement Comparable interface.
 *
 * @param <E> the type of elements in this list, which must be comparable
 */
public class SortedLinkedList<E extends Comparable<E>> {
    private final LinkedList<E> inner = new LinkedList<>();
    private final Comparator<? super E> comparator;

    public SortedLinkedList() {
        this(Comparator.naturalOrder());
    }
    public SortedLinkedList(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    /**
     * Inserts the specified element into this list in its sorted position.
     *
     * @param element the element to insert; must not be null
     * @return {@code true} (as specified by {@link java.util.Collection#add})
     * @throws NullPointerException if {@code element} is null
     */
    public boolean add(E element) {
        Objects.requireNonNull(element);

        int index = 0;
        while (index < inner.size() && comparator.compare(inner.get(index), element) < 0) {
            index++;
        }
        inner.add(index, element);
        return true;
    }

    /**
     * Removes a single instance of the specified element from this list, if present.
     *
     * @param element element to be removed from this list; must not be null
     * @return {@code true} if the list contained the specified element
     * @throws NullPointerException if {@code element} is null
     */
    public boolean remove(E element) {
        Objects.requireNonNull(element);
        return inner.remove(element);
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     *
     * @param element element whose presence in this list is to be tested; must not be null
     * @return {@code true} if this list contains the specified element
     * @throws NullPointerException if {@code element} is null
     */
    public boolean contains(E element) {
        Objects.requireNonNull(element);
        return inner.contains(element);
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return inner.size();
    }

    /**
     * Retrieves, but does not remove, the last element of this list, if present.
     *
     * @return an {@link Optional} describing the last element, or an empty Optional if the list is empty
     */
    public Optional<E> firstOptional() {
        return inner.isEmpty()
                ? Optional.empty()
                : Optional.of(inner.getFirst());
    }

    /**
     * Retrieves, but does not remove, the last element of this list, if present.
     *
     * @return an {@link Optional} describing the last element, or an empty Optional if the list is empty
     */
    public Optional<E> lastOptional() {
        return inner.isEmpty()
                ? Optional.empty()
                : Optional.of(inner.getLast());
    }

    /**
     * Retrieves and removes the first element of this list, if present.
     *
     * @return an {@link Optional} describing the removed first element, or an empty Optional if the list is empty
     */
    public Optional<E> pollFirstOptional() {
        if (inner.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(inner.removeFirst());
    }

    /**
     * Retrieves and removes the last element of this list, if present.
     *
     * @return an {@link Optional} describing the removed last element, or an empty Optional if the list is empty
     */
    public Optional<E> pollLastOptional() {
        if (inner.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(inner.removeLast());
    }

}
