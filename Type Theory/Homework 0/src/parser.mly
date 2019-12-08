%{
  open Expression;;
%}
%token <string> VAR
%token LAMBDA DOT
%token OPEN CLOSE
%token EOF
%start main
%type <Expression.expression> main
%%
main:
        expression EOF                                    { $1 }
expression:
        application LAMBDA VAR DOT expression             { Application ($1, (Abstraction ($3, $5))) }
        | LAMBDA VAR DOT expression                       { Abstraction ($2, $4) }
        | application                                     { $1 }
application:
        application term                                  { Application ($1, $2) }
        | term                                            { $1 }
term:
        OPEN expression CLOSE                             { $2 }
        | VAR                                             { Variable $1 }

