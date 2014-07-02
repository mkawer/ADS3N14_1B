package estruturas;

public class ArvoreRB<T extends Comparable<T>> extends ArvoreAVL<T>
{
	@Override
	public void insert(T elemento)
	{
		Nodo<T> atual = raiz;
		Nodo<T> atualPai = null;
		int comp = 0;

		// procurando a posicao do novo nodo:
		while (atual != null) {
			atualPai = atual;
			comp = elemento.compareTo(atual.getElemento());
			if (comp < 0)
				atual = atual.getEsquerda();
			else if (comp > 0)
				atual = atual.getDireita();
			else
				return;
		}

		// criando o novo nodo e adicionando-o a arvore na posicao correta:
		Nodo<T> novo = new Nodo<T>(elemento);
		if (atualPai == null) {
			raiz = novo;
		} else {
			if (comp < 0)
				atualPai.setEsquerdaComPai(novo);
			else if (comp > 0)
				atualPai.setDireitaComPai(novo);
		}

		// iniciando a validacao da arvore:
		insertCaso1(novo);
	}

	private void insertCaso1(Nodo<T> nodo)
	{
		if (nodo.getPai() == null)
			nodo.setCorPreto();
		else
			insertCaso2(nodo);
	}

	private void insertCaso2(Nodo<T> nodo)
	{
		if (!nodo.getPai().isCorPreto())
			insertCaso3(nodo);
	}

	private void insertCaso3(Nodo<T> nodo)
	{
		Nodo<T> avo = null;
		Nodo<T> tio = nodo.getTio();
		if (tio != null && tio.isCorVermelho()) {
			nodo.getPai().setCorPreto();
			tio.setCorPreto();
			avo = nodo.getAvo();
			avo.setCorVermelho();
			insertCaso1(avo);
		} else {
			insertCaso4(nodo);
		}
	}

	private void insertCaso4(Nodo<T> nodo)
	{
		Nodo<T> pai = nodo.getPai();
		Nodo<T> avo = nodo.getAvo();
		if (nodo == pai.getDireita() && pai == avo.getEsquerda()) {
			pai = rotaEsquerda(pai);
			nodo = nodo.getEsquerda();
		} else if (nodo == pai.getEsquerda() && pai == avo.getDireita()) {
			pai = rotaDireita(pai);
			nodo = nodo.getDireita();
		}
		insertCaso5(nodo);
	}

	private void insertCaso5(Nodo<T> nodo)
	{
		Nodo<T> avo = nodo.getAvo();
		nodo.getPai().setCorPreto();
		avo.setCorVermelho();
		if (nodo == nodo.getPai().getEsquerda())
			avo = rotaDireita(avo);
		else
			avo = rotaEsquerda(avo);
	}

	@Override
	public void delete(T elemento)
	{
		Nodo<T> atual = raiz;
		int comp = 0;
		while (atual != null) {
			comp = elemento.compareTo(atual.getElemento());
			if (comp < 0)
				atual = atual.getEsquerda();
			else if (comp > 0)
				atual = atual.getDireita();
			else
				break;
		}

		if (atual == null)
			return;

		if (atual.getEsquerda() != null && atual.getDireita() != null) {
			Nodo<T> pred = maxNodo(atual.getEsquerda());
			atual.setElemento(pred.getElemento());
			atual = pred;
		}

		Nodo<T> filho = (atual.getDireita() == null ? atual.getEsquerda() : atual.getDireita());
		if (filho != null)
			atual.setCor(filho.getCor());

		if (atual.isCorPreto())
			deleteCaso1(atual);
		substituirNodo(atual, filho);
	}

	private void deleteCaso1(Nodo<T> n)
	{
		if (n.getPai() != null)
			deleteCaso2(n);
	}

	private void deleteCaso2(Nodo<T> n)
	{
		Nodo<T> p = n.getPai();
		Nodo<T> s = n.getIrmao();
		if (n.isCorPreto() && p.isCorPreto() && s.isCorVermelho()) {
			p.setCorVermelho();
			s.setCorPreto();
			if (n.isFilhoEsquerda())
				p = rotaEsquerda(p);
			else
				p = rotaDireita(p);
		}
		deleteCaso3(n);
	}

	private void deleteCaso3(Nodo<T> n)
	{
		Nodo<T> p = n.getPai();
		Nodo<T> s = n.getIrmao();
		if (n.isCorPreto() && p.isCorPreto() && s.isCorPreto()
				&& (s.getEsquerda() == null || s.getEsquerda().isCorPreto())
				&& (s.getDireita() == null || s.getDireita().isCorPreto())) {
			s.setCorVermelho();
			deleteCaso1(p);
		} else {
			deleteCaso4(n);
		}
	}

	private void deleteCaso4(Nodo<T> n)
	{
		Nodo<T> p = n.getPai();
		Nodo<T> s = n.getIrmao();
		if (n.isCorPreto()
				&& p.isCorVermelho()
				&& s.isCorPreto()
				&& (s.getEsquerda() == null || s.getEsquerda().isCorPreto()
						&& (s.getDireita() == null || s.getDireita().isCorPreto()))) {
			p.setCorPreto();
			s.setCorVermelho();
		} else {
		}
		deleteCaso5(n);
	}

	private void deleteCaso5(Nodo<T> n)
	{
		Nodo<T> s = n.getIrmao();

		if (n.isFilhoEsquerda() && (s != null && s.isCorPreto())
				&& (s.getDireita() == null || s.getDireita().isCorPreto())
				&& (s.getEsquerda() != null && s.getEsquerda().isCorVermelho())) {
			s.setCorVermelho();
			s.getEsquerda().setCorPreto();
			s = rotaDireita(s);
		} else if (n.isFilhoDireita() && (s != null && s.isCorPreto())
				&& (s.getEsquerda() == null || s.getEsquerda().isCorPreto())
				&& (s.getDireita() != null && s.getDireita().isCorVermelho())) {
			s.setCorVermelho();
			s.getDireita().setCorPreto();
			s = rotaEsquerda(s);
		}
		deleteCaso6(n);
	}

	private void deleteCaso6(Nodo<T> n)
	{
		Nodo<T> p = n.getPai();
		Nodo<T> s = n.getIrmao();
		if (n.isFilhoEsquerda()) {
			// s.setCor(p.getCor());
			p.setCorPreto();
			s.getDireita().setCorPreto();
			p = rotaEsquerda(p);
		} else {
			// s.setCor(p.getCor());
			p.setCorPreto();
			s.getEsquerda().setCorPreto();
			p = rotaDireita(p);
		}
	}

	private Nodo<T> maxNodo(Nodo<T> nodo)
	{
		while (nodo.getDireita() != null)
			nodo = nodo.getDireita();
		return nodo;
	}

	private void substituirNodo(Nodo<T> atual, Nodo<T> novo)
	{
		if (atual.getPai() == null) {
			raiz = novo;
		} else {
			if (atual == atual.getPai().getEsquerda())
				atual.getPai().setEsquerda(novo);
			else
				atual.getPai().setDireita(novo);
		}
		if (novo != null)
			novo.setPai(atual.getPai());
	}
}
