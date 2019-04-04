package parser;

public class ExpressionParser {

    private class Result {

        private Expression acc;
        private int rest;

        Expression Acc() {
            return acc;
        }

        int Rest() {
            return rest;
        }

        void SetRest(int rest) {
            this.rest = rest;
        }

        Result(Expression v, int r) {
            this.acc = v;
            this.rest = r;
        }
    }

    public Expression parse(String s) {
        s = s.replaceAll("\\s", "").replaceAll("\n", "");
        Result result = Impl(s, 0);
        return result.Acc();
    }

    private Result Impl(String s, int i) {
        Result current = Disj(s, i);
        Expression acc = current.Acc();
        while (current.Rest() < s.length()) {
            if (s.charAt(current.Rest()) != '-') {
                break;
            }
            current = Impl(s, current.Rest() + 2);
            acc = new Impl(acc, current.Acc());
        }
        return new Result(acc, current.Rest());
    }

    private Result Disj(String s, int i) {
        Result current = Conj(s, i);
        Expression acc = current.Acc();
        while (current.Rest() < s.length()) {
            if (!(s.charAt(current.Rest()) == '|')) break;
            current = Conj(s, current.Rest() + 1);
            acc = new Disj(acc, current.Acc());
        }
        return new Result(acc, current.Rest());
    }

    private Result Conj(String s, int i) {
        Result current = Bracket(s, i);
        Expression acc = current.Acc();
        while (true) {
            if (current.Rest() == s.length()) return current;
            char sign = s.charAt(current.Rest());
            if (sign != '&') return current;
            Result right = Bracket(s, current.Rest() + 1);
            acc = new Conj(acc, right.Acc());
            current = new Result(acc, right.Rest());
        }
    }

    private Result Bracket(String s, int i) {
        char zeroChar = s.charAt(i);
        if (zeroChar == '!') {
            Result n = Bracket(s, i + 1);
            Expression res = new Neg(n.Acc());
            return new Result(res, n.Rest());
        } else {
            if (zeroChar == '(') {
                Result r = Impl(s, i + 1);
                if (s.charAt(r.Rest()) == ')') {
                    r.SetRest(r.Rest() + 1);
                }
                return r;
            }
        }
        return Variable(s, i);
    }

    private Result Variable(String s, int i) {
        StringBuilder f = new StringBuilder();
        int j = 0;
        Expression res;
        while (j + i < s.length() && (Character.isLetter(s.charAt(j + i)) || (Character.isDigit(s.charAt(j + i))) || s.charAt(j + i) == '\'')) {
            char ch = s.charAt(j + i);
            f.append(ch);
            j++;
        }
        res = new Variable(f.toString());
        return new Result(res, i + j);
    }

}
