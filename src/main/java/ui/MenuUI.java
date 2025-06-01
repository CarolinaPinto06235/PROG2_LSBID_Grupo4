package ui;
import model.Ficheiros;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.Estatisticas;
import model.Hospital;
import model.TecnicoDeSaude;
import utils.Utils;

import java.io.IOException;
import java.util.Scanner;
public class MenuUI {
    private Hospital hospital;
    private String opcao;
    Scanner sc = new Scanner(System.in);

    /**
     * Construtor da classe MenuUI que associa o menu a um hospital.
     * @param hospital objeto Hospital para manipulação dos dados.
     */
    public MenuUI(Hospital hospital) {
        this.hospital = hospital;
    }

    /**
     * Executa o menu principal do sistema, tratando a interação com o utilizador e chamando os métodos apropriados.
     * @throws IOException caso ocorra erro no carregamento ou gravação dos ficheiros
     */
    public void run() throws IOException {
        TecnicoDeSaude tecnico = null;

        try {
            Ficheiros.carregarPacientes(hospital);
            Ficheiros.carregarTecnicos(hospital);
            Ficheiros.carregarSinaisVitais(hospital);
            System.out.println("Ficheiros carregados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar ficheiros: " + e.getMessage());
        }

        do {

            System.out.println("\n-------------------------  MONOTORIZAÇÃO DOS PACIENTES INTERNADOS NUMA UCI ------------------------");
            System.out.println("--------------------------------------------------------------------------------------------------\n");
            System.out.println("\n--------------------------------------    MENU PRINCIPAL    --------------------------------------");
            System.out.println("----------------- |  1 - Introdução de dados.                                 |  -----------------");
            System.out.println("----------------- |  2 - Visualização de todos os dados.                      |  -----------------");
            System.out.println("----------------  |  3 - Ordenar pacientes por data de nascimento.            |  -----------------");
            System.out.println("----------------  |  4 - Ordenar técnicos de saúde por nome.                  |  -----------------");
            System.out.println("----------------- |  5 - Alteração dos sinais vitais.                         |  -----------------");
            System.out.println("----------------- |  6 - Percentagem de pacientes em situação crítica.        |  -----------------");
            System.out.println("----------------- |  7 - Estatísticas dos pacientes.                          |  -----------------");
            System.out.println("----------------- |  8 - Determinação do score de gravidade de um paciente.   |  -----------------");
            System.out.println("----------------- |  9 - Visualização de gráficos de barras.                  |  -----------------");
            System.out.println("----------------- | 10 - Guardar e visualizar dados do ficheiro.              |  -----------------");
            System.out.println("----------------- |  0 - Terminar aplicação.                                  |  -----------------");
            System.out.println("--------------------------------------------------------------------------------------------------");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if (opcao.equals("1")) {
                System.out.println("\nSelecionou a opção: Introdução de dados.\n");
                RegistarPaciente_UI ui = new RegistarPaciente_UI(hospital);
                ui.run();
            } else if (opcao.equals("2")) {
                System.out.println("\nSelecionou a opção: Visualização de dados no ecrã.\n");
                hospital.visualizarDados();
            } else if (opcao.equals("3")) {
                System.out.println("\nSelecionou a opção: Ordenar pacientes por data de nascimento.\n");
                hospital.ordenarPacientesPorDataNascimento();
            } else if (opcao.equals("4")) {
                System.out.println("\nSelecionou a opção: Ordenar técnicos de saúde por nome (A-Z).\n");
                hospital.ordenarTecnicosPorNome();
            } else if (opcao.equals("5")) {
                System.out.println("\nSelecionou a opção: Alteração dos sinais vitais.\n");
                Ficheiros.alterarSinaisVitais(hospital, tecnico);
            } else if (opcao.equals("6")) {
                System.out.println("\nSelecionou a opção: Percentagem de pacientes em situação crítica.\n");
                Estatisticas estatisticas = new Estatisticas();
                estatisticas.mostrarPercentagemCriticos(hospital);
            } else if (opcao.equals("7")) {
                System.out.println("\nSelecionou a opção: Estatísticas dos pacientes (um paciente, um grupo de pacientes ou de todos os pacientes).\n");
                Estatisticas estatisticas = new Estatisticas();
                estatisticas.mostrarMenu(hospital);
            } else if (opcao.equals("8")) {
                System.out.println("\nSelecionou a opção: Determinação do score de gravidade de um paciente.\n");
                Estatisticas estatisticas = new Estatisticas();
                estatisticas.calcularScoreGravidade(hospital);
            } else if (opcao.equals("9")) {
                System.out.println("\nSelecionou a opção: Visualização de gráficos de barras.\n");
                Estatisticas estatisticas = new Estatisticas();
                estatisticas.mostrarGraficoBarras(hospital);
            } else if (opcao.equals("10")) {
                System.out.println("\nSelecionou a opção: Guardar dados do ficheiro.\n");
                Ficheiros.guardarPacientes(hospital);
                Ficheiros.guardarTecnicos(hospital);
                Ficheiros.guardarSinaisVitais(hospital);
                System.out.println("Dados guardados com sucesso.");
                hospital.visualizarDados();
            }
        }
        while (!opcao.equals("0")) ;
    }
}
