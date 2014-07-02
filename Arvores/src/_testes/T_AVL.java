package _testes;

import views.ImprimirArvoreView;
import estruturas.ArvoreAVL;

public class T_AVL
{
	public static void main(String[] args)
	{
		ArvoreAVL<Integer> a = new ArvoreAVL<>();
		for (int i : Constantes.numeros)
			a.insert(i);

		ImprimirArvoreView.imprimirArvore(a);
	}
}
