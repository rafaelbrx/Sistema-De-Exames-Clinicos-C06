package br.com.sistemadeexames.exame;

import br.com.sistemadeexames.excecoes.ExceptionValorForaDaFaixa;
import br.com.sistemadeexames.interfaces.Analisavel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Exame implements Analisavel {

    // ATRIBUTOS COMUNS A TODOS OS EXAMES
    // Nome do exame
    protected final String nomeExame;
    // Valor medido no exame
    protected double valor;
    // Limite mínimo considerado normal para o exame.
    protected final double limiteMin;
    // Limite máximo considerado normal para o exame.
    protected final double limiteMax;
    // Data em que o exame foi realizado.
    protected LocalDateTime data;

    // O construtor inicializa todos os atributos da classe.
    public Exame(String nomeExame, double valor, double limiteMin, double limiteMax) {
        // Validação do limite do exame
        if (limiteMin > limiteMax) {
            throw new IllegalArgumentException("O limite mínimo não pode ser maior que o máximo.");
        }

        this.nomeExame = nomeExame;
        this.valor = valor;
        this.limiteMin = limiteMin;
        this.limiteMax = limiteMax;
    }


    // ====== METODO ABSTRATO ======
    public abstract boolean estaForaDaFaixa();

    //GETTERS
    // Retorna o nome do exame.
    public String getNomeExame() {
        return nomeExame;
    }

    // Retorna o valor obtido no exame.
    public double getValor() {
        return valor;
    }

    // Retorna a data do exame !!! ADICIONADO DEVIDO A NECESSIDADE DA CLASSE "Arquivo" !!!
    public LocalDateTime getData() { return data; }
    //____________________________________________________________________________________

    //SETTER para definir a data

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getFaixaNormal(){
        return limiteMin + " a " + limiteMax;
    }

    //METODO OBRIGATÓRIO DA INTERFACE
    @Override
    public void executarAnalise() throws ExceptionValorForaDaFaixa {

        System.out.println("Analisando exame: " + nomeExame);
        System.out.println("Valor: " + valor + " (Faixa normal: " + getFaixaNormal() + ")");

        if (estaForaDaFaixa()) {
            System.out.println("Situação: Alterado");
            throw new ExceptionValorForaDaFaixa("O valor do exame " + nomeExame + " está fora da faixa!");
        }

        System.out.println("Situação: Normal");
    }

}