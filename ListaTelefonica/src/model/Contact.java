package model;

import control.Arquivo;
import java.io.IOException;

/**
 * Classe responsavel pelo Objeto contato, que contem dados como id, nome, ddd e telefone
 * @author Lucas
 */
public class Contact implements Comparable<Contact> {

    private int id;
    private String nome;
    private int ddd;
    private int telefone;

    public Contact() {
    }

    /**
     * Metodo que cria o contato, recebe uma string que espera o padrao id;nome;ddd;telefone;
     * Usa metodologia de posicionamento de String
     * @param strLinha 
     */
    public Contact(String strLinha) {
        int pPos = 0;
        int nPos = strLinha.indexOf(";", pPos);
        this.id = Integer.valueOf(strLinha.substring(pPos, nPos));
        pPos = nPos + 1;
        nPos = strLinha.indexOf(";", pPos + 1);
        this.nome = strLinha.substring(pPos, nPos);
        pPos = nPos + 1;
        nPos = strLinha.indexOf(";", pPos + 1);
        this.ddd = Integer.valueOf(strLinha.substring(pPos, nPos));
        pPos = nPos + 1;
        nPos = strLinha.indexOf(";", pPos + 1);
        this.telefone = Integer.valueOf(strLinha.substring(pPos, nPos));
    }

    /**
     * Metodo de sobrescrita que gera um contato
     * @param id
     * @param nome
     * @param ddd
     * @param telefone 
     */
    public Contact(int id, String nome, int ddd, int telefone) {
        this.id = id;
        this.nome = nome;
        this.ddd = ddd;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo responsavel por instanciar e escrever um novo contato no arquivo contato.txt
     * @return 
     */
    public boolean escreveContato() {

        try {
            Arquivo arquivo = new Arquivo();
            StringBuilder sb = new StringBuilder();
            sb.append(this.id).append(";").append(this.nome).append(";").append(this.ddd).append(";").append(this.telefone).append(";");
            arquivo.escreveLinha(sb.toString());
            return true;
        } catch (IOException ex) {
            System.out.println("Erro ao escrever no arquivo.");
            return false;
        }

    }

    @Override
    public int compareTo(Contact o) {
        return this.getNome().toString().compareTo(o.getNome().toString());
    }

}
