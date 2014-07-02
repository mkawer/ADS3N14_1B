package model.ordenadores;

public class QuickSort
{
	private long qtdeComparacoes = 0;
	private long qtdeTrocas = 0;
	private long qtdeOperacoes = 0;
	private float qtdeTempoMiliSegundos;
	
	/**
	 * Retorna a quantidade total de comparacoes que foram efetuadas para realizar a ultima ordenacao.
	 * @return A quantidade total de comparacoes que foram efetuadas.
	 */
	public long getQtdeComparacoes()
	{
		return qtdeComparacoes;
	}
	
	/**
	 * Retorna a quantidade total de trocas que foram efetuadas para realizar a ultima ordenacao.
	 * @return A quantidade total de trocas que foram efetuadas.
	 */
	public long getQtdeTrocas()
	{
		return qtdeTrocas;
	}
	
	/**
	 * Retorna a quantidade total de operacoes aritmeticas que foram efetuadas para realizar a ultima ordenacao.
	 * @return A quantidade total de operacoes aritmeticas que foram efetuadas.
	 */
	public long getQtdeOperacoes()
	{
		return qtdeOperacoes;
	}
	
	/**
	 * Retorna a quantidade total de tempo (em milisegundos) que foram necessarios para realizar a ultima ordenacao.
	 * @return A quantidade total de tempo (em milisegundos) que foram necessarios.
	 */
	public float getQtdeTempoMiliSegundos()
	{
		return qtdeTempoMiliSegundos;
	}
	
	/**
	 * Ordena todos os elementos de uma array de inteiros, de modo ascendente, utilizando o algoritmo de ordenacao quicksort.
	 * @param array A array de inteiros que sera ordenada.
	 */
	public void sort(int[] array)
	{
		qtdeComparacoes = 0;
		qtdeTrocas = 0;
		qtdeOperacoes = 0;
		long tempoInicio = System.nanoTime();
		sort(array, 0, array.length - 1);
		qtdeTempoMiliSegundos = (System.nanoTime() - tempoInicio) / 1000000f;
	}
	
	/**
	 * Ordena um intervalo de uma array de inteiros, de modo ascendente, utilizando o algoritmo de ordenacao quicksort.
	 * @param array A array de inteiros cujo intervalo sera ordenado.
	 * @param inicio A posicao inicial do intervalo da array que sera ordenado.
	 * @param fim A posicao final do intervalo da array que sera ordenado.
	 */
	private void sort(int[] array, int inicio, int fim)
	{
		++qtdeComparacoes;
		if (inicio >= fim) {
			return;
		}
		
		int i = inicio;
		int f = fim;
		qtdeOperacoes += 2;
		int pivo = array[(i + f) / 2];
		int troca;
		
		while (true) {
			++qtdeComparacoes;
			if (!(i < f)) {
				break;
			}
			
			while (true) {
				++qtdeComparacoes;
				if (!(array[i] < pivo)) {
					break;
				}
				++qtdeOperacoes;
				++i;
			}
			
			while (true) {
				++qtdeComparacoes;
				if (!(array[f] > pivo)) {
					break;
				}
				++qtdeOperacoes;
				--f;
			}
			
			++qtdeComparacoes;
			if (i <= f) {
				++qtdeTrocas;
				troca = array[i];
				array[i] = array[f];
				array[f] = troca;
				qtdeOperacoes += 2;
				++i;
				--f;
			}
		}
		
		++qtdeComparacoes;
		if (f > inicio) {
			sort(array, inicio, f);
		}
		
		++qtdeComparacoes;
		if (i < fim) {
			sort(array, i, fim);
		}
	}
}
