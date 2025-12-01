package br.com.sistemadeexames.exame.analisados;

import br.com.sistemadeexames.exame.Exame;
import br.com.sistemadeexames.interfaces.Analisavel;

// A classe 'Hemograma' HERDA (extends) de 'Exame', ela representa um tipo específico de exame de sangue.
public class Hemograma extends Exame implements Analisavel {


    public Hemograma(double valor) {
        super("Hemograma", valor, 4.0, 10.0);
    }


    // Implementação do metodo abstrato herdado da classe Exame, aqui é definido o critério para determinar se o valor está fora da faixa normal.
    @Override
    public boolean estaForaDaFaixa() {
        return valor < limiteMin || valor > limiteMax;
    }

}
