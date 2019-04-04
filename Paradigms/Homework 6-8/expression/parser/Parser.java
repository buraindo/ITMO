package expression.parser;

import exceptionsLib.*;
import expression.TripleExpression;

public interface Parser<T> {
    TripleExpression<T> parse(String expression) throws UnknownIdentifierException, OddClosingParenthesisException, OddOpeningParenthesisException, UnknownSymbolException, IncorrectConstException, MissingOperandException, MissingOperationException, IllegalConstantException;
}
