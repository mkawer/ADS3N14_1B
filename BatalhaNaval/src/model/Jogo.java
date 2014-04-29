package model;

/**
 * Manipula a pontuacao do jogador
 * @author Marcelo Rebelo
 */
public class Jogo {

    int pontos = 15;

    /**
     * Retorna a pontuacao atual
     */
    public int getPontos() {
        return this.pontos;
    }

    /**
     * Adiciona 5 pontos a pontuacao atual
     */
    public void acertouNavio() {
        pontos += 5;
    }

    /**
     * Adiciona 3 pontos a pontuacao atual
     */
    public void acerto() {
        pontos += 3;
    }

    /**
     * Remove 1 ponto a pontuacao atual
     */
    public void erro() {
        pontos--;
    }

}
