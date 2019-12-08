type expression = 
  | Variable of string
  | Abstraction of string * expression
  | Application of expression * expression;;