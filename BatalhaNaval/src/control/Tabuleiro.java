package control;

import model.Navios;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.Random;
import model.Mensagens;
import model.Jogo;
import utils.Funcoes;

/**
 * Responsavel por manipular o jogo
 * @author Marcelo Rebelo
 */
public class Tabuleiro {

    private ArrayList<ArrayList<String>> linhas;
    private String coordenadas;

    /**
     * Monta um tabuleiro, 10 linha e 10 colunas
     */
    public Tabuleiro() {
        this.linhas = new ArrayList<>();
        ArrayList<String> linha;
        for (int i = 0; i < 10; i++) {
            linha = new ArrayList();
            for (int j = 0; j < 10; j++) {
                linha.add(".");
            }
            this.linhas.add(linha);
        }
    }

    /**
     * Retorna o tabuleiro montado
     * @return 
     */
    public ArrayList<ArrayList<String>> getTabuleiro() {
        return this.linhas;
    }

    /**
     * Inicia o tabuleiro, adiciona as peças, aleatoriamente
     * Adiciona 1 porta-avioes [A] (5 posicoes);
     * <ul>
     * <li>2 Destroyers [B,C] (4 posicoes)</li>
     * <li>2 Fragatas [D,E] (3 posicoes)</li>
     * <li>3 Torpedeiros [F,G,H] (2 posicoes)</li>
     * <li>5 Submarinos [I,J,K,L,M] (1 posicao)</li>
     * </ul>
     */
    public void inicio() {
        Navios navio = new Navios();
        Random rnd = new Random();
        int linha, coluna, nPeca;
        
        nPeca = 0;

        ArrayList<ArrayList<String>> listaPecas = new ArrayList<>(
                asList(
                        navio.getPortaAvioes(), navio.getDestroyer(), navio.getDestroyer(),
                        navio.getFragata(), navio.getFragata(), navio.getTorpedeiro(), navio.getTorpedeiro(),
                        navio.getTorpedeiro(), navio.getSubmarino(), navio.getSubmarino(), navio.getSubmarino(),
                        navio.getSubmarino(), navio.getSubmarino()
                )
        );

        for (ArrayList<String> obj : listaPecas) {
            ArrayList<String> lPecas = Funcoes.alfabeto();
            linha = rnd.nextInt(10);
            coluna = rnd.nextInt(10);
            while (!adiciona(obj, linha, coluna, lPecas.get(nPeca))) {
                linha = rnd.nextInt(9);
                coluna = rnd.nextInt(9);
            }
            nPeca++;
        }
    }

    /**
     * Verifica e aloca na matriz um objeto. Se não for possivel alocar, retorna falso.
     * @param peca
     * @param linha Linha que devera ser alocada a peca
     * @param coluna Coluna que devera ser alocada a peca
     * @param strPeca Letra referente a peca que sera adicionado
     * @return 
     */
    private boolean adiciona(List<String> peca, int linha, int coluna, String strPeca) {
        int j = 0;
        if (coluna + peca.size() > 10) {
            return false;
        } else {
            for (int i = coluna; i < coluna + peca.size(); i++) {
                if (!this.linhas.get(linha).get(i).equals(".")) {
                    return false;
                }
            }
            for (int i = coluna; i < coluna + peca.size(); i++) {
                this.linhas.get(linha).set(i, strPeca);
                j++;
            }
        }
        return true;
    }

    /**
     * Recebe o valor do jogador e o transforma em coordenadas
     * @param coordenada
     * @return 
     */
    private ArrayList<Integer> getCoordenadas(String coordenada) {
        try {
            this.coordenadas = coordenada;
            Integer pos1 = Integer.valueOf(coordenada.substring(1, 2));
            Integer pos2 = Funcoes.getPosAlfabeto(coordenada.substring(0, 1));
            ArrayList<Integer> retorno = new ArrayList<>(asList(pos1, pos2));
            return retorno;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Verifica se existe objetos da mesma peca na linha da ultima jogada.
     * @param atingido
     * @param linha
     * @return 
     */
    public boolean navioAtingido(String atingido, int linha) {
        for (int i = 0; i < this.linhas.get(linha).size(); i++) {
            if (this.linhas.get(linha).get(i).equals(atingido)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Executa e computa a jogada.
     * @param coordenadas
     * @param jogo
     * @return 
     */
    public Mensagens realizaJogada(ArrayList<Integer> coordenadas, Jogo jogo) {
        Mensagens e = new Mensagens();
        if (!this.linhas.get(coordenadas.get(0)).get(coordenadas.get(1)).equals("O") && !this.linhas.get(coordenadas.get(0)).get(coordenadas.get(1)).equals("-")) {
            if (this.linhas.get(coordenadas.get(0)).get(coordenadas.get(1)).equals(".")) {
                this.linhas.get(coordenadas.get(0)).set(coordenadas.get(1), "-");
                jogo.erro();
                e.setMsg("Errou ("+this.coordenadas+")");
                e.setTp("erro");
            } else {
                String atingido = this.linhas.get(coordenadas.get(0)).get(coordenadas.get(1));
                this.linhas.get(coordenadas.get(0)).set(coordenadas.get(1), "O");
                e.setMsg("Acertou ("+this.coordenadas+")");
                e.setTp("acerto");
                if (navioAtingido(atingido, coordenadas.get(0))) {
                    jogo.acertouNavio();
                } else {
                    jogo.acerto();
                }
            }
        }
        return e;
    }

    /**
     * Analisa as linhas e verifica se existem valores diferentes de ".", "O" ou "-" para definir fim de jogo
     * @return verdadeiro se o jogo chegou ao fim.
     */
    public boolean fim() {
        for (int i = 0; i < linhas.size(); i++) {
            for (int j = 0; j < linhas.get(i).size(); j++) {
                if (!linhas.get(i).get(j).equals("O") && !linhas.get(i).get(j).equals("-") && !linhas.get(i).get(j).equals(".")) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Coordenadas do jogador, trata e executa a jogada nas posicoes informadas.
     * @param coordenadaUsuario
     * @param jogo
     * @return 
     */
    public Mensagens joga(String coordenadaUsuario, Jogo jogo) {

        ArrayList<Integer> coordenadas = this.getCoordenadas(coordenadaUsuario);

        if (coordenadas == null) {
            Mensagens e = new Mensagens();
            e.setMsg("Coordenadas ("+this.coordenadas+") inválida");
            e.setTp("erro");
            return e;
        } else {
            try {
                this.linhas.get(coordenadas.get(0));
            } catch (IndexOutOfBoundsException ex) {
                Mensagens e = new Mensagens();
                e.setMsg("Coluna ("+this.coordenadas+") não existe");
                e.setTp("erro");
                return e;
            }

            try {
                this.linhas.get(coordenadas.get(0)).get(coordenadas.get(1));
            } catch (IndexOutOfBoundsException ex) {
                Mensagens e = new Mensagens();
                e.setMsg("A coordenada ("+this.coordenadas+") não existe");
                e.setTp("erro");
                return e;
            }

            return realizaJogada(coordenadas, jogo);
        }
    }

}
