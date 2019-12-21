grammar CFunction;

function  : type name OPEN arguments CLOSE SEMICOLON EOF ;
type      : IDENTIFIER ;
name      : IDENTIFIER ;
arguments : | args ;
arg       : type variable ;
variable  : ASTERISK variable | name ;
args      : arg multiarg ;
multiarg  : | COMMA args ;

IDENTIFIER : '_'*[a-zA-Z][a-zA-Z0-9_]* ;
CONST      : 'const' ;
COMMA      : ',' ;
OPEN       : '(' ;
CLOSE      : ')' ;
SEMICOLON  : ';' ;
ASTERISK   : '*' ;

WHITESPACE : [ \t\r\n] -> skip ;