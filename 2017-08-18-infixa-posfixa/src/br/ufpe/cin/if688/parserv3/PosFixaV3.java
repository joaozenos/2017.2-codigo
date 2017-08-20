package br.ufpe.cin.if688.parserv3;

import java.io.IOException;

import br.ufpe.cin.if688.lexer.ILexer;
import br.ufpe.cin.if688.lexer.LexerTokens;
import br.ufpe.cin.if688.lexer.tokens.Token;

public class PosFixaV3 {
	public static void main(String[] args) throws IOException {
		//TODO corrigir erro quando tem espaço no final
		String input = "    12     +      232";
		input = "2-3+4-5+6-89+122-32+44";
		input = "12+33*54";
		
		ILexer<Token> lexer = new LexerTokens(input);
		ParserV3 parse = new ParserV3(lexer);
		
		parse.expr();
		
		System.out.write('\n');
	}
}
