package ru.ifmo.rain.ibragimov.mapper;

import info.kgeorgiy.java.advanced.mapper.ParallelMapper;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

public class ParallelMapperImpl implements ParallelMapper {

    private final Queue<Runnable> tasks;
    private final List<Thread> consumers;
    private final Lock lock;
    private final Condition queueNotEmpty;
    private final Condition queueNotFull;
    private int threadsNumber;

    private static final int MAX_SIZE = 100;

    private void initWorkers() {
        for (var i = 0; i < threadsNumber; i++) {
            var consumer = new Thread(() -> {
                Runnable task;
                while (!Thread.interrupted()) {
                    lock.lock();
                    try {
                        while (tasks.isEmpty()) {
                            try {
                                queueNotEmpty.await();
                            } catch (InterruptedException ignored) { return; }
                        }
                        task = tasks.poll();
                        queueNotFull.signalAll();
                    } finally {
                        lock.unlock();
                    }
                    task.run();
                }
            });
            consumer.start();
            consumers.add(consumer);
        }
    }

    private void validateInput(int threadsNumbers) {
        if (threadsNumbers <= 0) throw new IllegalArgumentException("Amount of threads must be at least 1.");
    }

    public ParallelMapperImpl(int tNum) {
        validateInput(tNum);
        threadsNumber = tNum;
        lock = new ReentrantLock();
        queueNotEmpty = lock.newCondition();
        queueNotFull = lock.newCondition();
        consumers = new ArrayList<>(threadsNumber);
        tasks = new ArrayDeque<>();
        initWorkers();
    }

    private <T, R> void addTask(Function<? super T, ? extends R> function, T item, int index, List<R> result, CountDownLatch countDownLatch) throws InterruptedException {
        Runnable task = () -> {
            result.set(index, function.apply(item));
            countDownLatch.countDown();
        };
        lock.lock();
        try {
            while (tasks.size() == MAX_SIZE) {
                queueNotFull.await();
            }
            tasks.add(task);
            queueNotEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public <T, R> List<R> map(Function<? super T, ? extends R> function, List<? extends T> list) throws InterruptedException {
        var result = new ArrayList<R>(list.size());
        var countDownLatch = new CountDownLatch(list.size());
        result.addAll(Collections.nCopies(list.size(), null));
        for (var i = 0; i < list.size(); i++) {
            addTask(function, list.get(i), i, result, countDownLatch);
        }
        countDownLatch.await();
        return result;
    }

    @Override
    public void close() {
        for (var worker : consumers) {
            worker.interrupt();
        }
    }
}