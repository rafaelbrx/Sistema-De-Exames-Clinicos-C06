package br.com.sistemadeexames.interfaces;

import br.com.sistemadeexames.excecoes.ExceptionValorForaDaFaixa;

//classe Analisavel feita para a interface
public interface Analisavel {

    // Metodo abstrato (sem corpo), que deve ser implementado em todas as classes
    void executarAnalise() throws ExceptionValorForaDaFaixa;
}
