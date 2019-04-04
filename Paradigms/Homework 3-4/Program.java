package queue;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Program {
    public static void fill(LinkedQueue stack) {
        for (int i = 0; i < 10; i++) {
            stack.enqueue(Integer.toString(i));
        }
    }

    public static void dump(LinkedQueue stack) {
        while (!stack.isEmpty()) {
            System.out.println(stack.size() + " " +
                    stack.element() + " " +
                    stack.dequeue());
        }
    }

    public static void main(String[] args) {
        ArrayQueue stack = new ArrayQueue();
        var pattern = Pattern.compile("[01]*");
        /*stack.enqueue("hello");
        stack.enqueue("world");
        stack.enqueue(1);
        stack.enqueue(2);
        stack.enqueue(3);
        stack.enqueue(new Object());*/
        //fill(stack);
        //stack.clear();
        stack.enqueue(1);
        stack.dequeue();
        stack.enqueue(1);
        System.out.println(stack.element());
        //dump(stack);
    }
}