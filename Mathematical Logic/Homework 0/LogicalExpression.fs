module LogicalExpression

type op = And | Or | To

type expression =
    | BinaryOperator of op * expression * expression
    | Not of expression
    | Variable of string

