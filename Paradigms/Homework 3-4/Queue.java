package queue;

import java.util.function.Function;
import java.util.function.Predicate;

public interface Queue {
    //let n = size, h = head, t = tail, a = queue, rv = return value;

    //INV: n >= 0; ∀i a[i] != null;

    //PRE: item != null;
    void enqueue(Object item);
    //POST: n' = n + 1; item = a[t - 1] || a[n - 1], a' w\out item = a;

    //PRE: n > 0;
    Object dequeue();
    //POST: rv = a[h]; a' = a w\out item; n' = n - 1;

    Object[] toArray();
    //POST: a' = a;

    boolean isEmpty();
    //POST: rv = n > 0; n = n'; n >= 0; a' = a;

    int size();
    //POST: rv = n; n = n'; n >= 0; a' = a;

    //PRE: n > 0;
    void clear();
    //POST: n = 0;

    //PRE: n > 0;
    Object element();
    //POST: rv = a[h]; a' = a;

}
