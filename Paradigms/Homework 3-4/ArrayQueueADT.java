package queue;

import java.util.Arrays;

public class ArrayQueueADT {
    private static final int DEFAULT_SIZE = 5;
    private int maxSize;
    private Object[] array;
    private int head = 0;
    private int tail = 0;

    public static Object[] toArray(ArrayQueueADT queue) {
        Object[] result = queue.head < queue.tail ? Arrays.asList(queue.array).subList(queue.head, queue.tail).toArray() : queue.head == queue.tail ? new Object[0] : Arrays.asList(Arrays.asList(queue).subList(queue.tail, queue.maxSize), Arrays.asList(queue).subList(0, queue.head)).toArray();
        return result;
    }

    public static boolean isEmpty(ArrayQueueADT queue) {
        return ArrayQueueADT.size(queue) == 0;
    }

    public static int size(ArrayQueueADT queue) {
        if (queue.head > queue.tail) {
            return queue.maxSize - queue.head + queue.tail;
        }
        return queue.tail - queue.head;
    }

    public static void enqueue(ArrayQueueADT queue, Object item) {
        queue.array[queue.tail] = item;
        if (queue.tail + 1 == queue.maxSize) {
            queue.maxSize *= 2;
            queue.array = Arrays.copyOf(queue.array, queue.maxSize);
        }
        queue.tail = (queue.tail + 1) % queue.maxSize;
    }

    public static void push(ArrayQueueADT queue, Object item) {
        if (size(queue) == queue.maxSize) {
            queue.maxSize *= 2;
            queue.array = Arrays.copyOf(queue.array, queue.maxSize);
        }
        if (queue.head > 0) {
            queue.head = (queue.head - 1) % queue.maxSize;
        } else {
            queue.head = queue.maxSize - 1;
        }
        queue.array[queue.head] = item;
    }

    public static Object peek(ArrayQueueADT queue) {
        if (queue.tail > 0) {
            return queue.array[queue.tail - 1];
        }
        if (queue.tail == 0 && size(queue) > 0) {
            return queue.array[queue.maxSize - 1];
        }
        return null;
    }

    public static Object remove(ArrayQueueADT queue) {
        if (isEmpty(queue)) {
            return null;
        }
        if (queue.tail > 0) {
            queue.tail = (queue.tail - 1) % queue.maxSize;
        } else {
            queue.tail = queue.maxSize - 1;
        }
        return queue.array[queue.tail];
    }

    public static Object dequeue(ArrayQueueADT queue) {
        if (isEmpty(queue)) {
            return null;
        }
        Object result = queue.array[queue.head];
        queue.head = (queue.head + 1) % queue.maxSize;
        return result;
    }

    public static Object element(ArrayQueueADT queue) {
        return queue.array[queue.head];
    }

    public static void clear(ArrayQueueADT queue) {
        queue.head = 0;
        queue.tail = 0;
    }

    public ArrayQueueADT() {
        this(DEFAULT_SIZE);
    }

    public ArrayQueueADT(int size) {
        maxSize = size;
        array = new Object[maxSize];
    }
}
