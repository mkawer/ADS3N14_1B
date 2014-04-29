package utils;

import java.util.ArrayList;
import static java.util.Arrays.asList;

/**
 * Contem funcoes uteis para a aplicacao
 * @author Marcelo Rebelo
 */
public class Funcoes {
    
    public static ArrayList<String> alfabeto() {
        ArrayList<String> letras = new ArrayList<>(asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T"));
        return letras;
    }

    public static int getPosAlfabeto(String letra) {
        return alfabeto().indexOf(letra.toUpperCase());
    }
    
}
