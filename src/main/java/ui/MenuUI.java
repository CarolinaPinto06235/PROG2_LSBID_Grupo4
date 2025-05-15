package ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.ViagensTop;
import utils.Utils;

import model.Hospital;
import java.io.IOException;

public class MenuUI {
    private Hospital hospital;
    private String opcao;


    public MenuUI(Hospital hospital) {
        this.hospital = hospital;
    }

    public void run() {
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

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if (opcao.equals("1")) {
                hospital.adicionarPaciente
                System.out.println("Selecionou a opção: Introdução de dados.");
            } else if (opcao.equals("2")) {
                System.out.println("Selecionou a opção: Leitura de dados a partir de um ficheiro de texto.");
            } else if (opcao.equals("3")) {
                System.out.println("Selecionou a opção: Visualização de dados no ecrã.");
            } else if (opcao.equals("4")) {
                System.out.println("Selecionou a opção: Escolher um técnico de saúde.");
            } else if (opcao.equals("5")) {
                System.out.println("Selecionou a opção: Ordenar pacientes por data de nascimento.");
            } else if (opcao.equals("6")) {
                System.out.println("Selecionou a opção: Ordenar técnicos de saúde por nome.");
            } else if (opcao.equals("7")) {
                System.out.println("Selecionou a opção: Alteração dos sinais vitais.");
            } else if (opcao.equals("8")) {
                System.out.println("Selecionou a opção: Percentagem de pacientes em situação crítica.");
            } else if (opcao.equals("9")) {
                System.out.println("Selecionou a opção: Determinação do score de gravidade de um paciente.");
            } else if (opcao.equals("10")) {
                System.out.println("Selecionou a opção: Guardar e visualizar dados do ficheiro.");
            } else if (opcao.equals("11")) {
                System.out.println("Selecionou a opção: Visualização de gráficos de barras.");
            }
            while (!opcao.equals("0")) ;
        }
    }
}
