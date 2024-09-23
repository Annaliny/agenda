package ufpb.com.br;

import java.io.*;
import java.util.Map;

public class GravadorDeDados {

    private Map<String, Contato> contatos;
    private String nomeArquivoTxt;

    public GravadorDeDados(Map<String, Contato> contatos, String nomeArquivoTxt) {
        this.contatos = contatos;
        this.nomeArquivoTxt = nomeArquivoTxt;
    }

    public void salvarDados() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivoTxt))) {
            for (Contato contato : contatos.values()) {
                writer.write(contato.getNome() + ";" + contato.getDiaAniversario() + ";" + contato.getMesAniversario());
                writer.newLine();
            }
            System.out.println("Dados salvos com sucesso no arquivo TXT: " + nomeArquivoTxt);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
            throw e;
        }
    }

    public void recuperarDados() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivoTxt))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 3) {
                    String nome = partes[0];
                    int dia = Integer.parseInt(partes[1]);
                    int mes = Integer.parseInt(partes[2]);
                    Contato contato = new Contato(nome, dia, mes);
                    contatos.put(nome, contato);
                }
            }
            System.out.println("Dados recuperados com sucesso do arquivo TXT: " + nomeArquivoTxt);
        } catch (IOException e) {
            System.err.println("Erro ao recuperar os dados: " + e.getMessage());
            throw e;
        }
    }

    public Map<String, Contato> getContatos() {
        return contatos;
    }
}
