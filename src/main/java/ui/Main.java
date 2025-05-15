package ui;

import java.util.Scanner;
import java.util.List;
import java.io.IOException;

/**
 * Classe principal do programa.
 * Responsável por executar o sistema de gestão de pacientes e técnicos de saúde numa UCI,
 * incluindo registo de dados, estatísticas e manipulação de ficheiros.
 */

public class Main {

    /**
     * Método principal que inicializa o programa e apresenta o menu de opções ao utilizador.
     *
     * @param args Argumentos da linha de comandos (não utilizados).
     * @throws IOException Caso ocorra erro na leitura ou escrita de ficheiros.
     */

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        Gestor gestor = new Gestor();
        Estatisticas estatisticas = new Estatisticas();


        List<TecnicoDeSaude> tecnicos = Ficheiro.carregarTecnicos();
        if (tecnicos.isEmpty()){
            tecnicos = gestor.getTecnicos();
        }
        gestor.setTecnicos(tecnicos);
        Ficheiro.guardarTecnicos(tecnicos);

        List<Paciente> pacientes = Ficheiro.carregarPacientes();
        gestor.setPacientes(pacientes);
        Ficheiro.carregarMedicoes(pacientes);

        // Menu
        int opcao;
        do {

            System.out.println("\n-------------------------  MONOTORIZAÇÃO DE PACIENTES INTERNADOS NUMA UCI ------------------------");
            System.out.println("--------------------------------------------------------------------------------------------------\n");
            System.out.println("\n--------------------------------------    MENU PRINCIPAL    --------------------------------------");
            System.out.println("----------------- |  1 - Introdução de dados.                                 |  -----------------");
            System.out.println("----------------- |  2 - Leitura de dados a partir de ficheiros.              |  -----------------");
            System.out.println("----------------- |  3 - Visualização de todos os dados.                      |  -----------------");
            System.out.println("----------------  |  4 - Escolher um técnico de Saúde.                        |  -----------------");
            System.out.println("----------------  |  5 - Ordenar pacientes por data de nascimento.            |  -----------------");
            System.out.println("----------------  |  6 - Ordenar técnicos de saúde por nome.                  |  -----------------");
            System.out.println("----------------- |  7 - Alteração dos sinais vitais.                         |  -----------------");
            System.out.println("----------------- |  8 - Percentagem de pacientes em situação crítica.        |  -----------------");
            System.out.println("----------------- |  9 - Determinação do score de gravidade de um paciente.   |  -----------------");
            System.out.println("----------------- | 10 - Guardar e visualizar dados do ficheiro.              |  -----------------");
            System.out.println("----------------- | 11 - Visualização de gráficos de barras.                  |  -----------------");
            System.out.println("----------------- |  0 - Terminar aplicação.                                  |  -----------------");
            System.out.println("--------------------------------------------------------------------------------------------------");


            /* Lê a opção escolhida pelo utilizador */
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    gestor.adicionarPacientes(scanner); // Introdução de dados
                    break;
                case 2:
                    pacientes = Ficheiro.lerPacientesDeTexto("pacientes.txt"); // Leitura de ficheiro
                    System.out.println("Dados lidos do ficheiro com sucesso.");
                    break;
                case 3:
                    gestor.visualizarDados(pacientes); // Visualização de todos os dados
                    break;
                case 4:
                    gestor.escolherTecnicoSaude(scanner);
                    break;
                case 5:
                    gestor.listarPacientesOrdenadosPorDataNascimento(pacientes);
                    break;
                case 6:
                    gestor.listarTecnicosOrdenadosPorNome();
                    break;
                case 7:
                    gestor.alterarSinaisVitaisPercentualmente(pacientes, scanner); // Alteração dos sinais vitais
                    break;
                case 8:
                    double percentagemCriticos = gestor.calcularPercentagemCriticos(pacientes); // Percentagem crítica
                    System.out.printf("Percentagem de pacientes críticos: %.2f%%\n", percentagemCriticos);
                    break;
                case 9:
                    gestor.determinacaoScore(pacientes);
                    break;
                case 10:
                    Ficheiro.guardarPacientes(pacientes);
                    Ficheiro.guardarTecnicos(gestor.getTecnicos());
                    Ficheiro.guardarMedicoes(pacientes);
                    System.out.println("Dados guardados com sucesso.");
                    break;
                case 11:
                    gestor.mostrarGraficosDeBarras(pacientes, scanner); // Gráficos de barras
                    break;
                case 0:
                    System.out.println("Aplicação terminada.");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 8);
        // O loop continua até o utilizador escolher a opção 8

        scanner.close();
    }

}