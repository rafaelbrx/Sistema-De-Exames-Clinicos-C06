package br.com.sistemadeexames.paciente;

import br.com.sistemadeexames.exame.Exame;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Classe Paciente armazena os dados do paciente e os exames associados a ele
public class Paciente {
    private String nome;
    private String cpf;
    private List<Exame> exames;

    // Construtor que inicializa os dados do paciente
    public Paciente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.exames = new ArrayList<>();
    }

    // Metodo para adicionar um exame à lista do paciente
    public synchronized void adicionarExame(Exame exame) {
        if(exame == null) return;
        exame.setData(LocalDateTime.now());
        this.exames.add(exame);
    }


    // Retorna a lista de exames
    public synchronized List<Exame> getExames() {
        return exames;
    }

    // Exibe todos os exames do paciente
    public synchronized void listarExames() {

        if(exames.isEmpty()){
            System.out.println("Nenhum exame registrado para este paciente.");
            return;
        }

        System.out.println("Exames do paciente: " + nome);
        System.out.println("Portador do cpf: " + cpf);
        System.out.println("--------------------");
        System.out.println("Resultados do exame:");
        for (Exame e : exames) {
            System.out.println("\n- " + e.getNomeExame() + " realizado em " + e.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            System.out.println("Valor: " + e.getValor());
            System.out.println("Faixa normal: " + e.getFaixaNormal());
            System.out.println("Situação: " + (e.estaForaDaFaixa() ? "Alterado" : "Normal"));
            System.out.println("--------------------\n");
        }
    }

    // !!! ADICIONADO DEVIDO A NECESSIDADE DA CLASSE "Arquivo" !!!
    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    //____________________________________________________________

}

