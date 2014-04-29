package model;

import java.util.ArrayList;
import static java.util.Arrays.asList;

/**
 * Classe dos objetos de jogo
 * @author Marcelo Rebelo
 */
public class Navios {

    private ArrayList<String> portaAvioes;
    private ArrayList<String> destroyer;
    private ArrayList<String> fragata;
    private ArrayList<String> torpedeiro;
    private ArrayList<String> submarino;

    /**
     * Inicializa os objetos.
     */
    public Navios() {
        this.portaAvioes = new ArrayList<>(asList("P","P","P","P","P"));
        this.destroyer = new ArrayList<>(asList("D","D","D","D"));
        this.fragata = new ArrayList<>(asList("F","F","F"));
        this.torpedeiro = new ArrayList<>(asList("T","T"));
        this.submarino = new ArrayList<>(asList("S"));
    }

    public ArrayList<String> getPortaAvioes() {
        return portaAvioes;
    }

    public void setPortaAvioes(ArrayList<String> portaAvioes) {
        this.portaAvioes = portaAvioes;
    }

    public ArrayList<String> getDestroyer() {
        return destroyer;
    }

    public void setDestroyer(ArrayList<String> destroyer) {
        this.destroyer = destroyer;
    }

    public ArrayList<String> getFragata() {
        return fragata;
    }

    public void setFragata(ArrayList<String> fragata) {
        this.fragata = fragata;
    }

    public ArrayList<String> getTorpedeiro() {
        return torpedeiro;
    }

    public void setTorpedeiro(ArrayList<String> torpedeiro) {
        this.torpedeiro = torpedeiro;
    }

    public ArrayList<String> getSubmarino() {
        return submarino;
    }

    public void setSubmarino(ArrayList<String> submarino) {
        this.submarino = submarino;
    }

}