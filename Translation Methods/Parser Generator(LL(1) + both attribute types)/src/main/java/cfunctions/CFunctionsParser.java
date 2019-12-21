package cfunctions;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class CFunctionsParser {

    private final CFunctionsLexer lexer;

    public CFunctionsParser(final CFunctionsLexer lexer) {
        this.lexer = lexer;
        lexer.nextToken();
    }

    public static class Node {
        private final String text;
        private final Rule rule;
        public String val;
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
    
    public Node args() {
        Node res = new Node("args", Rule.args);
        Rule currentRule = lexer.getCurToken().getRule();
        switch (currentRule) {
            case IDENTIFIER: {
                Node arg = arg();
                res.children.add(arg);
                Node multiarg = multiarg();
                res.children.add(multiarg);
                res.val = arg.val + multiarg.val;
                break;
            }
            
            default:
                throw new ParseException("Illegal token " + currentRule.name());
        }
        return res;
    }

    public Node multiarg() {
        Node res = new Node("multiarg", Rule.multiarg);
        Rule currentRule = lexer.getCurToken().getRule();
        switch (currentRule) {
            case COMMA: {
                Node COMMA = new Node(lexer.getCurToken().getText(), Rule.COMMA);
                res.children.add(COMMA);
                consume(Rule.COMMA);
                Node args = args();
                res.children.add(args);
                res.val = COMMA.text + " " + args.val;
                break;
            }
            case CLOSE:
                res.val = "";
                break;
            default:
                throw new ParseException("Illegal token " + currentRule.name());
        }
        return res;
    }

    public Node function() {
        Node res = new Node("function", Rule.function);
        Rule currentRule = lexer.getCurToken().getRule();
        switch (currentRule) {
            case IDENTIFIER: {
                Node type = type();
                res.children.add(type);
                Node name = name();
                res.children.add(name);
                Node OPEN = new Node(lexer.getCurToken().getText(), Rule.OPEN);
                res.children.add(OPEN);
                consume(Rule.OPEN);
                Node arguments = arguments();
                res.children.add(arguments);
                Node CLOSE = new Node(lexer.getCurToken().getText(), Rule.CLOSE);
                res.children.add(CLOSE);
                consume(Rule.CLOSE);
                Node SEMICOLON = new Node(lexer.getCurToken().getText(), Rule.SEMICOLON);
                res.children.add(SEMICOLON);
                consume(Rule.SEMICOLON);
                res.val = type.val + " " + name.val + "(" + arguments.val + ");";
                break;
            }
            
            default:
                throw new ParseException("Illegal token " + currentRule.name());
        }
        return res;
    }

    public Node arg() {
        Node res = new Node("arg", Rule.arg);
        Rule currentRule = lexer.getCurToken().getRule();
        switch (currentRule) {
            case IDENTIFIER: {
                Node type = type();
                res.children.add(type);
                Node variable = variable();
                res.children.add(variable);
                res.val = type.val + " " + variable.val;
                break;
            }
            
            default:
                throw new ParseException("Illegal token " + currentRule.name());
        }
        return res;
    }

    public Node name() {
        Node res = new Node("name", Rule.name);
        Rule currentRule = lexer.getCurToken().getRule();
        switch (currentRule) {
            case IDENTIFIER: {
                Node IDENTIFIER = new Node(lexer.getCurToken().getText(), Rule.IDENTIFIER);
                res.children.add(IDENTIFIER);
                consume(Rule.IDENTIFIER);
                res.val = IDENTIFIER.text;
                break;
            }
            
            default:
                throw new ParseException("Illegal token " + currentRule.name());
        }
        return res;
    }

    public Node variable() {
        Node res = new Node("variable", Rule.variable);
        Rule currentRule = lexer.getCurToken().getRule();
        switch (currentRule) {
            case ASTERISK: {
                Node ASTERISK = new Node(lexer.getCurToken().getText(), Rule.ASTERISK);
                res.children.add(ASTERISK);
                consume(Rule.ASTERISK);
                Node variable = variable();
                res.children.add(variable);
                res.val = ASTERISK.text + variable.val;
                break;
            }
            case IDENTIFIER: {
                Node name = name();
                res.children.add(name);
                res.val = name.val;
                break;
            }
            
            default:
                throw new ParseException("Illegal token " + currentRule.name());
        }
        return res;
    }

    public Node arguments() {
        Node res = new Node("arguments", Rule.arguments);
        Rule currentRule = lexer.getCurToken().getRule();
        switch (currentRule) {
            case IDENTIFIER: {
                Node args = args();
                res.children.add(args);
                res.val = args.val;
                break;
            }
            case CLOSE:
                res.val = "";
                break;
            default:
                throw new ParseException("Illegal token " + currentRule.name());
        }
        return res;
    }

    public Node type() {
        Node res = new Node("type", Rule.type);
        Rule currentRule = lexer.getCurToken().getRule();
        switch (currentRule) {
            case IDENTIFIER: {
                Node IDENTIFIER = new Node(lexer.getCurToken().getText(), Rule.IDENTIFIER);
                res.children.add(IDENTIFIER);
                consume(Rule.IDENTIFIER);
                res.val = IDENTIFIER.text;
                break;
            }
            
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