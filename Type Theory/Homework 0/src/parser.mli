type token =
  | VAR of (string)
  | LAMBDA
  | DOT
  | OPEN
  | CLOSE
  | EOF

val main :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> Expression.expression
