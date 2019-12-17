grammar Ego;

program                 : blocks EOF ;
blocks                  : block+ ;
block                   : function | line ;

line                    : assignment NEWLINE | flow | call NEWLINE | returnStatement NEWLINE | NEWLINE ;
assignment              : name ASSIGN rvalue (AS type)* ;
returnStatement         : RETURN rvalue (AS type)* ;
type                    : T_INT | T_LONG | T_CHAR | T_DOUBLE | T_STRING | T_NOTHING ;
name                    : IDENTIFIER ;
rvalue                  : (name | call | literal) (operation (name | call | literal))* ;

literal                 : numberLiteral | doubleLiteral | charLiteral | stringLiteral ;
numberLiteral           : NUMBER ( NUMBER)* ;
doubleLiteral           : DOUBLE (operation DOUBLE)* ;
charLiteral             : CHAR (operation CHAR)* ;
stringLiteral           : STRING (PLUS STRING)* ;
operation               : PLUS | MINUS | MULTIPLY | DIVIDE | MODULO | AND | XOR | OR ;

function                : FUNCTION functionName OPEN args CLOSE RETURNS type scope ;
call                    : functionName OPEN fargs CLOSE ;
fargs                   : | farg | multifargs ;
args                    : | arg | multiargs;
arg                     : name OF type ;
farg                    : name | rvalue ;
multiargs               : arg COMMA multiarg ;
multifargs              : farg COMMA multifarg ;
multiarg                : arg | arg COMMA multiarg ;
multifarg               : farg | farg COMMA multifarg ;
functionName            : IDENTIFIER | NEXT_INT | NEXT_CHAR | NEXT_LONG |
                          NEXT_DOUBLE | NEXT_LINE |
                          PRINT_INT | PRINT_CHAR | PRINT_LONG |
                          PRINT_DOUBLE | PRINT_STRING ;


flow                    : forLoop | whileLoop | ifStatement ;
ifStatement             : IF statement scope ;
whileLoop               : WHILE statement scope ;
forLoop                 : FOR name IN (name | NUMBER) DOT DOT (name | NUMBER) scope ;
scope                   : BLOCK_OPEN line* BLOCK_CLOSE ;
statement               : NOT OPEN statement CLOSE | name | call | predicate ;
predicate               : (name | call | literal) (LESS | MORE_ | EQUALS | NOT_EQUALS) (name | call | literal) ;

fragment COLON          : ':' ;
fragment EQUALS_SIGN    : '=' ;
fragment AMPERSAND      : '&' ;
fragment VERTICAL       : '|' ;

NUMBER                  : [0-9]+ ;
STRING                  : '"'.*?'"' ;
CHAR                    : '\''.'\'' ;
DOUBLE                  : NUMBER DOT NUMBER ;
COMMA                   : ',' ;
OPEN                    : '(' ;
CLOSE                   : ')' ;
BLOCK_OPEN              : '{' ;
BLOCK_CLOSE             : '}' ;
SEMICOLON               : ';' ;
DOT                     : '.' ;
NEWLINE                 : '\n' ;

FUNCTION                : 'function' ;
RETURNS                 : 'returns' ;
RETURN                  : 'return' ;
OF                      : 'of' ;

MULTIPLY                : '*' ;
PLUS                    : '+' ;
MINUS                   : '-' ;
DIVIDE                  : '/' ;
MODULO                  : '%' ;

AND                     : AMPERSAND AMPERSAND ;
OR                      : VERTICAL VERTICAL ;
XOR                     : '^' ;
NOT                     : '!' ;
LESS                    : '<' ;
MORE_                   : '>' ;
EQUALS                  : EQUALS_SIGN EQUALS_SIGN ;
NOT_EQUALS              : NOT EQUALS_SIGN ;

ASSIGN                  : EQUALS_SIGN ;
AS                      : 'as' ;
IF                      : 'if' ;
WHILE                   : 'while' ;
FOR                     : 'for' ;
IN                      : 'in' ;

T_INT                   : 'Int' ;
T_LONG                  : 'Long' ;
T_CHAR                  : 'Char' ;
T_STRING                : 'String' ;
T_DOUBLE                : 'Double' ;
T_NOTHING               : 'Nothing' ;

NEXT_INT                : 'nextInt' ;
NEXT_LONG               : 'nextLong' ;
NEXT_CHAR               : 'nextChar' ;
NEXT_DOUBLE             : 'nextDouble' ;
NEXT_LINE               : 'nextLine' ;

PRINT_INT               : 'printInt' ;
PRINT_LONG              : 'printLong' ;
PRINT_CHAR              : 'printChar' ;
PRINT_DOUBLE            : 'printDouble' ;
PRINT_STRING            : 'printString' ;

IDENTIFIER              : '_'*[a-zA-Z][a-zA-Z0-9_]* ;
WHITESPACE              : [ \t\r\n] -> skip;