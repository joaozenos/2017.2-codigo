%%

/* Dont touch anything here */

%line
%column
%unicode
//%debug
%public
%standalone
%class Minijava
%eofclose


underline = [_]
whitespace	= [ \n\t\r\f]
letter	= [A-Za-z]
literal_integer	= 0|[1-9][0-9]*

alphanum = {letter}|{literal_integer}
identifier	= ({underline}|{letter})({alphanum}|{underline})*

line_comment = \/\/.*
block_comment = \/\*(.|\n\r)*?\*\/

%%

/* Lexical Rules */

/* Operators */

"&&"        { System.out.println("Token &&"); }
"||"        { System.out.println("Token ||"); }
">"         { System.out.println("Token >");  }
"<"         { System.out.println("Token <");  }
"=="        { System.out.println("Token =="); }
"!="        { System.out.println("Token !="); }
"+"         { System.out.println("Token +");  }
"-"         { System.out.println("Token -");  }
"*"         { System.out.println("Token *");  }
"!"         { System.out.println("Token !");  }

/* Pontuation and Delimiters */

";"         { System.out.println("Token ;"); }
"."         { System.out.println("Token ."); }
","         { System.out.println("Token ,"); }
"="         { System.out.println("Token ="); }
"("         { System.out.println("Token ("); }
")"         { System.out.println("Token )"); }
"{"         { System.out.println("Token {"); }
"}"         { System.out.println("Token }"); }
"["         { System.out.println("Token ["); }
"]"         { System.out.println("Token ]"); }


/* Key Words */

boolean                 { System.out.println("Token boolean"); }
class                   { System.out.println("Token class"); }
public                  { System.out.println("Token public"); }
extends                 { System.out.println("Token extends"); }
static                  { System.out.println("Token static"); }
void                    { System.out.println("Token void"); }
main                    { System.out.println("Token main"); }
String                  { System.out.println("Token String"); }
int                     { System.out.println("Token int"); }
while                   { System.out.println("Token while"); }
if                      { System.out.println("Token if"); }
else                    { System.out.println("Token else"); }
return                  { System.out.println("Token return"); }
length                  { System.out.println("Token length"); }
true                    { System.out.println("Token true"); }
false                   { System.out.println("Token false"); }
this                    { System.out.println("Token this"); }
new                     { System.out.println("Token new"); }
System.out.println      { System.out.println("Token System.out.println"); }

/* General */

{whitespace}		{ /* Do Nothing */ }
{line_comment}		{ System.out.println("Token LINE_COMMENT"); }
{block_comment}		{ System.out.println("Token BLOCK_COMMENT"); }
{identifier}		{ System.out.println("Token ID"); }
{literal_integer}	{ System.out.println("Token LITERAL_INTEGER");}
     
. { throw new RuntimeException("Caractere ilegal! '" + yytext() + "' na linha: " + yyline + ", coluna: " + yycolumn); }
