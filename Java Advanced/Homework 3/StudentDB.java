package ru.ifmo.rain.ibragimov.student;

import info.kgeorgiy.java.advanced.student.Group;
import info.kgeorgiy.java.advanced.student.Student;
import info.kgeorgiy.java.advanced.student.StudentGroupQuery;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentDB implements StudentGroupQuery {

    @SafeVarargs
    private <T> Comparator<T> getComparatorByCriterion(Comparator<T> first, Comparator<T>... comparators) {
        return Arrays.stream(comparators).reduce(first, Comparator::thenComparing);
    }

    private Stream<Map.Entry<String, List<Student>>> getGroupsStream(Collection<Student> collection, Supplier<Map<String, List<Student>>> generator) {
        return collection.stream().collect(Collectors.groupingBy(Student::getGroup, generator, Collectors.toList())).entrySet().stream();
    }

    private List<Group> getGroupsBy(Comparator<? super Student> comparator, Stream<Map.Entry<String, List<Student>>> groupStream) {
        return groupStream.map(entry -> new Group(entry.getKey(), entry.getValue().stream().sorted(comparator).collect(Collectors.toList()))).collect(Collectors.toList());
    }

    private List<Group> getSortedGroups(Comparator<? super Student> comparator, Collection<Student> collection, Supplier<Map<String, List<Student>>> generator) {
        return getGroupsBy(comparator, getGroupsStream(collection, generator));
    }

    private String getGroupNameByCriteria(Comparator<Map.Entry<String, List<Student>>> criteria, Collection<Student> collection, Supplier<Map<String, List<Student>>> generator) {
        return getGroupsStream(collection, generator).min(criteria).orElseThrow().getKey();
    }

    private List<String> mappedBy(Function<Student, String> mapper, Collection<Student> collection) {
        return collection.stream().map(mapper).collect(Collectors.toList());
    }

    private List<Student> filteredBy(Predicate<Student> predicate, Comparator<Student> comparator, Collection<Student> collection) {
        return collection.stream().filter(predicate).sorted(comparator).collect(Collectors.toList());
    }

    private List<Student> sortedBy(Comparator<? super Student> comparator, Collection<Student> collection) {
        return collection.stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public List<Group> getGroupsByName(Collection<Student> collection) {
        return getSortedGroups(getComparatorByCriterion(Comparator.comparing(Student::getLastName), Comparator.comparing(Student::getFirstName), Student::compareTo), collection, TreeMap::new);
    }

    @Override
    public List<Group> getGroupsById(Collection<Student> collection) {
        return getSortedGroups(getComparatorByCriterion(Student::compareTo), collection, TreeMap::new);
    }

    @Override
    public String getLargestGroup(Collection<Student> collection) {
        return getGroupNameByCriteria(getComparatorByCriterion(Comparator.comparing(g -> -g.getValue().size()), Comparator.comparing(Map.Entry::getKey)), collection, HashMap::new);
    }

    @Override
    public String getLargestGroupFirstName(Collection<Student> collection) {
        return getGroupNameByCriteria(getComparatorByCriterion(Comparator.comparing(g -> -getDistinctFirstNames(g.getValue()).size()), Comparator.comparing(Map.Entry::getKey)), collection, HashMap::new);
    }

    @Override
    public List<String> getFirstNames(List<Student> list) {
        return mappedBy(Student::getFirstName, list);
    }

    @Override
    public List<String> getLastNames(List<Student> list) {
        return mappedBy(Student::getLastName, list);
    }

    @Override
    public List<String> getGroups(List<Student> list) {
        return mappedBy(Student::getGroup, list);
    }

    @Override
    public List<String> getFullNames(List<Student> list) {
        return mappedBy(s -> String.format("%s %s", s.getFirstName(), s.getLastName()), list);
    }

    @Override
    public Set<String> getDistinctFirstNames(List<Student> list) {
        return new HashSet<>(mappedBy(Student::getFirstName, list));
    }

    @Override
    public String getMinStudentFirstName(List<Student> list) {
        return list.stream().min(Student::compareTo).orElseThrow().getFirstName();
    }

    @Override
    public List<Student> sortStudentsById(Collection<Student> collection) {
        return sortedBy(Comparator.naturalOrder(), collection);
    }

    @Override
    public List<Student> sortStudentsByName(Collection<Student> collection) {
        return sortedBy(getComparatorByCriterion(Comparator.comparing(Student::getLastName), Comparator.comparing(Student::getFirstName), Student::compareTo), collection);
    }

    @Override
    public List<Student> findStudentsByFirstName(Collection<Student> collection, String s) {
        return filteredBy(student -> student.getFirstName().equals(s), getComparatorByCriterion(Comparator.comparing(Student::getFirstName)), collection);
    }

    @Override
    public List<Student> findStudentsByLastName(Collection<Student> collection, String s) {
        return filteredBy(student -> student.getLastName().equals(s), getComparatorByCriterion(Comparator.comparing(Student::getFirstName)), collection);
    }

    @Override
    public List<Student> findStudentsByGroup(Collection<Student> collection, String s) {
        return filteredBy(student -> student.getGroup().equals(s), getComparatorByCriterion(Comparator.comparing(Student::getFirstName), Student::compareTo), collection);
    }

    @Override
    public Map<String, String> findStudentNamesByGroup(Collection<Student> collection, String groupName) {
        return collection.stream().filter(student -> student.getGroup().equals(groupName)).collect(Collectors.toMap(Student::getLastName, Student::getFirstName, (s1, s2) -> s1));
    }
}
