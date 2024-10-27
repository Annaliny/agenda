package ufpb.com.br;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public interface AgendaInterface {

    void adicionarContato(Contato contato);
    void removerContato(String nome) throws ContatoInexistenteException;
    Collection<Contato> pesquisaAniversariantes(int dia, int mes);
    void salvarDados() throws IOException;
    void recuperarDados() throws IOException;
    Map<String, Contato> getContatos();
}
