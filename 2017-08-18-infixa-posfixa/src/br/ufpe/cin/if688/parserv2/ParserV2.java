package br.ufpe.cin.if688.parserv2;

import java.io.IOException;

import br.ufpe.cin.if688.lexer.ILexer;

public class ParserV2 {
	static Character lookahead;
	ILexer<Character> lexer;
	public ParserV2(ILexer<Character> lexer) throws IOException {
		this.lexer = lexer;
		lookahead = lexer.getNextToken();
	}
	
	void expr() throws IOException {
		term(); rest();
	}
	
	void rest() throws IOException {
		if (lookahead == '+') {
			match('+'); term(); System.out.write('+'); rest();
		}
		else if (lookahead == '-') {
			match('-'); term(); System.out.write('-'); rest();
		}
		else return;	
	}
	
	void term() throws IOException {
		factor(); resto();
	}
	
	void resto() throws IOException {
		if (lookahead == '*') {
			match('*'); factor(); System.out.write('*'); resto();
		}
		else if (lookahead == '/') {
			match('/'); factor(); System.out.write('/'); resto();
		}
		else return;	
	}
	
	void factor() throws IOException {
		char t = lookahead;
		if (Character.isDigit(t)) {
			match(lookahead);
			System.out.write(t); 
		}
		else {
			throw new Error("erro de sintaxe");
		}
	}
	
	void match(char t) throws IOException {
		if (lookahead == t) lookahead = lexer.getNextToken();
		else throw new Error("erro de sintaxe");
	}
}
