package ufpb.com.br;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Agenda implements AgendaInterface {

    private Map<String, Contato> contatos;
    private GravadorDeDados gravador;

    public Agenda(String nomeArquivoTxt) {
        this.contatos = new HashMap<>();
        this.gravador = new GravadorDeDados(contatos, nomeArquivoTxt);
    }

    @Override
    public void adicionarContato(Contato contato) {
        if (contatos.containsKey(contato.getNome())) {
            System.out.println("Contato já existe com o nome: " + contato.getNome());
        } else {
            contatos.put(contato.getNome(), contato);
            System.out.println("Contato adicionado: " + contato.getNome());
        }
    }

    @Override
    public void removerContato(String nome) throws ContatoInexistenteException {
        if (contatos.containsKey(nome)) {
            contatos.remove(nome);
            System.out.println("Contato removido: " + nome);
        } else {
            throw new ContatoInexistenteException("Contato não encontrado: " + nome);
        }
    }

    @Override
    public Collection<Contato> pesquisaAniversariantes(int dia, int mes) {
        Collection<Contato> resultado = new ArrayList<>();
        for (Contato contato : contatos.values()) {
            if (contato.getDiaAniversario() == dia && contato.getMesAniversario() == mes) {
                resultado.add(contato);
            }
        }
        return resultado;
    }

    @Override
    public void salvarDados() throws IOException {
        gravador.salvarDados();
    }

    @Override
    public void recuperarDados() throws IOException {
        gravador.recuperarDados();
    }

    @Override
    public Map<String, Contato> getContatos() {
        return contatos;
    }
}
