package _testes;

import views.ImprimirArvoreView;
import estruturas.ArvoreBSP;

public class T_BSP
{
	public static void main(String[] args)
	{
		ArvoreBSP<Integer> a = new ArvoreBSP<>();
		for (int i : Constantes.numeros)
			a.insert(i);

		ImprimirArvoreView.imprimirArvore(a);
	}
}
