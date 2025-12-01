package br.com.sistemadeexames;

import br.com.sistemadeexames.arquivo.Arquivo;
import br.com.sistemadeexames.exame.Exame;
import br.com.sistemadeexames.exame.analisados.Colesterol;
import br.com.sistemadeexames.exame.analisados.Glicose;
import br.com.sistemadeexames.exame.analisados.Hemograma;
import br.com.sistemadeexames.excecoes.ExceptionCpfInvalido;
import br.com.sistemadeexames.paciente.Paciente;
import br.com.sistemadeexames.thread.ThreadExame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Paciente> pacientes = new ArrayList<>();

        // Lista de exames pendentes para execução (stand-by)
        List<ThreadExame> examesPendentes = new ArrayList<>();

        while (true) {

            System.out.println("\n\uD83C\uDFE5 Sistema de Exames Clínicos ");
            System.out.println("\n1 - Cadastrar paciente");
            System.out.println("2 - Adicionar exame a um paciente");
            System.out.println("3 - Executar exames (threads)");
            System.out.println("4 - Visualizar resultados");
            System.out.println("5 - Salvar resultados em arquivo");
            System.out.println("0 - Sair");

            int opcao;
            try {
                System.out.print("\nEscolha: ");
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite apenas números: Opções de 0 a 5.");
                continue;
            }

            switch (opcao) {

                case 1 -> {
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();
                        String cpf = null;
                        while (true) {
                            System.out.print("CPF: ");

                            try {
                                cpf = formatarCPF(sc.nextLine());
                                break;
                            } catch (ExceptionCpfInvalido e) {
                                System.out.println("Erro ao validar cpf: " + e.getMessage());
                            }
                        }
                        Paciente novo = new Paciente(nome, cpf);
                        pacientes.add(novo);
                        System.out.println("Paciente cadastrado!");
                }

                case 2 -> {
                    Paciente encontrado = null;
                    while (true) {
                        System.out.print("CPF do paciente: ");
                        try {
                            String cpfBusca = formatarCPF(sc.nextLine());
                            encontrado = buscarPaciente(pacientes, cpfBusca);

                            if (encontrado != null) break;
                            System.out.println("Paciente não encontrado!");
                        } catch (ExceptionCpfInvalido e) {
                            System.out.println("Erro ao validar cpf: " + e.getMessage());
                        }
                    }

                    System.out.println("\nTipo de exame:");
                    System.out.println("1 - Colesterol");
                    System.out.println("2 - Glicose");
                    System.out.println("3 - Hemograma");
                    int tipo;
                    while (true) {
                        try {
                            System.out.print("Escolha: ");
                            tipo = Integer.parseInt(sc.nextLine());
                            if (tipo >= 1 && tipo <= 3) break;
                            System.out.println("Opção inválida!");
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida. Digite apenas números.");
                        }
                    }

                    double valor;
                    while (true) {
                        try {
                            System.out.print("Valor medido: ");
                            valor = Double.parseDouble(sc.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida. Digite apenas números.");
                        }
                    }

                    Exame exame = switch (tipo) {
                        case 1 -> new Colesterol(valor);
                        case 2 -> new Glicose(valor);
                        case 3 -> new Hemograma(valor);
                        default -> null;
                    };


                    ThreadExame thread = new ThreadExame(encontrado, exame, 2);
                    examesPendentes.add(thread);

                    System.out.println("Exame adicionado à fila de execução.");
                }

                case 3 -> {
                    if (examesPendentes.isEmpty()) {
                        System.out.println("Nenhum exame pendente para executar.");
                        break;
                    }

                    System.out.println("\nExecutando todos os exames pendentes...\n");

                    // Inicializa todas as threads
                    for (ThreadExame t : examesPendentes) {
                        t.start();
                    }

                    // Aguarda o fim de todas
                    for (ThreadExame t : examesPendentes) {
                        try {
                            t.join();
                        } catch (InterruptedException ignored) {}
                    }

                    examesPendentes.clear(); // limpa fila

                    System.out.println("\n✔ Todos os exames foram executados!\n");
                }

                case 4 -> {
                    String cpf;

                    while (true) {
                        System.out.print("CPF: ");
                        try {
                            cpf = formatarCPF(sc.nextLine());
                            break;
                        } catch (ExceptionCpfInvalido e) {
                            System.out.println("Erro ao validar CPF: " + e.getMessage());
                        }
                    }
                    Paciente p = buscarPaciente(pacientes, cpf);
                    if (p == null) System.out.println("Paciente não encontrado.");
                    else p.listarExames();
                }


                case 5 -> {
                    Arquivo.salvarPacientes("resultados.txt", pacientes);
                    System.out.println("Arquivo salvo como resultados.txt");
                }

                case 0 -> {
                    System.out.println("Encerrando sistema...");
                    return;
                }

                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // Buscar paciente por CPF
    private static Paciente buscarPaciente(List<Paciente> lista, String cpf) {
        for (Paciente p : lista) {
            if (p.getCpf().equals(cpf)) return p;
        }
        return null;
    }

    //REGEX pra alterar tudo que não for dígito
    private static String formatarCPF(String cpf) throws ExceptionCpfInvalido {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            throw new ExceptionCpfInvalido("CPF deve conter 11 dígitos.");
        }

        return cpf;
    }
}