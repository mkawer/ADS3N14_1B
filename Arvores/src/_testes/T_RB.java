package _testes;

import views.ImprimirArvoreView;
import estruturas.ArvoreRB;

public class T_RB
{
	public static void main(String[] args)
	{
		ArvoreRB<Integer> a = new ArvoreRB<Integer>();
		for (int i : Constantes.numeros)
			a.insert(i);
		a.delete(8);
		
//		ArvoreRB<String> a = new ArvoreRB<String>();
//		for (String i : Constantes.letras)
//			a.insert(i);
//		a.delete("N");

		ImprimirArvoreView.imprimirArvore(a);
	}
}
