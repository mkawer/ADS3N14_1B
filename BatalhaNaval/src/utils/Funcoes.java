package utils;

import java.util.ArrayList;
import static java.util.Arrays.asList;

/**
 * Classe que contem funcoes uteis para a aplicacao
 * @author Lucas Pacheco Oliveira
 */
public class Funcoes {
    
    public static ArrayList<String> listaAlfabeto() {
        ArrayList<String> letras = new ArrayList<>(asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T"));
        return letras;
    }

    public static int getPosicaoAlfabeto(String letra) {
        return listaAlfabeto().indexOf(letra.toUpperCase());
    }
    
}
