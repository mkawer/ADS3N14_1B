package estruturas;

public class Nodo<T extends Comparable<T>>
{
	private static final boolean COR_PRETO = false;
	private static final boolean COR_VERMELHO = true;
	private T elemento;
	private Nodo<T> esquerda;
	private Nodo<T> direita;
	private Nodo<T> pai;
	private boolean cor;
	private int altura;
	private int fb;

	public Nodo(T elemento)
	{
		this.elemento = elemento;
		this.esquerda = null;
		this.direita = null;
		this.pai = null;
		this.cor = COR_VERMELHO;
		this.altura = 1;
		this.fb = 0;
	}

	public T getElemento()
	{
		return elemento;
	}

	public void setElemento(T elemento)
	{
		this.elemento = elemento;
	}

	public Nodo<T> getEsquerda()
	{
		return esquerda;
	}

	public void setEsquerda(Nodo<T> nodo)
	{
		this.esquerda = nodo;
	}

	public void setEsquerdaComPai(Nodo<T> nodo)
	{
		setEsquerda(nodo);
		if (this.esquerda != null)
			this.esquerda.setPai(this);
	}

	public Nodo<T> getDireita()
	{
		return direita;
	}

	public void setDireita(Nodo<T> nodo)
	{
		this.direita = nodo;
	}

	public void setDireitaComPai(Nodo<T> nodo)
	{
		setDireita(nodo);
		if (this.direita != null)
			this.direita.setPai(this);
	}

	public Nodo<T> getPai()
	{
		return pai;
	}

	public void setPai(Nodo<T> nodo)
	{
		this.pai = nodo;
	}

	public boolean getCor()
	{
		return cor;
	}

	public void setCor(boolean cor)
	{
		this.cor = cor;
	}

	public int getAltura()
	{
		return altura;
	}

	public void setAltura(int altura)
	{
		this.altura = altura;
	}

	public int getFB()
	{
		return fb;
	}

	public boolean isFilhoEsquerda()
	{
		return (pai != null && this == pai.getEsquerda());
	}

	public boolean isFilhoDireita()
	{
		return (pai != null && this == pai.getDireita());
	}

	public boolean isCorPreto()
	{
		return (cor == COR_PRETO);
	}

	public boolean isCorVermelho()
	{
		return (cor == COR_VERMELHO);
	}

	public void setCorPreto()
	{
		cor = COR_PRETO;
	}

	public void setCorVermelho()
	{
		cor = COR_VERMELHO;
	}

	protected Nodo<T> getAvo()
	{
		return (pai == null ? null : pai.getPai());
	}

	protected Nodo<T> getTio()
	{
		Nodo<T> g = getAvo();
		if (g == null)
			return null;
		return (pai == g.getEsquerda() ? g.getDireita() : g.getEsquerda());
	}

	protected Nodo<T> getIrmao()
	{
		if (pai == null)
			return null;
		return (	this == pai.getEsquerda() ? pai.getDireita() : pai.getEsquerda());
	}

	protected void atualizarFB()
	{
		int alturaEsquerda = (esquerda == null ? 0 : esquerda.getAltura());
		int alturaDireita = (direita == null ? 0 : direita.getAltura());
		this.fb = (alturaDireita - alturaEsquerda);
	}

	protected int calcularAltura()
	{
		int alturaEsquerda = (esquerda == null ? 0 : esquerda.getAltura());
		int alturaDireita = (direita == null ? 0 : direita.getAltura());
		return (alturaEsquerda > alturaDireita ? alturaEsquerda : alturaDireita);
	}

	protected int calcularFB()
	{
		int alturaEsquerda = (esquerda == null ? 0 : esquerda.getAltura());
		int alturaDireita = (direita == null ? 0 : direita.getAltura());
		return (alturaDireita - alturaEsquerda);
	}
}
