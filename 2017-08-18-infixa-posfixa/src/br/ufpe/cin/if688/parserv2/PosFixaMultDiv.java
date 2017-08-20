package br.ufpe.cin.if688.parserv2;

import java.io.IOException;

import br.ufpe.cin.if688.lexer.ILexer;
import br.ufpe.cin.if688.lexer.LexerSimples;

public class PosFixaMultDiv {
	public static void main(String[] args) throws IOException {
		//idealmente seria lido de um arquivo ou algo do tipo
		String input = "2+2";
		input = "9+2*3";
		input = "9+5/3";
		
		ILexer<Character> lexer = new LexerSimples(input);
		ParserV2 parse = new ParserV2(lexer);
		parse.expr();
		System.out.write('\n');
	}
}