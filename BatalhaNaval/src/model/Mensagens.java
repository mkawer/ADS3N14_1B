package model;

/**
 * Classe que contem o tipo e mensagem de retorno que sera visualizado pelo usuario
 * @author Marcelo Rebelo
 */
public class Mensagens {

    private String tp;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String mensagem) {
        this.msg = mensagem;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tipo) {
        this.tp = tipo;
    }

}
