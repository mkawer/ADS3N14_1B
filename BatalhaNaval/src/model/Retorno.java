package model;

/**
 * Classe que contem o tipo e mensagem de retorno que sera visualizado pelo usuario
 * @author Lucas Pacheco Oliveira
 */
public class Retorno {

    private String tipo;
    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
