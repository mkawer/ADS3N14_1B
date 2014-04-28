package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsavel pela manipulacao de arquivo.
 * @author Marcelo Rebelo
 */
public class Arquivo {

    private File file;
    private FileReader fr;
    private BufferedReader br;

    /**
     * Classe construtora, abre o arquivo e inicialia as variaveis.
     * @throws FileNotFoundException 
     */
    public Arquivo() throws FileNotFoundException {

        this.file = new File("contatos.txt");
        this.fr = new FileReader(this.file);
        this.br = new BufferedReader(this.fr);

        if (!this.file.exists()) {
            try {
                if(!this.file.createNewFile()){
                    System.out.println("Erro ao tentar abrir o arquivo.");
                }
            } catch (IOException ex) {
                System.out.println("Erro ao tentar abrir o arquivo.");
            }
        }

    }

    /**
     * Metodo que escreve uma linha em um arquivo.
     * @param linha
     * @throws IOException 
     */
    public void escreveLinha(String linha) {
        FileWriter fr;
        try {
            fr = new FileWriter(this.file, true);
            BufferedWriter bw = new BufferedWriter(fr);
            bw.append(linha);
            bw.newLine();
            bw.close();            
        } catch (IOException ex) {
            Logger.getLogger(Arquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Trunca o arquivo para o tamanho "0"
     */
    public void limpaArquivo() {
        FileWriter fr;
        try {
            fr = new FileWriter(this.file);
            BufferedWriter bw = new BufferedWriter(fr);
            bw.close();
        } catch (IOException ex) {
            System.out.println("Erro ao tentar limpar o arquivo.");
        }
    }

    /**
     * Le a proxima linha do arquivo, retorna null se for o final do arquivo (EOF)
     * @return 
     */
    public String leLinhaArquivo() {
        String linha = null;
        try {
            linha = this.br.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return linha;
    }

    /**
     * Fecha o arquivo, aberto anteriormente.
     */
    public void fechaArquivo() {
        if (this.br != null) {
            try {
                this.br.close();
            } catch (IOException ex) {
                System.out.println("Erro ao tentar fechar o arquivo.");
            }
        }
    }
}
