package br.com.sistemadeexames.excecoes;

//Essa classe é uma exceção
public class ExceptionValorForaDaFaixa extends Exception {

    // Construtor que recebe uma mensagem e repassa para o construtor da superclasse (Exception).
    public ExceptionValorForaDaFaixa(String mensagem) {
        super(mensagem);
    }
}
