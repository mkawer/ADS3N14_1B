package model;

/**
 * Classe responsavel pela manipulacao da pontuacao do usuario
 *
 * @author Lucas Pacheco Oliveira
 */
public class Jogo {

    int pontuacao = 15;

    /**
     * Retorna a pontuacao atual
     */
    public int getPontuacao() {
        return this.pontuacao;
    }

    /**
     * Metodo que adiciona 5 pontos a pontuacao atual
     */
    public void acertouNavio() {
        pontuacao += 5;
    }

    /**
     * Metodo que adiciona 3 pontos a pontuacao atual
     */
    public void acertou() {
        pontuacao += 3;
    }

    /**
     * Metodo que remove 1 ponto a pontuacao atual
     */
    public void errou() {
        pontuacao--;
    }

}
