package model;

import control.Arquivo;
import structure.LinkedList;
import structure.SortedList;
import structure.Node;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas
 * Classe responsavel pela manipulacao da lista de contatos, ordenada ou encadeada.
 */
public class Lista {

    private SortedList<Contact> sortList;
    private Node<Contact> currentRecord;
    private int sequence;

    /**
     * Metodo contrutor que inicia uma lista ordenada.
     */
    public Lista() {

        Arquivo a;
        sequence = 0;
        this.sortList = new SortedList<Contact>();

        try {
            a = new Arquivo();
            String linha = "";
            while ((linha = a.leLinhaArquivo()) != null) {
                Contact contact = new Contact(linha);
                Node<Contact> node = new Node<Contact>(contact);
                sequence = contact.getId() > sequence ? contact.getId() : sequence;
                this.sortList.append(node);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro ao tentar criar a lista. ");
            ex.printStackTrace();
        }

    }

    public int getNextId() {
        return sequence + 1;
    }

    /**
     * Filtra o contato, recebe um fragmento, e efetua um loop, verificando se o item
     * contem o fragmento informado pelo usuario
     */
    public boolean filtroContato(String primeiraLetra) {
        Node<Contact> nodo = this.sortList.getHead();
        while (nodo != null) {
            if (nodo.getData().getNome().startsWith(primeiraLetra)) {
                this.currentRecord = nodo;
                break;
            }
            nodo = nodo.getNext();
        }

        if (this.currentRecord == null) {
            System.out.println("Nenhum registro encontrado para a letra [" + primeiraLetra + "].");
            return false;
        } else {
            escreveRegistro(this.currentRecord);
            return true;
        }

    }
    
    
    /**
     * Recebe um id do usuario, recria a lista em uma lista encadeana, na ordem natural que foi gravada
     * menos o id informado pelo usuario.
     * Se o id existe na lista, reescreve o arquivo com base nesta lista.
     * @param id
     * @return verdadeiro se o registro foi encontrado e removido.
     */
    public boolean removeContatoById(int id) {

        LinkedList<Contact> lista = new LinkedList<Contact>();
        Arquivo a;
        String linha = "";
        boolean apagar = false;

        try {
            a = new Arquivo();
            while ((linha = a.leLinhaArquivo()) != null) {
                Contact contato = new Contact(linha);
                Node<Contact> nodo = new Node<Contact>(contato);
                if (nodo.getData().getId() != id) {
                    lista.append(nodo);
                } else {
                    apagar = true;
                }
            }
            
            if(apagar) {
                a.limpaArquivo();
                Node<Contact> nodo = lista.getHead();            
                while(nodo!=null) {
                    a.escreveLinha(new StringBuilder().append(nodo.getData().getId()).append(";").append(nodo.getData().getNome()).append(";").append(nodo.getData().getDdd()).append(";").append(nodo.getData().getTelefone()).append(";").toString());
                    nodo = nodo.getNext();
                }
                return true;
            } else {
                return false;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro ao tentar ler o arquivo.");
            return false;
        }
    }
    
    /**
     * Metodo que seta o registro atual para o proximo registro, se o registro atual e o ultimo
     * seta o primeiro registro, formando um loop
     */
    public void listaProximo() {
        if (this.currentRecord == null) {
            this.currentRecord = this.sortList.getHead();
        } else {
            this.currentRecord = this.currentRecord.getNext();
            if (this.currentRecord == null) {
                this.currentRecord = this.sortList.getHead();
            }
        }
        escreveRegistro(this.currentRecord);
    }

    /**
     * Metodo que seta o registro atual para o proximo registro, se o registro atual e o primeiro
     * seta o ultimo registro, realizando um loop
     */
    public void listaAnterior() {
        Node<Contact> nodo = this.sortList.getHead();
        Node<Contact> anterior = nodo;
        while (nodo != null) {
            nodo = nodo.getNext();
            if (this.currentRecord == anterior) {
                this.currentRecord = this.sortList.getTail();
                break;
            } else {
                if (this.currentRecord == nodo) {
                    this.currentRecord = anterior;
                    break;
                }
            }
            anterior = nodo;
        }
        escreveRegistro(this.currentRecord);
    }

    /**
     * Mostra na tela para o usuário uma lista ordenada.
     */
    public void exibeListaOrdenada() {
        Node<Contact> nodo = this.sortList.getHead();
        while (nodo != null) {
            escreveRegistro(nodo);
            nodo = nodo.getNext();
        }
    }

    /**
     * Realiza uma busca utilizando a metodologia de pesquisa binaria.
     * Os nodos são inseridos em um Array, este array e dividido ao meio
     * Se o valor anterior for maior que o fragmento pesquisado busca da metade ate o final da lista
     * senao busca da metade ate o inicio da lista.
     * @param fragmento
     * @return retorna o numero de comparacoes realizadas até encontrar o fragmento.
     */
    public int buscaBinaria (String fragmento) {
        Node<Contact> nodo = this.sortList.getHead();
        List<Contact> lista = new ArrayList<Contact>();
        Contact encontrou = null;
        int comparacoes = 0;
        
        while (nodo != null) {
            lista.add(nodo.getData());
            nodo = nodo.getNext();
        }
        
        if(lista.size()>0){
            
            int metade = Math.round((float) lista.size()/2);
            
            if(lista.get(metade).getNome().substring(0, 1).compareToIgnoreCase(fragmento)==0){
                /* Entao o elemento e igual */
                comparacoes++;
                currentRecord = new Node<>(lista.get(metade));
                encontrou = lista.get(metade);
            } else if(lista.get(metade).getNome().substring(0, 1).compareToIgnoreCase(fragmento)>0) {
                /* Entao o elemento e maior */
                for(int i=metade;i>=0;i--) {
                    comparacoes++;
                    if(lista.get(i).getNome().substring(0, 1).compareToIgnoreCase(fragmento)==0) {
                        currentRecord = new Node<>(lista.get(i));
                        encontrou = lista.get(i);
                    }
                }
            } else {
                /* Entao o elemento e menor. */
                for(int i=metade;i<lista.size();i++) {
                    comparacoes++;
                    if(lista.get(i).getNome().substring(0, 1).compareToIgnoreCase(fragmento)==0) {
                        currentRecord = new Node<>(lista.get(i));
                        encontrou = lista.get(i);
                    }                    
                }
            }
            
            if(encontrou!=null) {
                System.out.println("Registro encontrado ("+String.valueOf(comparacoes)+" comparacoes): ");
                escreveRegistro(new Node<>(encontrou));
            } else {
                System.out.println("Nenhum registro encontrado.");
            }
            
        }
        return comparacoes;
        
    }
    
    /**
     * Metodo que escreve na o contato.
     * @param nodo 
     */
    public void escreveRegistro(Node<Contact> nodo) {
        System.out.print("[ Id: ");
        System.out.print(nodo.getData().getId());
        System.out.print(" ] Nome: ");
        System.out.print(nodo.getData().getNome());
        System.out.print(" - Telefone: (");
        System.out.print(nodo.getData().getDdd());
        System.out.print(")");
        System.out.print(nodo.getData().getTelefone());
        System.out.println("");
    }

}
