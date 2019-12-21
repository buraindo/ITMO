package cfunctions.handomado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@SuppressWarnings("all")
public class Parser {

    private final Lexer lexer;

    public Parser(final Lexer lexer) {
        this.lexer = lexer;
        lexer.nextToken();
    }

    public static class Node {
        private final String text;
        private final Rule rule;
        private List<Node> children = new ArrayList<>();

        public Node(final String text, final Rule rule) {
            this.text = text;
            this.rule = rule;
        }

        public String getText() {
            return text;
        }

        public Rule getRule() {
            return rule;
        }

        public List<Node> getChildren() {
            return children;
        }
    }

    public static class ParseException extends RuntimeException {
        public ParseException(String message) {
            super(message);
        }
    }

    private Map<String, List<List<Rule>>> rules = new HashMap<>() {{
        put("function", List.of(List.of(Rule.type, Rule.name, Rule.OPEN, Rule.arguments, Rule.CLOSE, Rule.SEMICOLON, Rule.EOF)));
        put("type", List.of(List.of(Rule.IDENTIFIER)));
        put("name", List.of(List.of(Rule.IDENTIFIER)));
        put("arguments", List.of(List.of(Rule.EPSILON), List.of(Rule.args)));
        put("arg", List.of(List.of(Rule.type, Rule.variable)));
        put("variable", List.of(List.of(Rule.ASTERISK, Rule.variable), List.of(Rule.name)));
        put("args", List.of(List.of(Rule.arg, Rule.multiarg)));
        put("multiarg", List.of(List.of(Rule.EPSILON), List.of(Rule.COMMA, Rule.args)));
    }};

    private Map<Rule, Supplier<Node>> functions = new HashMap<>() {{
        put(Rule.function, Parser.this::function);
        put(Rule.type, Parser.this::type);
        put(Rule.name, Parser.this::name);
        put(Rule.arguments, Parser.this::arguments);
        put(Rule.arg, Parser.this::arg);
        put(Rule.variable, Parser.this::variable);
        put(Rule.args, Parser.this::args);
        put(Rule.multiarg, Parser.this::multiarg);
    }};

    public Node function() {
        Node res = new Node("function", Rule.function);
        Lexer.Token curToken = lexer.getCurToken();
        List<List<Rule>> alphas = rules.get("function");
        Rule currentRule = curToken.getRule();
        switch (currentRule) {
            case IDENTIFIER:
                for (final Rule r : alphas.get(0)) {
                    if (r.isTerminal()) {
                        res.children.add(new Node(lexer.getCurToken().getText(), r));
                        consume(r);
                    } else {
                        Node t = functions.get(r).get();
                        res.children.add(t);
                    }
                }
                break;
            default:
                throw new ParseException("Illegal token " + currentRule.name());
        }
        return res;
    }

    public Node type() {
        Node res = new Node("type", Rule.type);
        Lexer.Token curToken = lexer.getCurToken();
        List<List<Rule>> alphas = rules.get("type");
        Rule currentRule = curToken.getRule();
        switch (currentRule) {
            case IDENTIFIER:
                for (final Rule r : alphas.get(0)) {
                    if (r.isTerminal()) {
                        res.children.add(new Node(lexer.getCurToken().getText(), r));
                        consume(r);
                    } else {
                        Node t = functions.get(r).get();
                        res.children.add(t);
                    }
                }
                break;
            default:
                throw new ParseException("Illegal token " + currentRule.name());
        }
        return res;
    }

    public Node name() {
        Node res = new Node("name", Rule.name);
        Lexer.Token curToken = lexer.getCurToken();
        List<List<Rule>> alphas = rules.get("name");
        Rule currentRule = curToken.getRule();
        switch (currentRule) {
            case IDENTIFIER:
                for (final Rule r : alphas.get(0)) {
                    if (r.isTerminal()) {
                        res.children.add(new Node(lexer.getCurToken().getText(), r));
                        consume(r);
                    } else {
                        Node t = functions.get(r).get();
                        res.children.add(t);
                    }
                }
                break;
            default:
                throw new ParseException("Illegal token " + currentRule.name());
        }
        return res;
    }

    public Node arguments() {
        Node res = new Node("arguments", Rule.arguments);
        Lexer.Token curToken = lexer.getCurToken();
        List<List<Rule>> alphas = rules.get("arguments");
        Rule currentRule = curToken.getRule();
        switch (currentRule) {
            case IDENTIFIER:
                for (final Rule r : alphas.get(1)) {
                    if (r.isTerminal()) {
                        res.children.add(new Node(lexer.getCurToken().getText(), r));
                        consume(r);
                    } else {
                        Node t = functions.get(r).get();
                        res.children.add(t);
                    }
                }
                break;
            case CLOSE:
                break;
            default:
                throw new ParseException("Illegal token " + currentRule.name());
        }
        return res;
    }

    public Node arg() {
        Node res = new Node("arg", Rule.arg);
        Lexer.Token curToken = lexer.getCurToken();
        List<List<Rule>> alphas = rules.get("arg");
        Rule currentRule = curToken.getRule();
        switch (currentRule) {
            case IDENTIFIER:
                for (final Rule r : alphas.get(0)) {
                    if (r.isTerminal()) {
                        res.children.add(new Node(lexer.getCurToken().getText(), r));
                        consume(r);
                    } else {
                        Node t = functions.get(r).get();
                        res.children.add(t);
                    }
                }
                break;
            default:
                throw new ParseException("Illegal token " + currentRule.name());
        }
        return res;
    }

    public Node variable() {
        Node res = new Node("variable", Rule.variable);
        Lexer.Token curToken = lexer.getCurToken();
        List<List<Rule>> alphas = rules.get("variable");
        Rule currentRule = curToken.getRule();
        switch (currentRule) {
            case ASTERISK:
                for (final Rule r : alphas.get(0)) {
                    if (r.isTerminal()) {
                        res.children.add(new Node(lexer.getCurToken().getText(), r));
                        consume(r);
                    } else {
                        Node t = functions.get(r).get();
                        res.children.add(t);
                    }
                }
                break;
            case IDENTIFIER:
                for (final Rule r : alphas.get(1)) {
                    if (r.isTerminal()) {
                        res.children.add(new Node(lexer.getCurToken().getText(), r));
                        consume(r);
                    } else {
                        Node t = functions.get(r).get();
                        res.children.add(t);
                    }
                }
                break;
            default:
                throw new ParseException("Illegal token " + currentRule.name());
        }
        return res;
    }

    public Node args() {
        Node res = new Node("args", Rule.args);
        Lexer.Token curToken = lexer.getCurToken();
        List<List<Rule>> alphas = rules.get("args");
        Rule currentRule = curToken.getRule();
        switch (currentRule) {
            case IDENTIFIER:
                for (final Rule r : alphas.get(0)) {
                    if (r.isTerminal()) {
                        res.children.add(new Node(lexer.getCurToken().getText(), r));
                        consume(r);
                    } else {
                        Node t = functions.get(r).get();
                        res.children.add(t);
                    }
                }
                break;
            default:
                throw new ParseException("Illegal token " + currentRule.name());
        }
        return res;
    }

    public Node multiarg() {
        Node res = new Node("multiarg", Rule.multiarg);
        Lexer.Token curToken = lexer.getCurToken();
        List<List<Rule>> alphas = rules.get("multiarg");
        Rule currentRule = curToken.getRule();
        switch (currentRule) {
            case COMMA:
                for (final Rule r : alphas.get(1)) {
                    if (r.isTerminal()) {
                        res.children.add(new Node(lexer.getCurToken().getText(), r));
                        consume(r);
                    } else {
                        Node t = functions.get(r).get();
                        res.children.add(t);
                    }
                }
                break;
            case CLOSE:
                break;
            default:
                throw new ParseException("Illegal token " + currentRule.name());
        }
        return res;
    }

    private void consume(final Rule expected) {
        Rule actual = lexer.getCurToken().getRule();
        if (expected != actual) {
            throw new ParseException("Illegal token " + actual.name() + ", expected " + expected.name());
        }
        lexer.nextToken();
    }

}
