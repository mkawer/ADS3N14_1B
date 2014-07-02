package view;

import static java.lang.System.out;

import java.util.Scanner;

public class InteracaoUsuarioView
{
	private static Scanner scanner;
	
	/**
	 * Exibe uma mensagem e solicita ao usuario uma resposta.
	 * @param mensagem A mensagem a ser exibida.
	 * @return A resposta entrada pelo usuario.
	 */
	public static String solicitarEntrada(String mensagem)
	{
		scanner = new Scanner(System.in);
		out.print(mensagem);
		return scanner.nextLine();
	}
}
