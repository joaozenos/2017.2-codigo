package br.ufpe.cin.if688.lexer;

import java.io.IOException;

public class LexerSimples implements ILexer<Character> {
	private int column = 0;
	private int line = 1;
	private String input;
	
	public LexerSimples(String in) {
		this.input = in;
	}
	
	@Override
	public Character getNextToken() throws IOException {
		if (column < input.length()) {
			Character c = input.charAt(column); 
			column++;
			return c;
		}
		else {
			return '$';
		}
	}

	@Override
	public int getLine() {
		return line;
	}

	@Override
	public int getColumn() {
		return column;
	}
}
