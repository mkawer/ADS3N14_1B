package estruturas;

public class ArvoreBSP<T extends Comparable<T>> extends Arvore<T>
{
	@Override
	public void insert(T elemento)
	{
		raiz = insert(raiz, elemento);
	}

	protected Nodo<T> insert(Nodo<T> atual, T elemento)
	{
		if (atual == null)
			return new Nodo<T>(elemento);

		int comp = elemento.compareTo(atual.getElemento());
		if (comp < 0)
			atual.setEsquerda(insert(atual.getEsquerda(), elemento));
		else if (comp > 0)
			atual.setDireita(insert(atual.getDireita(), elemento));

		return atual;
	}

	@Override
	public void delete(T elemento)
	{
		System.out.println("Remocao em Arvore BSP ainda nao implementado!");
	}
}
