package queue;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ArrayQueue extends AbstractQueue {
    private static final int DEFAULT_SIZE = 5;
    private int maxSize;
    private Object[] queue;
    private int head = 0;
    private int tail = 0;

    private void grow() {
        maxSize *= 2;
        queue = Arrays.copyOf(queue, maxSize);
    }

    public void clearQueue() {
        head = 0;
        tail = 0;
    }

    public void Enqueue(Object item) {
        queue[tail] = item;
        if (tail + 1 == maxSize) {
            grow();
        }
        tail = (tail + 1) % maxSize;
    }

    public void remove() {
        head = (head + 1) % maxSize;
    }

    public Object Element() {
        return queue[head];
    }

    public ArrayQueue() {
        this(DEFAULT_SIZE);
    }

    public ArrayQueue(int size) {
        maxSize = size;
        Object[] t = new Object[maxSize];
        queue = t;
    }

}
