package queue;

public abstract class AbstractQueue implements Queue {

    protected int size;

    public void clear() {
        assert size > 0;

        clearQueue();
        size = 0;
    }

    public Object element() {
        assert size > 0;

        return Element();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Object dequeue() {
        assert size > 0;

        Object result = element();
        size--;
        remove();
        return result;
    }

    public void enqueue(Object obj) {
        assert obj != null;

        Enqueue(obj);
        size++;
    }

    public Object[] toArray() {
        assert size > 0;

        int s = size();
        Object[] result = new Object[s];
        for (int i = 0; i != s; i++) {
            Object element = dequeue();
            result[i] = element;
            enqueue(element);
        }
        return result;
    }

    public abstract Object Element();

    public abstract void clearQueue();

    public abstract void Enqueue(Object obj);

    public abstract void remove();

}
