package queue;

import java.util.Arrays;

public class ArrayQueueModule {
    //let n = size, h = head, t = tail, a = queue, rv = return value;

    //INV: n >= 0; ∀i a[i] != null;

    private static final int DEFAULT_SIZE = 5;
    private static int maxSize;
    private static Object[] queue;
    private static int head = 0;
    private static int tail = 0;

    private static void grow () {
        maxSize *= 2;
        queue = Arrays.copyOf(queue, maxSize);
    }

    public static Object[] toArray() {
        Object[] result = head < tail ? Arrays.asList(queue).subList(head, tail).toArray() : head == tail ? new Object[0] : Arrays.asList(Arrays.asList(queue).subList(tail, maxSize), Arrays.asList(queue).subList(0, head)).toArray();
        return result;
    }
    //POST: a' = a;

    public static boolean isEmpty() {
        return head == tail;
    }
    //POST: rv = n > 0; n = n'; n >= 0; a' = a;

    public static int size() {
        if (head > tail) {
            return maxSize - head + tail;
        }
        return tail - head;
    }
    //POST: rv = n; n = n'; n >= 0; a' = a;

    //PRE: item != null;
    public static void enqueue(Object item) {
        queue[tail] = item;
        if (tail + 1 == maxSize) {
            grow();
        }
        tail = (tail + 1) % maxSize;
    }
    //POST: n' = n + 1; item = a[t - 1] || a[n - 1], a' w\out item = a;

    //PRE: item != null;
    public static void push(Object item) {
        if (size() == maxSize) {
            grow();
        }
        if (head > 0) {
            head = (head - 1) % maxSize;
        } else {
            head = maxSize - 1;
        }
        queue[head] = item;
    }
    //POST: n' = n + 1; item = a[h - 1] || a[n - 1], a' w\out item = a;

    //PRE: n > 0;
    public static Object peek() {
        Object result = null;
        int pos = -1;
        if (tail > 0) {
            pos = tail - 1;
        }
        if (tail == 0 && size() > 0) {
            pos = maxSize - 1;
        }
        try {
            result = queue[pos];
        } catch (NullPointerException ex) {
            System.err.println("Queue is empty");
        }
        return result;
    }
    //POST: rv = a[t - 1] || a[n - 1]; a' = a;

    //PRE: n > 0;
    public static Object remove() {
        Object result = null;
        if (tail > 0) {
            tail = (tail - 1) % maxSize;
        } else {
            tail = maxSize - 1;
        }
        try {
            result = queue[tail];
        } catch (NullPointerException ex) {
            System.err.println("Queue is empty");
        }
        return result;
    }
    //POST: rv = a[t - 1] || a[n - 1]; a' = a w\out item; n' = n - 1;

    //PRE: n > 0;
    public static Object dequeue() {
        Object result = null;
        try {
            result = queue[head];
        } catch (NullPointerException ex) {
            System.err.println("Queue is empty");
        }
        head = (head + 1) % maxSize;
        return result;
    }
    //POST: rv = a[h]; a' = a w\out item; n' = n - 1;

    //PRE: n > 0;
    public static Object element() {
        Object result = null;
        try {
            result = queue[head];
        } catch (NullPointerException ex) {
            System.err.println("Queue is empty");
        }
        return result;
    }
    //POST: rv = a[h]; a' = a;

    //PRE: n > 0;
    public static void clear() {
        head = 0;
        tail = 0;
    }
    //POST: n = 0;

    public ArrayQueueModule() {
        this(DEFAULT_SIZE);
    }

    public ArrayQueueModule(int size) {
        maxSize = size;
        queue = new Object[maxSize];
    }
}
