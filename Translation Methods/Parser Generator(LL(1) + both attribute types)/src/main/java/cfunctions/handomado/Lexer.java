package cfunctions.handomado;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private final String input;
    private final int length;
    private Token curToken;
    private StringBuilder word;
    private int index;

    public static class Token {
        private final Rule rule;
        private final String text;

        public Token(final Rule rule, final String text) {
            this.rule = rule;
            this.text = text;
        }

        public Rule getRule() {
            return rule;
        }

        public String getText() {
            return text;
        }
    }

    private static class TokenRule {
        private final Rule rule;
        private final Pattern pattern;

        private TokenRule(Rule rule, Pattern pattern) {
            this.rule = rule;
            this.pattern = pattern;
        }
    }

    public static class LexicalException extends RuntimeException {
        public LexicalException(String message) {
            super(message);
        }
    }

    private final Pattern oneToRuleThemAll = Pattern.compile("(_*[a-zA-Z][a-zA-Z0-9_]*)|(const)|(,)|(\\()|(\\))|(;)|(\\*)|([ \\t\\r\\n])");

    private final List<TokenRule> tokenRules = Arrays.asList(
            new TokenRule(Rule.IDENTIFIER, Pattern.compile("_*[a-zA-Z][a-zA-Z0-9_]*")),
            new TokenRule(Rule.CONST, Pattern.compile("const")),
            new TokenRule(Rule.COMMA, Pattern.compile(",")),
            new TokenRule(Rule.OPEN, Pattern.compile("\\(")),
            new TokenRule(Rule.CLOSE, Pattern.compile("\\)")),
            new TokenRule(Rule.SEMICOLON, Pattern.compile(";")),
            new TokenRule(Rule.ASTERISK, Pattern.compile("\\*")),
            new TokenRule(Rule.WHITESPACE, Pattern.compile("[ \\t\\r\\n]"))
    );

    private Set<Rule> ignore = Set.of(
            Rule.WHITESPACE
    );

    private Rule find(final String s) {
        Rule result = Rule.DUMMY;
        for (final TokenRule r : tokenRules) {
            if (r.pattern.matcher(s).matches()) {
                result = r.rule;
                break;
            }
        }
        return result;
    }

    public Lexer(final String input) {
        this.input = input;
        this.word = new StringBuilder();
        this.curToken = new Token(Rule.DUMMY, "");
        this.index = 0;
        this.length = input.length();
    }

    public Token getCurToken() {
        return curToken;
    }

    public void nextToken() {
        word = new StringBuilder();
        if (index == length) {
            curToken = new Token(Rule.EOF, "<EOF>");
            return;
        }
        char cur = input.charAt(index);
        index++;
        word.append(cur);
        String s = word.toString();
        Rule t = find(s);
        Matcher m = oneToRuleThemAll.matcher(s);
        if (m.matches()) {
            while (index < length) {
                cur = input.charAt(index);
                index++;
                word.append(cur);
                s = word.toString();
                t = find(s);
                m = oneToRuleThemAll.matcher(s);
                if (!m.matches()) {
                    index--;
                    s = s.substring(0, s.length() - 1);
                    t = find(s);
                    if (ignore.contains(t)) {
                        nextToken();
                        return;
                    }
                    break;
                }
            }
            curToken = new Token(t, s);
        } else {
            throw new LexicalException("Unexpected token " + word.toString());
        }
    }
}
