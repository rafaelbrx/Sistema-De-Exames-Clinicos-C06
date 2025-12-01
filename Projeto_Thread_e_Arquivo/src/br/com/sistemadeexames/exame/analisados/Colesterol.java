package br.com.sistemadeexames.exame.analisados;

import br.com.sistemadeexames.exame.Exame;
import br.com.sistemadeexames.interfaces.Analisavel;

// A classe 'Colesterol' herda de 'Exame', que representa um exame de colesterol total no sangue.
public class Colesterol extends Exame implements Analisavel {

    // Construtor que define limites de referência para o colesterol total. mínimo: 125 mg/dL e máximo: 200 mg/dL
    public Colesterol(double valor) {
        super("Colesterol", valor, 125.0, 200.0);
    }

    // Metodo que verifica se o colesterol está fora da faixa normal.
    @Override
    public boolean estaForaDaFaixa() {
        return valor < limiteMin || valor > limiteMax;
    }
}