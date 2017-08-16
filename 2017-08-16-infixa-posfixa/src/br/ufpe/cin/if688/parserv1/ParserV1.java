package br.ufpe.cin.if688.parserv1;

import java.io.IOException;

public class ParserV1 {
	static Character lookahead;
	ILexer<Character> lexer;
	
	public ParserV1(ILexer<Character> lexer) throws IOException {
		this.lexer = lexer;
		lookahead = lexer.getNextToken();
	}
	
	void expr() throws IOException {
		term(); rest();
	}
	
	void rest() throws IOException {
		if (lookahead == '+') {
			match('+'); 
			term(); 
			System.out.write('+'); 
			rest();
		}
		else if (lookahead == '-') {
			match('-'); 
			term(); 
			System.out.write('-'); 
			rest();
		}
		else return;	
	}
	
	void term() throws IOException {
		char t = lookahead;
		if (Character.isDigit(t)) {
			match(lookahead);
			System.out.write(t); 
		}
		else {
			throw new Error("erro de sintaxe - esperava um digito");
		}
	}
	
	void match(char t) throws IOException {
		if (lookahead == t) lookahead = lexer.getNextToken();
		else throw new Error("erro de sintaxe, digitou '"+lookahead+"' e era esperado '"+t+"'");
	}
}
