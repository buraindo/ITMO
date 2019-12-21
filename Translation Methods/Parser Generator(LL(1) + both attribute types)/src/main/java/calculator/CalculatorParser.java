package calculator;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class CalculatorParser {

    private final CalculatorLexer lexer;

    public CalculatorParser(final CalculatorLexer lexer) {
        this.lexer = lexer;
        lexer.nextToken();
    }

    public static class Node {
        private final String text;
        private final Rule rule;
        public int val;
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
    
    public Node t() {
        Node res = new Node("t", Rule.t);
        Rule currentRule = lexer.getCurToken().getRule();
        switch (currentRule) {
            case NUMBER: {
                Node f = f();
                res.children.add(f);
                Node tPrime = tPrime(f.val);
                res.children.add(tPrime);
                res.val = tPrime.val;
                break;
            }
            case OPEN: {
                Node f = f();
                res.children.add(f);
                Node tPrime = tPrime(f.val);
                res.children.add(tPrime);
                res.val = tPrime.val;
                break;
            }
            
            default:
                throw new ParseException("Illegal token " + currentRule.name());
        }
        return res;
    }

    public Node e() {
        Node res = new Node("e", Rule.e);
        Rule currentRule = lexer.getCurToken().getRule();
        switch (currentRule) {
            case NUMBER: {
                Node t = t();
                res.children.add(t);
                Node ePrime = ePrime(t.val);
                res.children.add(ePrime);
                res.val = ePrime.val;
                break;
            }
            case OPEN: {
                Node t = t();
                res.children.add(t);
                Node ePrime = ePrime(t.val);
                res.children.add(ePrime);
                res.val = ePrime.val;
                break;
            }
            
            default:
                throw new ParseException("Illegal token " + currentRule.name());
        }
        return res;
    }

    public Node tPrime(int acc) {
        Node res = new Node("tPrime", Rule.tPrime);
        Rule currentRule = lexer.getCurToken().getRule();
        switch (currentRule) {
            case MULTIPLY: {
                Node MULTIPLY = new Node(lexer.getCurToken().getText(), Rule.MULTIPLY);
                res.children.add(MULTIPLY);
                consume(Rule.MULTIPLY);
                Node t = t();
                res.children.add(t);
                res.val = acc * t.val;
                break;
            }
            case DIVIDE: {
                Node DIVIDE = new Node(lexer.getCurToken().getText(), Rule.DIVIDE);
                res.children.add(DIVIDE);
                consume(Rule.DIVIDE);
                Node t = t();
                res.children.add(t);
                res.val = acc / t.val;
                break;
            }
            case PLUS:
                res.val = acc;
                break;
            case MINUS:
                res.val = acc;
                break;
            case EOF:
                res.val = acc;
                break;
            case CLOSE:
                res.val = acc;
                break;
            default:
                throw new ParseException("Illegal token " + currentRule.name());
        }
        return res;
    }

    public Node f() {
        Node res = new Node("f", Rule.f);
        Rule currentRule = lexer.getCurToken().getRule();
        switch (currentRule) {
            case NUMBER: {
                Node NUMBER = new Node(lexer.getCurToken().getText(), Rule.NUMBER);
                res.children.add(NUMBER);
                consume(Rule.NUMBER);
                res.val = Integer.parseInt(NUMBER.text);
                break;
            }
            case OPEN: {
                Node OPEN = new Node(lexer.getCurToken().getText(), Rule.OPEN);
                res.children.add(OPEN);
                consume(Rule.OPEN);
                Node e = e();
                res.children.add(e);
                Node CLOSE = new Node(lexer.getCurToken().getText(), Rule.CLOSE);
                res.children.add(CLOSE);
                consume(Rule.CLOSE);
                res.val = e.val;
                break;
            }
            
            default:
                throw new ParseException("Illegal token " + currentRule.name());
        }
        return res;
    }

    public Node ePrime(int acc) {
        Node res = new Node("ePrime", Rule.ePrime);
        Rule currentRule = lexer.getCurToken().getRule();
        switch (currentRule) {
            case PLUS: {
                Node PLUS = new Node(lexer.getCurToken().getText(), Rule.PLUS);
                res.children.add(PLUS);
                consume(Rule.PLUS);
                Node e = e();
                res.children.add(e);
                res.val = acc + e.val;
                break;
            }
            case MINUS: {
                Node MINUS = new Node(lexer.getCurToken().getText(), Rule.MINUS);
                res.children.add(MINUS);
                consume(Rule.MINUS);
                Node e = e();
                res.children.add(e);
                res.val = acc - e.val;
                break;
            }
            case EOF:
                res.val = acc;
                break;
            case CLOSE:
                res.val = acc;
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