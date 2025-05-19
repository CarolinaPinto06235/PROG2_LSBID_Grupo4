package model;

import utils.Data;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Ficheiros {
    private static final String FICHEIRO_PACIENTES = "pacientes.txt";
    public static final String FICHEIRO_TECNICOS = "tecnicos.txt";
    public static final String FICHEIRO_SINAIS_VITAIS = "sinais_vitais.txt";

    public static void mostrarDadosDoFicheiro(Hospital hospital) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Deseja observar os dados dos (1) Pacientes, dos (2) Técnicos de Saúde ou (3) os Sinais Vitais dos Pacientes?");
        int escolha = sc.nextInt();
        sc.nextLine();

        if (escolha == 1) {
            carregarPacientes(hospital);
            mostrarPacientes(hospital.getLstPacientes());
        } else if (escolha == 2) {
            carregarTecnicos(hospital);
            mostrarTecnicos(hospital.getLstTecnicos());
        } else if (escolha == 3) {
            carregarSinaisVitais(hospital);
            mostrarSinaisVitais(hospital);
        } else {
            System.out.println("Opção inválida.");
        }
    }

    public static void carregarPacientes(Hospital hospital) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FICHEIRO_PACIENTES));
        String linha;
        while ((linha = br.readLine()) != null) {
            if (linha.trim().isEmpty()) continue;
            String[] campos = linha.split(";");
            if (campos.length < 5) continue;

            int id = Integer.parseInt(campos[0].trim());
            String nome = campos[1].trim();
            char sexo = campos[2].trim().charAt(0);
            Data dataNascimento = new Data(campos[3].trim());
            Data dataInternamento = new Data(campos[4].trim());

            Paciente p = new Paciente(id, nome, sexo, dataNascimento, dataInternamento);

            boolean existe = hospital.getLstPacientes().stream()
                    .anyMatch(pac -> pac.getId() == id);
            if (!existe) {
                hospital.adicionarPaciente(p);
            }
        }
        br.close();
    }

    public static void carregarTecnicos(Hospital hospital) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FICHEIRO_TECNICOS));
        String linha;
        while ((linha = br.readLine()) != null) {
            if (linha.trim().isEmpty()) continue;
            String[] campos = linha.split(",");
            if (campos.length < 4) continue;

            String nome = campos[0].trim();
            Data dataNascimento = new Data(campos[1].trim());
            char sexo = campos[2].trim().charAt(0);
            String categoria = campos[3].trim();

            TecnicoDeSaude t = new TecnicoDeSaude(nome, dataNascimento, sexo, categoria);

            boolean existe = hospital.getLstTecnicos().stream()
                    .anyMatch(tec -> tec.getNome().equalsIgnoreCase(nome) &&
                            tec.getDataNascimento().equals(dataNascimento));
            if (!existe) {
                hospital.getLstTecnicos().add(t);
            }
        }
        br.close();
    }

    public static void carregarSinaisVitais(Hospital hospital) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FICHEIRO_SINAIS_VITAIS));
        String linha;
        while ((linha = br.readLine()) != null) {
            if (linha.trim().isEmpty()) continue;

            String[] campos = linha.split(";");
            if (campos.length < 5) continue;

            int ID = Integer.parseInt(campos[0].trim());
            String nome = campos[1].trim();
            double frequenciaCardiaca = Double.parseDouble(campos[2].trim());
            double saturacaoOxigenio = Double.parseDouble(campos[3].trim());
            double temperatura = Double.parseDouble(campos[4].trim());

            boolean pacienteExiste = hospital.getLstPacientes().stream()
                    .anyMatch(pac -> pac.getId() == ID);

            if (!pacienteExiste) {
                System.out.println("Paciente com ID " + ID + " não encontrado. Ignorando linha.");
                continue;
            }

            hospital.getLstFreqCard().add(new FrequenciaCardiaca(ID, frequenciaCardiaca));
            hospital.getLstSaturacao().add(new SaturacaoOxigenio(ID, saturacaoOxigenio));
            hospital.getLstTemperatura().add(new Temperatura(ID, temperatura));
        }
        br.close();
    }

    public static void alterarSinaisVitaisEGuardar(Hospital hospital) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o nome do técnico de saúde que regista os dados: ");
        String nomeTecnico = sc.nextLine();

        TecnicoDeSaude tecnico = hospital.getLstTecnicos().stream()
                .filter(t -> t.getNome().equalsIgnoreCase(nomeTecnico))
                .findFirst()
                .orElse(null);

        if (tecnico == null) {
            System.out.println("Técnico não encontrado.");
            return;
        }

        hospital.alterarSinaisVitais(tecnico);
        guardarSinaisVitais(hospital);
    }

    private static void mostrarPacientes(List<Paciente> pacientes) {
        System.out.println("----- Lista de Pacientes -----");
        for (Paciente p : pacientes) {
            System.out.println(p);
        }
        System.out.println("------------------------------");
    }

    private static void mostrarTecnicos(List<TecnicoDeSaude> tecnicos) {
        System.out.println("----- Lista de Técnicos de Saúde -----");
        for (TecnicoDeSaude t : tecnicos) {
            System.out.println(t);
        }
        System.out.println("-------------------------------------");
    }

    private static void mostrarSinaisVitais(Hospital hospital) {
        System.out.println("----- Frequências Cardíacas -----");
        for (FrequenciaCardiaca f : hospital.getLstFreqCard()) {
            System.out.println(f);
        }

        System.out.println("----- Saturações de Oxigénio -----");
        for (SaturacaoOxigenio s : hospital.getLstSaturacao()) {
            System.out.println(s);
        }

        System.out.println("----- Temperaturas -----");
        for (Temperatura t : hospital.getLstTemperatura()) {
            System.out.println(t);
        }
    }

    public static void guardarPacientes(Hospital hospital) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FICHEIRO_PACIENTES));
        for (Paciente p : hospital.getLstPacientes()) {
            String linha = p.getId() + "," + p.getNome() + "," + p.getSexo() + "," +
                    p.getDataNascimento().toString() + "," + p.getDataInternamento().toString();
            bw.write(linha);
            bw.newLine();
        }
        bw.close();
    }

    public static void guardarTecnicos(Hospital hospital) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FICHEIRO_TECNICOS));
        for (TecnicoDeSaude t : hospital.getLstTecnicos()) {
            String linha = t.getId() + ", " + t.getNome() + ", " + t.getDataNascimento().toString() + "," +
                    t.getSexo() + "," + t.getCategoriaProfissional();
            bw.write(linha);
            bw.newLine();
        }
        bw.close();
    }

    public static void guardarSinaisVitais(Hospital hospital) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FICHEIRO_SINAIS_VITAIS));
        for (Paciente p : hospital.getLstPacientes()) {
            for (Temperatura temp : hospital.getLstTemperatura()) {
                bw.write("Paciente: " + temp.getPaciente().getId() + ", " + temp.getPaciente().getNome()
                        + " | Valor Temperatura: " + temp.getTemperatura()
                        + " | Data: " + temp.getDataRegisto()
                        + " | Técnico de Saúde: " + temp.getTecnicoDeSaude().getId()
                        + ", " + temp.getTecnicoDeSaude().getNome());
            }

            for (FrequenciaCardiaca fc : hospital.getLstFreqCard()) {
                bw.write(("Paciente: " + fc.getPaciente().getId() + ", " + fc.getPaciente().getNome()
                        + " | Valor Frequência: " + fc.getFrequenciaCardiaca()
                        + " | Data: " + fc.getDataRegisto()
                        + " | Técnico de Saúde: " + fc.getTecnicoDeSaude().getId()
                        + ", " + fc.getTecnicoDeSaude().getNome()));
            }

            for (SaturacaoOxigenio sat : hospital.getLstSaturacao()) {
                bw.write("Paciente: " + sat.getPaciente().getId() + ", " + sat.getPaciente().getNome()
                        + " | Valor Saturação: " + sat.getSaturacaoOxigenio()
                        + " | Data: " + sat.getDataRegisto()
                        + " | Técnico de Saúde: " + sat.getTecnicoDeSaude().getId()
                        + ", " + sat.getTecnicoDeSaude().getNome());
            }
        }
        bw.close();
    }
}
