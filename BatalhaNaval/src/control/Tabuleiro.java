package control;

import model.Navios;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.Random;
import model.Retorno;
import model.Jogo;
import utils.Funcoes;

/**
 * Classe responsavel por manipular o tabuleiro do jogo
 * @author Lucas Pacheco Oliveira
 */
public class Tabuleiro {

    private ArrayList<ArrayList<String>> linhas;
    private String coordenada;

    /**
     * Monta um tabuleiro, com 10 linha e 10 colunas. Preenche todas as posicoes com "."
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
    public void inicia() {
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
            ArrayList<String> lPecas = Funcoes.listaAlfabeto();
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
     * Verifica e aloca na matriz um objeto. Retorna falso caso não seja possivel alocar, ou se houver sobreposicao de objetos.
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
     * Recebe o valor do usuario e o transforma em coordenadas
     * @param coordenada
     * @return 
     */
    private ArrayList<Integer> getCoordenadas(String coordenada) {
        try {
            this.coordenada = coordenada;
            Integer pos1 = Integer.valueOf(coordenada.substring(1, 2));
            Integer pos2 = Funcoes.getPosicaoAlfabeto(coordenada.substring(0, 1));
            ArrayList<Integer> retorno = new ArrayList<>(asList(pos1, pos2));
            return retorno;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Verifica se existem objetos da mesma peca na linha da ultima jogada. Retorna verdadeiro se não houver mais regitros da peca na linha
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
     * Executa e marca a jogada. Se a posicao conter um objeto ou "."
     * @param coordenadas
     * @param jogo
     * @return 
     */
    public Retorno realizaJogada(ArrayList<Integer> coordenadas, Jogo jogo) {
        Retorno e = new Retorno();
        if (!this.linhas.get(coordenadas.get(0)).get(coordenadas.get(1)).equals("O") && !this.linhas.get(coordenadas.get(0)).get(coordenadas.get(1)).equals("-")) {
            if (this.linhas.get(coordenadas.get(0)).get(coordenadas.get(1)).equals(".")) {
                this.linhas.get(coordenadas.get(0)).set(coordenadas.get(1), "-");
                jogo.errou();
                e.setMensagem("Você errou ("+this.coordenada+")");
                e.setTipo("erro");
            } else {
                String atingido = this.linhas.get(coordenadas.get(0)).get(coordenadas.get(1));
                this.linhas.get(coordenadas.get(0)).set(coordenadas.get(1), "O");
                e.setMensagem("Você acertou ("+this.coordenada+")");
                e.setTipo("acerto");
                if (navioAtingido(atingido, coordenadas.get(0))) {
                    jogo.acertouNavio();
                } else {
                    jogo.acertou();
                }
            }
        }
        return e;
    }

    /**
     * Analisa todas as linhas verificando se existem valores diferentes de ".", "O" ou "-"
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
     * Resgata as coordenadas do usuario, trata e executa a jogada nas posicoes informadas pelo usuario.
     * @param coordenadaUsuario
     * @param jogo
     * @return 
     */
    public Retorno joga(String coordenadaUsuario, Jogo jogo) {

        ArrayList<Integer> coordenadas = this.getCoordenadas(coordenadaUsuario);

        if (coordenadas == null) {
            Retorno e = new Retorno();
            e.setMensagem("Coordenadas inválidas ("+this.coordenada+")");
            e.setTipo("erro");
            return e;
        } else {
            try {
                this.linhas.get(coordenadas.get(0));
            } catch (IndexOutOfBoundsException ex) {
                Retorno e = new Retorno();
                e.setMensagem("A coluna que você informou não existe ("+this.coordenada+")");
                e.setTipo("erro");
                return e;
            }

            try {
                this.linhas.get(coordenadas.get(0)).get(coordenadas.get(1));
            } catch (IndexOutOfBoundsException ex) {
                Retorno e = new Retorno();
                e.setMensagem("A linha que você informou não existe ("+this.coordenada+")");
                e.setTipo("erro");
                return e;
            }

            return realizaJogada(coordenadas, jogo);
        }
    }

}
