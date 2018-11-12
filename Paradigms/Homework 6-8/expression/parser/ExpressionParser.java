package expression.parser;

import exceptionsLib.*;
import expression.*;

class Result<T> {

    private TripleExpression<T> acc;
    private int rest;

    TripleExpression<T> Acc() {
        return acc;
    }

    int Rest() {
        return rest;
    }

    void SetRest(int rest) {
        this.rest = rest;
    }

    Result(TripleExpression<T> v, int r) {
        this.acc = v;
        this.rest = r;
    }
}

public class ExpressionParser<T> implements Parser<T> {

    private int balance;
    private boolean flag = false;
    private boolean negFlag = false;
    private Operation<T> operation;

    public ExpressionParser (Operation<T> operation) {
        this.operation = operation;
    }

    public TripleExpression<T> parse(String s) throws UnknownIdentifierException, OddClosingParenthesisException, OddOpeningParenthesisException, UnknownSymbolException, IncorrectConstException, MissingOperandException, MissingOperationException, IllegalConstantException {
        if (!flag) {
            Util.initialize();
            flag = true;
        }
        balance = 0;
        s = s.replaceAll("\\s", "");
        Result<T> result = AddSub(s, 0);
        if (balance > 0) {
            throw new OddOpeningParenthesisException(s, result.Rest());
        }
        return result.Acc();
    }

    private Result<T> AddSub(String s, int i) throws UnknownIdentifierException, OddClosingParenthesisException, OddOpeningParenthesisException, UnknownSymbolException, IncorrectConstException, MissingOperandException, MissingOperationException, IllegalConstantException {
        var current = MulDiv(s, i);
        var acc = current.Acc();
        while (current.Rest() < s.length()) {
            if (!(s.charAt(current.Rest()) == '+' || s.charAt(current.Rest()) == '-')) {
                if (s.charAt(current.Rest()) == '(' || s.charAt(current.Rest()) == ')' && balance == 0) {
                    throw new MissingOperationException(s, current.Rest());
                }
                break;
            }
            char sign = s.charAt(current.Rest());

            current = MulDiv(s, current.Rest() + 1);
            if (sign == '+') {
                acc = new CheckedAdd<>(acc, current.Acc(), operation);
            } else {
                acc = new CheckedSubtract<>(acc, current.Acc(), operation);
            }
        }
        return new Result<>(acc, current.Rest());
    }

    private Result<T> MulDiv(String s, int i) throws UnknownIdentifierException, UnknownSymbolException, OddOpeningParenthesisException, OddClosingParenthesisException, IncorrectConstException, MissingOperandException, MissingOperationException, IllegalConstantException {
        var current = Bracket(s, i);

        var acc = current.Acc();
        while (true) {
            if (current.Rest() == s.length()) {
                return current;
            }
            char sign = s.charAt(current.Rest());
            if ((sign != '*' && sign != '/')) {
                if (sign == '(' || sign == ')' && balance == 0) {
                    throw new OddClosingParenthesisException(s, current.Rest());
                }
                if (!Util.appropriate.contains(s.charAt(current.Rest()))) {
                    throw new UnknownSymbolException(s, current.Rest());
                }
                return current;
            }

            Result<T> right = Bracket(s, current.Rest() + 1);

            if (sign == '*') {
                acc = new CheckedMultiply<>(acc, right.Acc(), operation);
            } else {
                acc = new CheckedDivide<>(acc, right.Acc(), operation);
            }

            current = new Result<>(acc, right.Rest());
        }
    }

    private Result<T> Bracket(String s, int i) throws UnknownIdentifierException, OddClosingParenthesisException, UnknownSymbolException, OddOpeningParenthesisException, IncorrectConstException, MissingOperandException, MissingOperationException, IllegalConstantException {
        if (i == s.length()) {
            throw new MissingOperandException(s, i);
        }
        char zeroChar = s.charAt(i);
        if (zeroChar == '-') {
            negFlag = true;
            var n = Bracket(s, i + 1);
            var res = n.Acc();
            if (negFlag)
                res = new CheckedNegate<>(n.Acc(), operation);
            return new Result<>(res, n.Rest());
        } else {
            if (zeroChar == '(') {
                balance++;
                Result<T> r = AddSub(s, i + 1);
                if (r.Rest() == s.length()) {
                    throw new OddOpeningParenthesisException(s, r.Rest());
                }
                if (s.charAt(r.Rest()) == ')') {
                    balance--;
                    if (balance < 0) {
                        throw new OddClosingParenthesisException(s, r.Rest());
                    }
                    r.SetRest(r.Rest() + 1);
                }
                return r;
            }
        }
        return Variable(s, i);
    }

    private Result<T> Variable(String s, int i) throws UnknownSymbolException, MissingOperandException, IllegalConstantException {
        StringBuilder f = new StringBuilder();
        int j = 0;
        TripleExpression<T> res;
        int minusCount = 0;
        while (s.charAt(i + j) == '-') {
            minusCount++;
            j++;
        }
        while (j + i < s.length() && (Character.isLetter(s.charAt(j + i)) || (Character.isDigit(s.charAt(j + i)) && j > 0))) {
            var ch = s.charAt(j + i);
            if (!Character.isDigit(ch) && ch != 'x' && ch != 'y' && ch != 'z') {
                throw new UnknownSymbolException(Character.toString(ch), j + i);
            }
            f.append(ch);
            j++;
        }
        if (!f.toString().isEmpty()) {
            res = new Variable<>(f.toString());
            if (minusCount % 2 == 1) res = new CheckedNegate<>(res, operation);
            return new Result<>(res, i + 1);
        }
        return Num(s, i);
    }

    private Result<T> Num(String s, int i) throws MissingOperandException, IllegalConstantException {
        int j = 0;
        TripleExpression<T> res;
        int minusCount = 0;
        while (s.charAt(i + j) == '-') {
            minusCount++;
            j++;
        }
        while (i + j < s.length() && (Character.isDigit(s.charAt(i + j)) || s.charAt(i + j) == '.')) {
            j++;
        }
        if (j == 0) {
            throw new MissingOperandException(s, i + j);
        }
        var substring = s.substring(i, i + j);
        try {
            res = new Const<>(operation.parse(substring));
        } catch (NumberFormatException ex) {
            if (i == 0 || !(s.charAt(i - 1) == '-' && Util.tryParse("-" + substring)))
                throw new IllegalConstantException(substring, s, i);
            res = new Const<>(operation.parse("-" + substring));
            negFlag = false;
        }
        if (minusCount % 2 == 1) res = new CheckedNegate<>(res, operation);
        return new Result<>(res, i + j);
    }

}
