package ufpb.com.br;

import java.io.IOException;
import java.util.Collection;

public interface AgendaInterface {

    public boolean cadastraContato(String nome, int dia, int mes);

    public Collection<Contato> pesquisaAniversariantes(int dia, int mes);

    public boolean removeContato(String nome) throws ContatoInexistenteException;

    public void salvarDados() throws IOException;

    public void recuperarDados() throws IOException;
}
