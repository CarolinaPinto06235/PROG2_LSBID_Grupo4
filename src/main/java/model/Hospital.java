package model;

import utils.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hospital {

    private String nome;
    private final List<Paciente> lstPacientes;
    private final List<FrequenciaCardiaca> lstFreqCard;
    private final List<SaturacaoOxigenio> lstSaturacao;
    private final List<Temperatura> lstTemperatura;
    private final List<TecnicoDeSaude> lstTecnicos;
// Completar


    public Hospital(String nome) {
        this.nome = nome;
        this.lstPacientes = new ArrayList<>();
        this.lstFreqCard = new ArrayList<>();
        this.lstSaturacao = new ArrayList<>();
        this.lstTemperatura = new ArrayList<>();
        this.lstTecnicos = new ArrayList<>();
    }

    public List<Paciente> getLstPacientes() {
        return lstPacientes;
    }

    public List<TecnicoDeSaude> getLstTecnicos() {
        return lstTecnicos;
    }

    public List<FrequenciaCardiaca> getLstFreqCard() {
        return lstFreqCard;
    }

    public List<SaturacaoOxigenio> getLstSaturacao() {
        return lstSaturacao;
    }

    public List<Temperatura> getLstTemperatura() {
        return lstTemperatura;
    }


    public boolean adicionarPaciente(Paciente paciente) {
        for (Paciente p : lstPacientes) {
            if (p.getId() == paciente.getId()) {
                return false;
            }
        }
        lstPacientes.add(paciente);
        return true;
    }

    public boolean adicionarFreqCardiaca(Data dataRegisto, double frequencia, Paciente paciente, TecnicoDeSaude tecnicoDeSaude) {
        if (paciente == null || tecnicoDeSaude == null)
            return false;
        FrequenciaCardiaca frequenciaCardiaca1 = new FrequenciaCardiaca(dataRegisto, frequencia, paciente, tecnicoDeSaude);
        return lstFreqCard.add(frequenciaCardiaca1);
    }


    public boolean adicionarSaturacaoOxigenio (Data dataRegisto, double saturacaoOxigenio, Paciente paciente, TecnicoDeSaude tecnicoDeSaude) {
        if (paciente == null || tecnicoDeSaude == null)
            return false;
        SaturacaoOxigenio saturacaoOxigenio1 = new SaturacaoOxigenio(dataRegisto, saturacaoOxigenio, paciente, tecnicoDeSaude);
        return lstSaturacao.add(saturacaoOxigenio1);
    }


    public boolean adicionarTemperatura (Data dataRegisto, double temperatura, Paciente paciente, TecnicoDeSaude tecnicoDeSaude) {
        if (paciente == null || tecnicoDeSaude == null)
            return false;
        Temperatura temperatura1 = new Temperatura(dataRegisto, temperatura, paciente, tecnicoDeSaude);
        return lstTemperatura.add(temperatura1);
    }

    public void visualizarDados(){
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n------------ Visualizar dados: ------------");
            System.out.println("-------| 1. Pacientes                |-------");
            System.out.println("-------| 2. Técnicos de Saúde        |-------");
            System.out.println("-------| 3. Todos                    |-------");
            System.out.println("-------| 0. Voltar ao menu principal |-------");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    mostrarPacientes();
                    break;
                case 2:
                    mostrarTecnicosSaude();
                    break;
                case 3:
                    mostrarPacientes();
                    mostrarTecnicosSaude();
                    break;
                case 0:
                    System.out.println("A regressar ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);
    }

    public void mostrarPacientes() {
        if (lstPacientes.isEmpty()) {
            System.out.println("Não há pacientes registados.");
        } else {
            System.out.println("------- Lista de Pacientes: -------\n");
            for (Paciente p : lstPacientes) {
                System.out.println(p);
            }
        }
    }

    public void mostrarTecnicosSaude() {
        if (lstTecnicos.isEmpty()) {
            System.out.println("Não há técnicos de saúde para mostrar.");
        } else {
            System.out.println("------- Lista de Técnicos de Saúde: -------\n");
            for (TecnicoDeSaude t : lstTecnicos) {
                System.out.println(t);
            }
        }
    }

/* Alternativa

    public boolean adicionarFreqCardiaca1(Data dataRegisto, double frequencia, int idPaciente, int idTecnicoDeSaude) {
        //Paciente p = procurarPaciente(idPaciente);
        //TecnicoPaciente t = procurarTecnico(idTecnico);
        //....
        return false;
    }

    public boolean listaContemPaciente(int id) {
        for (Paciente paciente : lstPacientes) {
            if (paciente.getId() == id) {
                return true;
            }
        }
        return false;
    }*/
}

// Completar com outras funcionalidades


