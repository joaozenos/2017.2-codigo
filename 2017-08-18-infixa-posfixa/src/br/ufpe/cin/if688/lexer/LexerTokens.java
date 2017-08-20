package br.ufpe.cin.if688.lexer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import br.ufpe.cin.if688.lexer.tokens.Num;
import br.ufpe.cin.if688.lexer.tokens.Tag;
import br.ufpe.cin.if688.lexer.tokens.Token;
import br.ufpe.cin.if688.lexer.tokens.Word;

public class LexerTokens implements ILexer<Token> {
	private int column = 0;
	private int line = 1;
	private String input;
	private char peek = ' ';

	public LexerTokens(String in) {
		input = in;		
		reserve(new Word(Tag.TRUE, "true"));
		reserve(new Word(Tag.FALSE, "false"));
	}
	

	private Hashtable<String,Word> words = new Hashtable<>();
	
	void reserve(Word w) {
		words.put(w.lexeme, w);
	}
	
	public Token getNextToken() throws IOException {
		return scan();
	}
	
	private Token scan() throws IOException {
		if (column < input.length()) {
			while(column < input.length()) {			
				peek = input.charAt(column);
				if ( peek == ' ' || peek == '\t' ) {
					column++;
				}
				else if ( peek == '\n' ) {
					line = line + 1;
					column++;
					//normalmente faria algo como column = 0;
				}
				else {
					break;
				}
			}
			
			if (Character.isDigit(peek)) {
				StringBuilder sb = new StringBuilder();
				//TODO considerar remover! 
				sb.append(peek);
				column++;
				while ((column < input.length()) && Character.isDigit(input.charAt(column))) {
					peek = input.charAt(column);
					sb.append(peek);
					column++;
				} 
				int v = Integer.parseInt(sb.toString());
				return new Num(v);
			}
			
			if (Character.isLetter(peek)) {
				StringBuilder sb = new StringBuilder();
				sb.append(peek);
				column++;
				while ((column < input.length()) && Character.isLetter(input.charAt(column))) {
					peek = input.charAt(column);
					sb.append(peek);
					column++;
				}
				String s = sb.toString();
				Word w = words.get(s);
				if (w != null) {
					return w;
				}
				w = new Word(Tag.ID,s);
				reserve(w);
				return w;
			}
			
			if (peek == '+' || peek == '-' || peek == '*' || peek == '/') {
				Token t = new Token(peek);
				column++;
				peek = ' ';
				return t;
			}
			else {
				throw new Error("Símbolo não reconhecido: "+peek);
			}
		}
		else {
			return new Token('$');
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
