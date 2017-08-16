package br.ufpe.cin.if688.parserv1;

import java.io.IOException;

public class PosFixaV1 {
	public static void main(String[] args) throws IOException {
		//idealmente seria lido de um arquivo ou algo do tipo
		String input = "2+2";
		//vai dar erro...
		input = "9+(2-3)";
		//vai retornar e nao sinaliza o erro
		input = "9+2*3";
		//cheque se a traducao esta correta
		input = "9-2+3+4-5+1+8-5+2";
		
		ILexer<Character> lexer = new LexerSimples(input);
		ParserV1 parser = new ParserV1(lexer);
		parser.expr();
		System.out.write('\n');
	}
}