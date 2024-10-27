package ufpb.com.br;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class AgendaTest {

    private AgendaInterface agenda;
    private final String nomeArquivoTxt = "contatos.txt";

    @BeforeEach
    void setUp() {
        agenda = new Agenda(nomeArquivoTxt);
    }

    @Test
    void testAdicionarContato() {
        Contato contato = new Contato("Pessoa 01", 15, 6);
        agenda.adicionarContato(contato);
        assertEquals(1, agenda.getContatos().size(), "Deveria haver 1 contato na agenda");
    }

    @Test
    void testRemoverContato() throws ContatoInexistenteException {
        Contato contato = new Contato("Pessoa 01", 15, 6);
        agenda.adicionarContato(contato);
        agenda.removerContato("Pessoa 01");
        assertEquals(0, agenda.getContatos().size(), "Deveria haver 0 contatos na agenda após remoção");
    }

    @Test
    void testSalvarERecuperarDados() throws IOException {
        // Adiciona um contato e salva
        agenda.adicionarContato(new Contato("Pessoa 01", 15, 6));
        agenda.salvarDados();

        // Cria uma nova agenda para recuperar dados
        Agenda novaAgenda = new Agenda(nomeArquivoTxt);
        novaAgenda.recuperarDados();

        // Verifica se o contato foi recuperado corretamente
        assertEquals(1, novaAgenda.getContatos().size(), "Deveria recuperar 1 contato");
        assertNotNull(novaAgenda.getContatos().get("Pessoa 01"), "Contato 'Pessoa 01' deve existir na nova agenda");
    }

    @Test
    void testRemoverContatoInexistente() {
        Contato contato = new Contato("Pessoa 01", 15, 6);
        agenda.adicionarContato(contato);

        assertThrows(ContatoInexistenteException.class, () -> {
            agenda.removerContato("Pessoa 02");
        }, "Deveria lançar exceção ao remover um contato inexistente");
    }

    @Test
    void testPesquisaAniversariantes() {
        agenda.adicionarContato(new Contato("Pessoa 01", 15, 6));
        agenda.adicionarContato(new Contato("Pessoa 02", 15, 6));
        agenda.adicionarContato(new Contato("Pessoa 03", 20, 7));

        var aniversariantes = agenda.pesquisaAniversariantes(15, 6);
        assertEquals(2, aniversariantes.size(), "Deveria encontrar 2 aniversariantes no dia 15 de junho");
    }

    @Test
    void testSalvarDadosEmArquivo() throws IOException {
        agenda.adicionarContato(new Contato("Pessoa 01", 15, 6));
        agenda.salvarDados();

        Path path = Path.of(nomeArquivoTxt);
        assertTrue(Files.exists(path), "O arquivo de contatos deve existir após a gravação");
        Files.deleteIfExists(path);
    }
}
