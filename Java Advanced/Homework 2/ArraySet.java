package ru.ifmo.rain.ibragimov.arrayset;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ArraySet<E> extends AbstractSet<E> implements SortedSet<E> {
    private static UnsupportedOperationException uoe() { return new UnsupportedOperationException(); }
    private static NoSuchElementException nse() { return new NoSuchElementException(); }

    // all mutating methods throw UnsupportedOperationException
    @Override public boolean add(E e) { throw uoe(); }
    @Override public boolean addAll(Collection<? extends E> c) { throw uoe(); }
    @Override public void    clear() { throw uoe(); }
    @Override public boolean remove(Object o) { throw uoe(); }
    @Override public boolean removeAll(Collection<?> c) { throw uoe(); }
    @Override public boolean removeIf(Predicate<? super E> filter) { throw uoe(); }
    @Override public boolean retainAll(Collection<?> c) { throw uoe(); }

    private final List<E> array;
    private final Comparator<? super E> comparator;

    public ArraySet() {
        this(Collections.emptyList(), null);
    }

    public ArraySet(Collection<? extends E> array, Comparator<? super E> comparator) {
        var treeSetWrapper = new TreeSet<E>(comparator);
        treeSetWrapper.addAll(array);
        this.array = new ArrayList<>(treeSetWrapper);
        this.comparator = comparator;
    }

    public ArraySet(Collection<? extends E> array) {
        this(array.stream().sorted().collect(Collectors.toList()), null);
    }

    public ArraySet(Comparator<? super E> comparator) {
        this(Collections.emptyList(), comparator);
    }

    public ArraySet(List<E> array, Comparator<? super E> comparator) {
        this.array = array;
        this.comparator = comparator;
    }

    @Override
    public Comparator<? super E> comparator() {
        return comparator;
    }

    private SortedSet<E> subSet(E fromElement, E toElement, boolean inclusive) {
        var from = Collections.binarySearch(array, Objects.requireNonNull(fromElement), comparator);
        var to = Collections.binarySearch(array, Objects.requireNonNull(toElement), comparator);
        if (from < 0) from = -from - 1;
        if (to < 0) to = -to - 1;
        if (from > to) return new ArraySet<>(Collections.emptyList(), comparator);
        return new ArraySet<>(array.subList(from, inclusive ? to + 1 : to), comparator);
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return subSet(fromElement, toElement, false);
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        if (isEmpty()) return new ArraySet<>(Collections.emptyList(), comparator);
        return subSet(first(), toElement, false);
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        if (isEmpty()) return new ArraySet<>(Collections.emptyList(), comparator);
        return subSet(fromElement, last(), true);
    }

    @Override
    public E first() {
        if (isEmpty()) throw nse();
        return array.get(0);
    }

    @Override
    public E last() {
        if (isEmpty()) throw nse();
        return array.get(size() - 1);
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(Object o) {
        return Collections.binarySearch(array, (E) Objects.requireNonNull(o), comparator) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return Collections.unmodifiableList(array).iterator();
    }

    @Override
    public Object[] toArray() {
        return array.toArray();
    }

    @SuppressWarnings("SuspiciousToArrayCall")
    @Override
    public <T> T[] toArray(T[] a) {
        return array.toArray(a);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

}
