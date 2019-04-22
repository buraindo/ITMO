package parser;

import java.util.HashMap;
import java.util.Map;

public interface Expression {

    String toString();

    boolean evaluate();

    boolean state();

    void setState(boolean state);

    Class<?> getRealClass();

    Map<String, Integer> values = new HashMap<>();

    Map<Integer, String> indexToVariable = new HashMap<>();

    boolean equals(Object other);

    int hashCode();

}
