package controller;

import java.util.ArrayList;
import java.util.Random;

import model.ordenadores.BubbleSort;
import model.ordenadores.QuickSort;
import view.InteracaoUsuarioView;
import view.MensageiroView;
import view.RelatorioOrdenadoresView;

public class OrdenacaoController
{
	private int[] arrayA;
	private int[] arrayB;
	private int tamanhoArray;
	
	public OrdenacaoController()
	{
		this.arrayA = null;
		this.arrayB = null;
		this.tamanhoArray = 0;
	}
	
	public void run()
	{
		this.tamanhoArray = solicitarTamanhoArray();
		boolean permitirNumerosRepetidos = solicitarPadraoArray();
		
		MensageiroView.imprime("\nGerando array ... ");
		gerarArray(permitirNumerosRepetidos);
		MensageiroView.imprimeLinha("concluido!");
		
		BubbleSort bs = new BubbleSort();
		QuickSort qs = new QuickSort();
		
		MensageiroView.imprime("\nOrdenando com o BubbleSort ... ");
		bs.sort(arrayA);
		MensageiroView.imprimeLinha("concluido!");
		
		MensageiroView.imprime("Ordenando com o QuickSort ... ");
		qs.sort(arrayB);
		MensageiroView.imprimeLinha("concluido!");
		
		MensageiroView.imprime("\nVerificando ordenacao do BubbleSort ... ");
		MensageiroView.imprimeLinha((verificarOrdenacao(arrayA) ? "ok!" : "incorreta!"));
		
		MensageiroView.imprime("Verificando ordenacao do QuickSort ... ");
		MensageiroView.imprimeLinha((verificarOrdenacao(arrayB) ? "ok!" : "incorreta!"));
		
		RelatorioOrdenadoresView.print(bs, qs);
	}
	
	private boolean verificarOrdenacao(int[] a)
	{
		int anterior = a[0];
		for (int i = 0; i < a.length; i++) {
			if (a[i] < anterior) {
				return false;
			}
			anterior = a[i];
		}
		return true;
	}
	
	private int solicitarTamanhoArray()
	{
		int entrada;
		while (true) {
			try {
				entrada = Integer.parseInt(
						InteracaoUsuarioView.solicitarEntrada("Digite o tamanho da array (entre 3 e 150.000.000): ")
				);
				if (entrada >= 3 && entrada <= 150000000) {
					return entrada;
				}
				MensageiroView.imprimeLinha("   Valor invalido, tente novamente!");
			} catch (NumberFormatException ex) {
				MensageiroView.imprimeLinha("   Entrada invalida, tente novamente!");
			}
		}
	}
	
	private boolean solicitarPadraoArray()
	{
		String entrada;
		while (true) {
			entrada = InteracaoUsuarioView.solicitarEntrada("Permitir numeros repetidos na array (s/n): ").toUpperCase();
			if (entrada.equals("S")) {
				return true;
			}
			if (entrada.equals("N")) {
				return false;
			}
			MensageiroView.imprimeLinha("   Entrada invalida, tente novamente!");
		}
	}
	
	private void gerarArray(boolean permitirNumerosRepetidos)
	{
		this.arrayA = new int[this.tamanhoArray];
		this.arrayB = new int[this.tamanhoArray];
		Random random = new Random();
		int i;
		
		if (permitirNumerosRepetidos) {
			int valorMaximo = this.tamanhoArray + (this.tamanhoArray / 2) + 1;
			for (i = 0; i < this.tamanhoArray; i++) {
				this.arrayA[i] = random.nextInt(valorMaximo);
				this.arrayB[i] = this.arrayA[i];
			}
		} else {
			ArrayList<Integer> lista = new ArrayList<Integer>(this.tamanhoArray);
			for (i = 0; i < this.tamanhoArray; i++) {
				lista.add(i);
			}
			for (i = 0; i < this.tamanhoArray; i++) {
				this.arrayA[i] = lista.remove(random.nextInt(lista.size()));
				this.arrayB[i] = this.arrayA[i];
			}
		}
	}
}
