grammar gpi;

options
{
    language=Java;
}

ID  :	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

INT :	'0'..'9'+
    ;

FLOAT
    :   ('0'..'9')+ '.' ('0'..'9')* EXPONENT?
    |   '.' ('0'..'9')+ EXPONENT?
    |   ('0'..'9')+ EXPONENT
    ;

COMMENT
    :   '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    |   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;}
    ;

STRING
    :  '\'' ( ESC_SEQ | ~('\\'|'\'') )* '\''
    ;
    
DATETIME
     :    '#' (~ '#' )* '#'
     ;

fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
ESC_SEQ
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UNICODE_ESC
    |   OCTAL_ESC
    ;

fragment
OCTAL_ESC
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UNICODE_ESC
    :   '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;
    
logicalExpression
    :    booleanAndExpression ( OR booleanAndExpression )*
    ;
    
    
    booleanAndExpression
    :    equalityExpression ( AND equalityExpression )*
    ;
    
    equalityExpression
    :    relationalExpression ( (EQUALS | NOTEQUALS) relationalExpression)*
    ;
    
    relationalExpression
    :    additiveExpression ( (LT | LTEQ | GT | GTEQ) additiveExpression)*
    ;

additiveExpression
    :    multiplicativeExpression ( (PLUS | MINUS) multiplicativeExpression )*
    ;

multiplicativeExpression
    :    unaryExpression (( MULT | DIV | MOD ) unaryExpression)*
    ;

unaryExpression
    :    NOT! primaryExpression
       ;

primaryExpression
    :    '(' logicalExpression ')'
    |    value
    ;
    
expression
    :     logicalExpression EOF
    ;
    
function
    :    IDENT '(' ( logicalExpression (',' logicalExpression)* )? ')'
    ;
    
IDENT
    :    ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*
    ;
    
parameter
    :    '[' (IDENT|INT) ']';


OR       :    '||' | 'or';
AND      :    '&&' | 'and';
EQUALS   :    '='  | '==';
NOTEQUALS:    '!=' | '<>';
LT       :    '<'  | 'lt';
LTEQ     :    '<=' | 'lte';
GT       :    '>'  | 'gt';
GTEQ     :    '>=' | 'gte';
PLUS     :    '+';
MINUS    :    '-';
MULT     :    '*';
DIV      :    '/';
MOD      :    '%';
NOT      :    '!'  | 'not';



value    :   
         INT
    |    FLOAT
    |    STRING
    |    DATETIME
    |    function
    |    parameter
    ;
