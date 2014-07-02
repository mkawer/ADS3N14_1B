package estruturas;

import java.util.ArrayList;
import java.util.List;

abstract public class Arvore<T extends Comparable<T>>
{
	protected Nodo<T> raiz;

	public Arvore()
	{
		this.raiz = null;
	}

	public Nodo<T> getRaiz()
	{
		return raiz;
	}

	abstract public void insert(T elemento);

	abstract public void delete(T elemento);

	public List<T> preOrder()
	{
		List<T> lista = new ArrayList<T>();
		preOrder(raiz, lista);
		return lista;
	}

	private void preOrder(Nodo<T> atual, List<T> lista)
	{
		if (atual == null)
			return;
		lista.add(atual.getElemento());
		preOrder(atual.getEsquerda(), lista);
		preOrder(atual.getDireita(), lista);
	}

	public List<T> inOrder()
	{
		List<T> lista = new ArrayList<T>();
		inOrder(raiz, lista);
		return lista;
	}

	private void inOrder(Nodo<T> atual, List<T> lista)
	{
		if (atual == null)
			return;
		inOrder(atual.getEsquerda(), lista);
		lista.add(atual.getElemento());
		inOrder(atual.getDireita(), lista);
	}

	public List<T> postOrder()
	{
		List<T> lista = new ArrayList<T>();
		postOrder(raiz, lista);
		return lista;
	}

	private void postOrder(Nodo<T> atual, List<T> lista)
	{
		if (atual == null)
			return;
		postOrder(atual.getEsquerda(), lista);
		postOrder(atual.getDireita(), lista);
		lista.add(atual.getElemento());
	}
}
