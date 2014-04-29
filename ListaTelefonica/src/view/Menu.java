package view;

import java.util.Scanner;
import model.Contact;
import model.Lista;

/**
 * @author Marcelo Rebelo
 */
public class Menu {

    private static Lista lista;
    
    /**
     * Exibe o menu principal. Retorna a opcao do usuario   
     */
    private static int exibeMenu() {
        int opcao = -1;
        Scanner menu = new Scanner(System.in);
        try {
            System.out.println("Digite a opcao desejada: ");
            System.out.print(" * 2 - Listar contatos");
            System.out.print(" * 3 - Adicionar contato");
            System.out.print(" * 4 - Procurar contatos");
            System.out.print(" * 5 - Procura Binaria");
            System.out.println(" * 6 - Excluir contato");
            System.out.println(" **** 0 - Sair **** ");
            opcao = menu.nextInt();
        } catch (Exception e) {
            opcao = 1;
        }
        return opcao;

    }

    /**
     * Cria um novo contato
     * @return true se a insercao foi bem sucedida
     */
    public static boolean novoContato() {

        try {
            Scanner iUsuario = new Scanner(System.in);
            System.out.println("Informe os dados ");
            System.out.println("Nome: ");
            String nome = iUsuario.nextLine();
            System.out.println("DDD: ");
            int ddd = iUsuario.nextInt();
            System.out.println("Telefone: ");
            //Trata a entrada do usuario, removendo dados nao numericos.
            int telefone = Integer.valueOf(iUsuario.next().replaceAll("[^0-9]", ""));
            Contact contato = new Contact(lista.getNextId(), nome, ddd, telefone);
            contato.escreveContato();
            lista = new Lista();
            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {

        int opcao = -1;
        lista = new Lista();

        while (opcao == -1) {
            opcao = exibeMenu();

            switch (opcao) {
                case 2:
                    listar();
                    opcao = -1;
                    break;
                case 3:
                    System.out.println("Adicionar contato");
                    novoContato();
                    opcao = -1;
                    break;
                case 4:
                    System.out.println("Procurar na lista");
                    procuraLista();
                    opcao = -1;
                    break;
                case 5:
                    System.out.println("Busca binaria");
                    buscaBinaria();
                    opcao = -1;
                    break;
                case 6:
                    System.out.println("Exluir contato");
                    removeContato();
                    opcao = -1;
                    break;
                case 0:
                    System.out.println("Finalizando!");
                    System.exit(0);
                default:
                    System.out.println("Opcao invalida!");
                    opcao = -1;
            }
        }

    }

    /**
     * Metodo que solicita Id do contato que deseja remover. Exibe mensagens com o status da solicitacao.
     */
    public static void removeContato() {
        Scanner entradaUsuario = new Scanner(System.in);
        System.out.println("Digite o id do contato que deseja remover, ou 0 para sair: ");
        String opcao = entradaUsuario.nextLine();

        while (!opcao.equals("sair")) {
            try {
                int id = Integer.valueOf(opcao);
                if(lista.removeContatoById(id)){
                    lista = new Lista();
                    System.out.println("Contato ["+id+"] removido!");
                } else {
                    System.out.println("Contato ["+id+"] inexistente.");
                }
                opcao = "sair";
            } catch (Exception e) {
                System.out.println("Digite um valor valido.");
            }
        }
    }

    /**
     * Metodo que solicita ao usuario uma letra para que a consulta seja realizada.
     * Se o usuario digita o numero 99 volta ao menu anterior
     */
    public static void buscaBinaria() {
        Scanner ent = new Scanner(System.in);
        String opcao = ent.nextLine();
        if(opcao!="sair") {
            if(lista.buscaBinaria(opcao)>0){
                anteriorProximo();
            }
        }
    }
    
    /**
     * Solicita os dados do usuario para aplicar o filtro na lista de contatos.
     */
    public static void procuraLista() {

        System.out.println("Digite a primeira letra a exibir ou digite (0) para sair: ");
        String opcao = "";
        while (opcao != "0") {
            Scanner entradaUsuario = new Scanner(System.in);
            opcao = entradaUsuario.nextLine();
            if (opcao == "0") {
                break;
            } else {
                if (!opcao.equals("")) {
                    if (lista.filtroContato(opcao)) {
                        anteriorProximo();
                    }
                    opcao = "0";
                } else {
                    System.out.println("E necessario informar pelo menos uma letra para iniciar a pesquisa.");
                }
            }
        }
    }

    /**
     * Metodo que exibe proximo/anterior. Retorna ao menu principal se o usuario informar 0.
     */
    public static void anteriorProximo() {
        Integer opcao = -1;
        while (opcao == -1) {
            try {
                Scanner entradaUsuario = new Scanner(System.in);
                System.out.println("Opcoes: (1) Registro anterior | (2) Proximo registro. | (0) Sair.");
                opcao = entradaUsuario.nextInt();
                if (opcao == 1 || opcao == 2) {
                    if (opcao == 1) {
                        lista.listaAnterior();
                    } else {
                        lista.listaProximo();
                    }
                    opcao = 0;
                    anteriorProximo();
                }
            } catch (Exception e) {
                System.out.println("Opcao Invalida!");
                opcao = -1;
            }

        }
    }

    /**
     * Metodo que lista os contatos contidos na ListaOrdenada.
     */
    public static void listar() {
        System.out.println("Listagem: ");
        lista.exibeListaOrdenada();
    }
}
