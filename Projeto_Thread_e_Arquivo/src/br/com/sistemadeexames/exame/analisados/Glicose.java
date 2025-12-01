package br.com.sistemadeexames.exame.analisados;

import br.com.sistemadeexames.exame.Exame;
import br.com.sistemadeexames.interfaces.Analisavel;

// A classe 'Glicose' também HERDA de 'Exame', que representa um exame de medição de glicose no sangue.
public class Glicose extends Exame implements Analisavel {

    // Construtor define os limites normais para glicose. mínimo: 70 mg/dL e máximo: 99 mg/dL
    public Glicose(double valor) {
        super("Glicose", valor, 70.0, 99.0);
    }

    // Metodo que verifica se o valor da glicose está fora da faixa normal.
    @Override
    public boolean estaForaDaFaixa() {
        return valor < limiteMin || valor > limiteMax;
    }

}
