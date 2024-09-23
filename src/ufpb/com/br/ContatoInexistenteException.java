package ufpb.com.br;

public class ContatoInexistenteException extends Exception {

    public ContatoInexistenteException() {
        super("Contato não encontrado ou não existe");
    }

    public ContatoInexistenteException(String message) {
        super(message);
    }
}

