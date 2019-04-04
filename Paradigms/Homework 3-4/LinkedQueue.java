package queue;

import java.lang.*;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LinkedQueue extends AbstractQueue {
    private Node head;
    private Node tail;

    public void Enqueue(Object element) {
        tail = new Node(element, tail);
        if (head == null) head = tail;
    }

    public void clearQueue() {
        head = null;
        tail = null;
    }

    public void remove() {
        head = head.next;
    }

    public Object Element() {
        assert size > 0;

        return tail.value;
    }

    private class Node {
        private Object value;
        private Node next;

        public Node(Object value, Node next) {
            assert value != null;

            this.value = value;
            this.next = next;
        }
    }
}