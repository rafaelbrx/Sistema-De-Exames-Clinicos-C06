package br.com.sistemadeexames.excecoes;

public class ExceptionCpfInvalido extends RuntimeException {
    public ExceptionCpfInvalido(String message) {
        super(message);
    }
}
