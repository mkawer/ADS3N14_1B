package view;

import control.Tabuleiro;
import java.util.ArrayList;
import java.util.Scanner;
import model.Mensagens;
import model.Jogo;

/**
 *
 * @author Marcelo Rebelo
 */
public class ViewJogo {
    
    private Jogo jogo;
    Tabuleiro tabuleiro;

    /**
     * Inicializa as variaveis
     */
    public ViewJogo() {
        this.jogo = new Jogo();
        this.tabuleiro = new Tabuleiro();
    }
    
    /**
     * Monta o tabuleiro
     * @param tabuleiro
     * @param desistiu 
     */
    public void escreveTabueiro(Tabuleiro tabuleiro, boolean desistiu) {
        StringBuilder strLinha = new StringBuilder();
        ArrayList<ArrayList<String>> tab = tabuleiro.getTabuleiro();
        
        System.out.println("   A | B | C | D | E | F | G | H | I | J");
        for(int i=0;i<tab.size();i++){
            strLinha.append(i);
            strLinha.append(" | ");
            for(int j=0;j<tab.get(i).size();j++){
                if(tab.get(i).get(j).equals("-")||tab.get(i).get(j).equals("O")) {
                    if(j==tab.get(i).size()+1){
                        strLinha.append(tab.get(i).get(j));
                    } else {
                        strLinha.append(tab.get(i).get(j));
                        strLinha.append(" |");
                    }
                } else {
                    if(j==tab.get(i).size()+1){
                        strLinha.append(".");
                    } else {
                        strLinha.append(". |");
                    }
                }
                strLinha.append(" ");
            }
            System.out.println(strLinha.toString());
            System.out.println("------------------------------------------");
            strLinha = new StringBuilder();
        }
        
    }

    /**
     * Recebe do console a entrada do usuario, espera uma Letra de A a J e um numero de 0 a 9
     * @return retorna o valor recebido via console do usuario.
     */
    private String solicitaPosicaoUsuario() {
        Scanner entradaUsuario = new Scanner(System.in);
        System.out.println("Digite a posição para a próxima jogada:\nPrimeiro a coluna, seguido da linha. Ex(A0)");
        String coordenada = entradaUsuario.next();
        if(coordenada.equals("sair")) {
            System.out.println("Você desistiu, sua pontuação foi : "+jogo.getPontos());
            System.exit(0);
        }
        return coordenada;
    }
    
    public static void main(String[] args) {
        
        ViewJogo viewJogo = new ViewJogo();
        viewJogo.tabuleiro.inicio();
        String coordenadaUsuario = "";
        
        while(viewJogo.jogo.getPontos()>0&&!viewJogo.tabuleiro.fim()) {
            viewJogo.escreveTabueiro(viewJogo.tabuleiro, false);
            coordenadaUsuario = viewJogo.solicitaPosicaoUsuario();
            Mensagens tentativa = viewJogo.tabuleiro.joga(coordenadaUsuario, viewJogo.jogo);
            System.out.println(tentativa.getTp()+" - "+tentativa.getMsg()+" - pontuação: ["+viewJogo.jogo.getPontos()+"].");
        }
        
        viewJogo.escreveTabueiro(viewJogo.tabuleiro, true);
        if(viewJogo.jogo.getPontos()>0) {
            System.out.println("Você ganhou, sua pontuação foi: "+viewJogo.jogo.getPontos());
        } else {
            System.out.println("Perdeu.");
        }
        
    }
    
}
