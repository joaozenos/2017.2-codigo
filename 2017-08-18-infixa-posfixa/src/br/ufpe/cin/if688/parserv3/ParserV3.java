package br.ufpe.cin.if688.parserv3;

import java.io.IOException;

import br.ufpe.cin.if688.lexer.ILexer;
import br.ufpe.cin.if688.lexer.tokens.Num;
import br.ufpe.cin.if688.lexer.tokens.Tag;
import br.ufpe.cin.if688.lexer.tokens.Token;
import br.ufpe.cin.if688.lexer.tokens.Word;

public class ParserV3 {
   private ILexer<Token> lex;    // analisador lexico
   private Token look;   // lookahead tag
	
	public ParserV3(ILexer<Token> l) throws IOException { 
		this.lex = l; 
		move(); 
	}

	void move() throws IOException { 
		look = lex.getNextToken(); 
	}
	
	void error(String s) { 
		throw new Error("na linha "+lex.getLine()+", coluna "+lex.getColumn()+": "+s); 
	}
	
	void match(int t) throws IOException {
		if( look.tag == t ) {
			move();
		}
		else {
			error("erro de sintaxe");
		}
	}
	
	
	void expr() throws IOException {
		term(); rest();
	}
	
	void rest() throws IOException {
		if (look.tag == '+') {
			match('+'); term(); System.out.print("+ "); rest();
		}
		else if (look.tag == '-') {
			match('-'); term(); System.out.print("- "); rest();
		}
		else return;	
	}
	
	void term() throws IOException {
		factor(); resto();
	}
	
	void resto() throws IOException {
		if (look.tag == '*') {
			match('*'); factor(); System.out.print("* "); resto();
		}
		else if (look.tag == '/') {
			match('/'); factor(); System.out.print("/ "); resto();
		}
		else return;	
	}
	
	void factor() throws IOException {
		switch( look.tag ) {
		case Tag.NUM:
			Num n = (Num) look;
			System.out.print(n.getValue() + " ");
			match(Tag.NUM);
			break;
		case Tag.ID:
			Word w = (Word) look;
			System.out.print(w.getLexeme() + " ");
			match(Tag.ID);
			break;
		default:
			error("erro de sintaxe");
			break;
		}
	}

}
