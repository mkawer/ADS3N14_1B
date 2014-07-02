package estruturas;

public class ArvoreAVL<T extends Comparable<T>> extends ArvoreBSP<T>
{
	@Override
	protected Nodo<T> insert(Nodo<T> atual, T elemento)
	{
		if (atual == null)
			return new Nodo<T>(elemento);

		int comp = elemento.compareTo(atual.getElemento());
		if (comp < 0)
			atual.setEsquerdaComPai(insert(atual.getEsquerda(), elemento));
		else if (comp > 0)
			atual.setDireitaComPai(insert(atual.getDireita(), elemento));

		atual = balancear(atual);
		atual.setAltura(atual.calcularAltura() + 1);

		return atual;
	}

	private Nodo<T> balancear(Nodo<T> nodo)
	{
		nodo.atualizarFB();
		if (nodo.getFB() == 2) {
			if (nodo.getDireita().getFB() == -1)
				nodo.setDireitaComPai(rotaDireita(nodo.getDireita()));
			nodo = rotaEsquerda(nodo);
		} else if (nodo.getFB() == -2) {
			if (nodo.getEsquerda().getFB() == 1)
				nodo.setEsquerdaComPai(rotaEsquerda(nodo.getEsquerda()));
			nodo = rotaDireita(nodo);
		}
		return nodo;
	}

	@Override
	public void delete(T elemento)
	{
		System.out.println("Remocao em Arvore AVL ainda nao implementado!");
	}

	protected Nodo<T> rotaDireita(Nodo<T> n)
	{
		Nodo<T> b = n.getEsquerda();
		n.setEsquerda(b.getDireita());
		if (b.getDireita() != null)
			b.getDireita().setPai(n);
		b.setPai(n.getPai());
		if (n.getPai() == null) {
			raiz = b;
		} else {
			if (n == n.getPai().getEsquerda())
				n.getPai().setEsquerda(b);
			else
				n.getPai().setDireita(b);
		}
		b.setDireita(n);
		n.setPai(b);

		n.setAltura(n.calcularAltura() + 1);
		b.setAltura(b.calcularAltura() + 1);

		n = b;
		return b;
	}

	protected Nodo<T> rotaEsquerda(Nodo<T> n)
	{
		Nodo<T> b = n.getDireita();
		n.setDireita(b.getEsquerda());
		if (b.getEsquerda() != null)
			b.getEsquerda().setPai(n);
		b.setPai(n.getPai());
		if (n.getPai() == null) {
			raiz = b;
		} else {
			if (n == n.getPai().getEsquerda())
				n.getPai().setEsquerda(b);
			else
				n.getPai().setDireita(b);
		}
		b.setEsquerda(n);
		n.setPai(b);

		n.setAltura(n.calcularAltura() + 1);
		b.setAltura(b.calcularAltura() + 1);

		n = b;
		return b;
	}
}
