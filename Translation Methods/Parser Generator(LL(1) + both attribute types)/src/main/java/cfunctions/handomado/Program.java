package cfunctions.handomado;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Program {
    private static String toString(final Parser.Node node) {
        if (node.getRule().isTerminal()) return node.getText();
        switch (node.getRule()) {
            case function:
                List<Parser.Node> children = node.getChildren();
                return String.format("%s %s(%s);", toString(children.get(0)), toString(children.get(1)), toString(children.get(3)));
            case type:
            case name:
                return toString(node.getChildren().get(0));
            case arguments:
                children = node.getChildren();
                if (children.isEmpty()) return "";
                return toString(children.get(0));
            case arg:
                children = node.getChildren();
                return String.format("%s %s", toString(children.get(0)), toString(children.get(1)));
            case variable:
                children = node.getChildren();
                if (children.size() == 1) return toString(children.get(0));
                return String.format("*%s", toString(children.get(1)));
            case multiarg:
                children = node.getChildren();
                if (children.isEmpty()) return "";
                return String.format(", %s", toString(children.get(1)));
            case args:
                children = node.getChildren();
                return String.format("%s%s", toString(children.get(0)), toString(children.get(1)));
        }
        return "";
    }

    public static void main(String[] args) throws IOException {
        final Lexer lexer = new Lexer(Files.readString(Paths.get("src/main/resources/tests/whitespaces1.txt")));
        final Parser parser = new Parser(lexer);
        Parser.Node function = parser.function();
        System.out.println(toString(function));
    }
}
