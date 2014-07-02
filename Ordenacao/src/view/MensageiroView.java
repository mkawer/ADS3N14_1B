package view;

import static java.lang.System.out;

public class MensageiroView
{
	/**
	 * Imprime uma mensagem no terminal (sem quebra de linha ao final).
	 * @param mensagem A mensagem a ser imprimida.
	 */
	public static void imprime(String mensagem)
	{
		out.print(mensagem);
	}
	
	/**
	 * Imprime uma mensagem no terminal, quebrando a linha ao final.
	 * @param mensagem A mensagem a ser imprimida.
	 */
	public static void imprimeLinha(String mensagem)
	{
		out.println(mensagem);
	}
	
	/**
	 * Imprime uma quebra de linha no terminal.
	 */
	public static void imprimeLinha()
	{
		out.println();
	}
}
