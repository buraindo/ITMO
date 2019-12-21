grammar Rules;

metaGrammar                   : grammarName header rules ;
grammarName                   : GRAMMAR IDENTIFIER ;
header                        : HEADER code ;
rules                         : grammarRule+ ;
grammarRule                   : tokenRule | syntaxRule ;
tokenRule                     : TOKEN_NAME attribute? COLON REGEX code? SKIP_RULE? SEMICOLON ;
attribute                     : RETURNS SQUARE_OPEN IDENTIFIER IDENTIFIER SQUARE_CLOSE ;
syntaxRule                    : IDENTIFIER argumentWithType? attribute? COLON names SEMICOLON ;
name                          : TOKEN_NAME | IDENTIFIER argument? ;
names                         : moreNames | names OR names ;
moreNames                     : name+ code? ;
code                          : CODE ;
argument                      : ARGUMENT ;
argumentWithType              : SQUARE_OPEN IDENTIFIER IDENTIFIER SQUARE_CLOSE ;

GRAMMAR                       : 'grammar' ;
IMPORT                        : 'import' ;
HEADER                        : 'header' ;
RETURNS                       : 'returns' ;
PACKAGE                       : 'package' ;
SKIP_RULE                     : '-> skip' ;

OR                            : '|' ;
COLON                         : ':' ;
SEMICOLON                     : ';' ;
CURLY_OPEN                    : '{' ;
CURLY_CLOSE                   : '}' ;
OPEN                          : '(' ;
CLOSE                         : ')' ;
DOT                           : '.' ;
ASTERISK                      : '*' ;
SQUARE_OPEN                   : '[' ;
SQUARE_CLOSE                  : ']' ;
EQUALS_SIGN                   : '=' ;

TOKEN_NAME                    : [A-Z_]+ ;
IDENTIFIER                    : [a-zA-Z]+ ;
CODE                          : CURLY_OPEN .+? CURLY_CLOSE ;
ARGUMENT                      : OPEN .+? CLOSE ;
REGEX                         : '"'.*?'"' ;
WHITESPACE                    : [ \t\r\n] -> skip ;