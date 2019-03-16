package ru.ifmo.rain.ibragimov.iterative;

import info.kgeorgiy.java.advanced.concurrent.ListIP;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IterativeParallelism implements ListIP {

    private int threadsNumber;
    private int eachCount;
    private int restCount;
    private List<Thread> threads;

    private void joinThreads(final List<Thread> threads) throws InterruptedException {
        for (Thread thread: threads) {
            thread.join();
        }
    }

    private <T> void validateInput(int threadsNumbers, List<? extends T> values) {
        if (threadsNumbers <= 0) throw new IllegalArgumentException("Amount of threads but me at least 1.");
        Objects.requireNonNull(values);
    }

    private <T, R> void addThread(List<Thread> threads, List<R> values, final int index, Stream<? extends T> stream, Function<Stream<? extends T>, R> mapper) {
        var thread = new Thread(() -> values.set(index, mapper.apply(stream)));
        thread.start();
        threads.add(thread);
    }

    private <T> void init(int i, List<? extends T> list) {
        validateInput(i, list);
        threadsNumber = Math.max(1, Math.min(i, list.size()));
        threads = new ArrayList<>();
        int count = list.size();
        eachCount = count / threadsNumber;
        restCount = count % threadsNumber;
    }

    private <T, R> void addThreads(List<R> values, List<? extends T> list, Function<Stream<? extends T>, R> mapper) {
        for (int j = 0, r = 0; j < threadsNumber; j++) {
            final var l = r;
            r = l + eachCount + (restCount-- > 0 ? 1 : 0);
            addThread(threads, values, j, list.subList(l, r).stream(), mapper);
        }
    }

    private <T, R> R task(int i, List<? extends T> list, Function<Stream<? extends T>, R> mapper, Function<Stream<R>, R> resultGrabber) throws InterruptedException {
        init(i, list);
        var localExtrema = new ArrayList<R>(Collections.nCopies(threadsNumber, null));
        addThreads(localExtrema, list, mapper);
        joinThreads(threads);
        return resultGrabber.apply(localExtrema.stream());
    }

    @Override
    public <T> T maximum(int i, List<? extends T> list, Comparator<? super T> comparator) throws InterruptedException {
        return task(i, list, stream -> stream.max(comparator).orElseThrow(), stream -> stream.max(comparator).orElseThrow());
    }

    @Override
    public <T> T minimum(int i, List<? extends T> list, Comparator<? super T> comparator) throws InterruptedException {
        return maximum(i, list, Collections.reverseOrder(comparator));
    }

    @Override
    public <T> boolean all(int i, List<? extends T> list, Predicate<? super T> predicate) throws InterruptedException {
        return task(i, list, stream -> stream.allMatch(predicate), stream -> stream.allMatch(item -> item));
    }

    @Override
    public <T> boolean any(int i, List<? extends T> list, Predicate<? super T> predicate) throws InterruptedException {
        return !all(i, list, predicate.negate());
    }

    @Override
    public String join(int i, List<?> list) throws InterruptedException {
        return task(i, list, stream -> stream.map(Object::toString).collect(Collectors.joining()), stream -> stream.collect(Collectors.joining()));
    }

    @Override
    public <T> List<T> filter(int i, List<? extends T> list, Predicate<? super T> predicate) throws InterruptedException {
        return task(i, list, stream -> stream.filter(predicate).collect(Collectors.toList()), listStream -> listStream.flatMap(Collection::stream).collect(Collectors.toList()));
    }

    @Override
    public <T, U> List<U> map(int i, List<? extends T> list, Function<? super T, ? extends U> function) throws InterruptedException {
        return task(i, list, stream -> stream.map(function).collect(Collectors.toList()), listStream -> listStream.flatMap(Collection::stream).collect(Collectors.toList()));
    }
}
