package br.com.sistemadeexames.arquivo;

import br.com.sistemadeexames.paciente.Paciente;
import br.com.sistemadeexames.exame.Exame;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {

    public static void salvarPacientes(String nomeArquivo, List<Paciente> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {

            for (Paciente p : lista) {
                for (Exame e : p.getExames()) {
                    bw.write(
                            p.getClass().getSimpleName() + ": " +
                                    p.getCpf() + "; " +
                                    p.getNome() + "; " +
                                    e.getNomeExame() + "; " +
                                    e.getValor() + "; " +
                                    e.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                    );
                    bw.newLine();
                }
            }

        } catch (IOException ex) {
            System.out.println("Erro ao salvar arquivo: " + ex.getMessage());
        }
    }

    public static List<String> carregarLinhas(String nomeArquivo) {

        List<String> linhas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException ex) {
            System.out.println("Erro ao carregar arquivo: " + ex.getMessage());
        }

        return linhas;
    }
}
