package view;

import static java.lang.System.out;

import java.text.NumberFormat;

import model.ordenadores.BubbleSort;
import model.ordenadores.QuickSort;

public class RelatorioOrdenadoresView
{
	/**
	 * Imprime o relatorio final de comparacao do desempenho dos dois ordenadores.
	 * @param bs O ordenador BubbleSort.
	 * @param qs O ordenador QuickSort.
	 */
	public static void print(BubbleSort bs, QuickSort qs)
	{
		out.println("\nRELATORIO FINAL");
		out.println("\n   Quantidade de comparacoes:");
		out.println("      BubbleSort: " + formatarNumero(bs.getQtdeComparacoes()));
		out.println("       QuickSort: " + formatarNumero(qs.getQtdeComparacoes()));
		out.println("\n   Quantidade de trocas:");
		out.println("      BubbleSort: " + formatarNumero(bs.getQtdeTrocas()));
		out.println("       QuickSort: " + formatarNumero(qs.getQtdeTrocas()));
		out.println("\n   Quantidade de operacoes aritmeticas:");
		out.println("      BubbleSort: " + formatarNumero(bs.getQtdeOperacoes()));
		out.println("       QuickSort: " + formatarNumero(qs.getQtdeOperacoes()));
		out.println("\n   Quantidade de tempo (em milisegundos):");
		out.println("      BubbleSort: " + formatarNumero(bs.getQtdeTempoMiliSegundos()));
		out.println("       QuickSort: " + formatarNumero(qs.getQtdeTempoMiliSegundos()));
	}
	
	private static String formatarNumero(Number numero)
	{
		return NumberFormat.getIntegerInstance().format(numero);
	}
}
