package ui;
import model.Ficheiros;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import model.ViagensTop;
import model.Estatisticas;
import model.Ficheiros;
import model.Hospital;
import model.TecnicoDeSaude;
import utils.Utils;

import java.io.IOException;
import java.util.Scanner;
public class MenuUI {
    private Hospital hospital;
    private String opcao;
    Scanner sc = new Scanner(System.in);

    public MenuUI(Hospital hospital) {
        this.hospital = hospital;
    }

    public void run() throws IOException {
        TecnicoDeSaude tecnico = null;
        do {

            System.out.println("\n-------------------------  MONOTORIZAÇÃO DE PACIENTES INTERNADOS NUMA UCI ------------------------");
            System.out.println("--------------------------------------------------------------------------------------------------\n");
            System.out.println("\n--------------------------------------    MENU PRINCIPAL    --------------------------------------");
            System.out.println("----------------- |  1 - Introdução de dados.                                 |  -----------------");
            System.out.println("----------------- |  2 - Leitura de dados a partir de ficheiros.              |  -----------------");
            System.out.println("----------------- |  3 - Visualização de todos os dados.                      |  -----------------");
            System.out.println("----------------  |  4 - Ordenar pacientes por data de nascimento.            |  -----------------");
            System.out.println("----------------  |  5 - Ordenar técnicos de saúde por nome.                  |  -----------------");
            System.out.println("----------------- |  6 - Alteração dos sinais vitais.                         |  -----------------");
            System.out.println("----------------- |  7 - Percentagem de pacientes em situação crítica.        |  -----------------");
            System.out.println("----------------- |  8 - Estatísticas dos pacientes.                          |  -----------------");
            System.out.println("----------------- |  9 - Determinação do score de gravidade de um paciente.   |  -----------------");
            System.out.println("----------------- | 10 - Guardar e visualizar dados do ficheiro.              |  -----------------");
            System.out.println("----------------- | 11 - Visualização de gráficos de barras.                  |  -----------------");
            System.out.println("----------------- |  0 - Terminar aplicação.                                  |  -----------------");
            System.out.println("--------------------------------------------------------------------------------------------------");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if (opcao.equals("1")) {
                System.out.println("Selecionou a opção: Introdução de dados.");
                RegistarPaciente_UI ui = new RegistarPaciente_UI(hospital);
                ui.run();
            } else if (opcao.equals("2")) {
                System.out.println("Selecionou a opção: Leitura de dados a partir de um ficheiro de texto.");
                Ficheiros.mostrarDadosDoFicheiro(hospital);
            } else if (opcao.equals("3")) {
                System.out.println("Selecionou a opção: Visualização de dados no ecrã.");
                hospital.visualizarDados();
            } else if (opcao.equals("4")) {
                System.out.println("Selecionou a opção: Ordenar pacientes por data de nascimento.");
                hospital.ordenarPacientesPorDataNascimento();
            } else if (opcao.equals("5")) {
                System.out.print("Digite o nome do técnico: ");
                String nomeTecnico = sc.nextLine();
                tecnico = hospital.buscarTecnicoPorNome(nomeTecnico);
                if (tecnico == null) {
                    System.out.println("Técnico não encontrado.");
                }
            } else if (opcao.equals("6")) {
                if (tecnico == null) {
                    System.out.println("Você precisa selecionar um técnico primeiro (opção 5).");
                }
                System.out.println("Selecionou a opção: Alteração dos sinais vitais.");
                Ficheiros.alterarSinaisVitais(hospital, tecnico);
            } else if (opcao.equals("7")) {
                System.out.println("Selecionou a opção: Percentagem de pacientes em situação crítica.");
                Estatisticas estatisticas = new Estatisticas();
                estatisticas.mostrarPercentagemCriticos(hospital);
            } else if (opcao.equals("8")) {
                System.out.println("Selecionou a opção: Estatísticas dos pacientes (um paciente, um grupo de pacientes ou de todos os pacientes).");
                Estatisticas estatisticas = new Estatisticas();
                estatisticas.mostrarMenu(hospital);
            } else if (opcao.equals("9")) {
                System.out.println("Selecionou a opção: Determinação do score de gravidade de um paciente.");
                Estatisticas estatisticas = new Estatisticas();
                estatisticas.calcularScoreGravidade(hospital);
            } else if (opcao.equals("10")) {
                System.out.println("Selecionou a opção: Guardar e visualizar dados do ficheiro.");
                Ficheiros.guardarPacientes(hospital);
                Ficheiros.guardarTecnicos(hospital);
                Ficheiros.guardarSinaisVitais(hospital);
                System.out.println("Dados guardados com sucesso.");
                Ficheiros.mostrarDadosDoFicheiro(hospital);
                Ficheiros.carregarSinaisVitais(hospital);
                Ficheiros.mostrarSinaisVitais(hospital);
            } else if (opcao.equals("11")) {
                System.out.println("Selecionou a opção: Visualização de gráficos de barras.");
                Estatisticas estatisticas = new Estatisticas();
                estatisticas.mostrarGraficoBarras(hospital);
            }
        }
        while (!opcao.equals("0")) ;
    }
}
