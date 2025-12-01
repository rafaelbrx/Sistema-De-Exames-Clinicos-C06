package br.com.sistemadeexames.thread;

import br.com.sistemadeexames.exame.Exame;
import br.com.sistemadeexames.excecoes.ExceptionValorForaDaFaixa;
import br.com.sistemadeexames.paciente.Paciente;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ThreadExame extends Thread {

    private final Paciente paciente;
    private final Exame exame;
    private final int duracaoSegundos;

    public ThreadExame(Paciente paciente, Exame exame, int duracaoSegundos) {
        this.paciente = paciente;
        this.exame = exame;
        this.duracaoSegundos = duracaoSegundos;
    }

    @Override
    public void run() {
        try {
            System.out.println("[INÍCIO] Exame iniciado - " +
                    "Paciente: " + paciente.getNome() +
                    " | Exame: " + exame.getNomeExame() +
                    " | Valor: " + exame.getValor() + "\n"
            );

            // Simula o processamento do exame
            Thread.sleep(duracaoSegundos * 1000L);

            try {
                exame.executarAnalise();
            } catch (ExceptionValorForaDaFaixa e) {
                System.out.println("⚠ Resultado ANORMAL: " + e.getMessage());
            }

            paciente.adicionarExame(exame);

            // mensagem caso tenha dado certo
            System.out.println("\n[CONCLUÍDO] Exame finalizado - " +
            "Paciente: " + paciente.getNome() +
            " | Exame: " + exame.getNomeExame() +
            " | Data concluída: " + exame.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +"\n");


        } catch (InterruptedException e) {
            System.err.println("A execução da thread foi interrompida: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}