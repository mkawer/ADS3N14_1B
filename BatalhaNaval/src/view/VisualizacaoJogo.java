package view;

import control.Tabuleiro;
import java.util.ArrayList;
import java.util.Scanner;
import model.Retorno;
import model.Jogo;

/**
 *
 * @author Lucas Pacheco Oliveira
 */
public class VisualizacaoJogo {
    
    private Jogo jogo;
    Tabuleiro tabuleiro;

    /**
     * Método construtor que inicializa a pontuacao do jogador, e o tabuleiro.
     */
    public VisualizacaoJogo() {
        this.jogo = new Jogo();
        this.tabuleiro = new Tabuleiro();
    }
    
    /**
     * Recebe e manipula o tabuleiro, escreve "." onde ainda não contér "O" ou "-"
     * Se desistiu for verdadeiro, mostra o tabuleiro com todos os navios (remove mascara)
     * @param tabuleiro
     * @param desistiu 
     */
    public void escreveTabueiro(Tabuleiro tabuleiro, boolean desistiu) {
        StringBuilder strLinha = new StringBuilder();
        ArrayList<ArrayList<String>> tab = tabuleiro.getTabuleiro();
        
        System.out.println("   A  B  C  D  E  F  G  H  I  J");
        for(int i=0;i<tab.size();i++){
            strLinha.append(i);
            strLinha.append("  ");
            for(int j=0;j<tab.get(i).size();j++){
                if(tab.get(i).get(j).equals("-")||tab.get(i).get(j).equals("O")) {
                    strLinha.append(tab.get(i).get(j));
                    
                } else {
                    strLinha.append(".");
                    //strLinha.append(tab.get(i).get(j));
                }
                strLinha.append("  ");
            }
            System.out.println(strLinha.toString());
            strLinha = new StringBuilder();
        }
        
    }

    /**
     * Resgata via console a entrada do usuario, espera uma Letra de A a J e um numero de 0 a 9
     * @return retorna o valor recebido via console do usuario.
     */
    private String solicitaPosicaoUsuario() {
        Scanner entradaUsuario = new Scanner(System.in);
        System.out.println("Digite a posição para a próxima jogada:\nEx: (A0,C7,H9) ou sair para abandonar a partida");
        String coordenada = entradaUsuario.next();
        if(coordenada.equals("sair")) {
            System.out.println("Você desistiu com : "+jogo.getPontuacao()+" pontos.");
            System.exit(0);
        }
        return coordenada;
    }
    
    public static void main(String[] args) {
        
        VisualizacaoJogo vJogo = new VisualizacaoJogo();
        vJogo.tabuleiro.inicia();
        String coordenadaUsuario = "";
        
        while(vJogo.jogo.getPontuacao()>0&&!vJogo.tabuleiro.fim()) {
            vJogo.escreveTabueiro(vJogo.tabuleiro, false);
            coordenadaUsuario = vJogo.solicitaPosicaoUsuario();
            Retorno jogada = vJogo.tabuleiro.joga(coordenadaUsuario, vJogo.jogo);
            System.out.println(jogada.getTipo()+" - "+jogada.getMensagem()+" - pontuação: ["+vJogo.jogo.getPontuacao()+"].");
        }
        
        vJogo.escreveTabueiro(vJogo.tabuleiro, true);
        if(vJogo.jogo.getPontuacao()>0) {
            System.out.println("Você ganhou com "+vJogo.jogo.getPontuacao()+" pontos.");
        } else {
            System.out.println("Você perdeu.");
        }
        
    }
    
}
