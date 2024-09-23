package ufpb.com.br;

import java.util.Objects;

import java.io.Serializable;

public class Contato implements Serializable {

    private static final long serialVersionUID = 1L; // Versão de serialização

    private String nome;
    private int diaAniversario;
    private int mesAniversario;

    public Contato(String nome, int diaAniversario, int mesAniversario) {
        this.nome = nome;
        this.diaAniversario = diaAniversario;
        this.mesAniversario = mesAniversario;
    }

    public Contato() {}

    public String getNome() {
        return nome;
    }

    public int getDiaAniversario() {
        return diaAniversario;
    }

    public int getMesAniversario() {
        return mesAniversario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDiaAniversario(int diaAniversario) {
        this.diaAniversario = diaAniversario;
    }

    public void setMesAniversario(int mesAniversario) {
        this.mesAniversario = mesAniversario;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "nome='" + nome + '\'' +
                ", diaAniversario=" + diaAniversario +
                ", mesAniversario=" + mesAniversario +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return diaAniversario == contato.diaAniversario &&
                mesAniversario == contato.mesAniversario &&
                Objects.equals(nome, contato.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, diaAniversario, mesAniversario);
    }
}