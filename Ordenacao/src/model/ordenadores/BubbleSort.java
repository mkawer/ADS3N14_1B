package model.ordenadores;

public class BubbleSort
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
	 * Ordena uma array de inteiros de modo ascendente.
	 * @param array A array de inteiros que sera ordenada.
	 */
	public void sort(int[] array)
	{
		qtdeComparacoes = 0;
		qtdeTrocas = 0;
		qtdeOperacoes = 0;
		long tempoInicio = System.nanoTime();
		
		int dadoTroca;
		int indiceProximo;
		int i = array.length - 1;
		int j;
		
		while (true) {
			++qtdeComparacoes;
			if (!(i > 0)) {
				break;
			}
			
			j = 0;
			while (true) {
				++qtdeComparacoes;
				if (!(j < i)) {
					break;
				}
				
				++qtdeOperacoes;
				indiceProximo = j + 1;
				++qtdeComparacoes;
				if (array[j] > array[indiceProximo]) {
					++qtdeTrocas;
					dadoTroca = array[j];
					array[j] = array[indiceProximo];
					array[indiceProximo] = dadoTroca;
				}
				
				++qtdeOperacoes;
				++j;
			}
			
			++qtdeOperacoes;
			--i;
		}
		
		qtdeTempoMiliSegundos = (System.nanoTime() - tempoInicio) / 1000000f;
	}
}
