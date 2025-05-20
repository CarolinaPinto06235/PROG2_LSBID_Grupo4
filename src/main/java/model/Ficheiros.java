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
        int idContador = hospital.getLstTecnicos().size() + 1;  // Gera id simples
        while ((linha = br.readLine()) != null) {
            if (linha.trim().isEmpty()) continue;
            String[] campos = linha.split(",");
            if (campos.length < 4) continue;

            String nome = campos[0].trim();
            Data dataNascimento = new Data(campos[1].trim());
            char sexo = campos[2].trim().charAt(0);
            String categoria = campos[3].trim();

            TecnicoDeSaude t = new TecnicoDeSaude(idContador++, nome, sexo, dataNascimento, categoria);

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
            if (campos.length < 7) continue;

            int ID = Integer.parseInt(campos[0].trim());
            String nome = campos[1].trim();
            double frequenciaCardiaca = Double.parseDouble(campos[2].trim());
            double saturacaoOxigenio = Double.parseDouble(campos[3].trim());
            double temperatura = Double.parseDouble(campos[4].trim());
            Data dataRegisto = new Data(campos[5].trim());
            String nomeTecnico = campos[6].trim();

            Paciente paciente = hospital.getLstPacientes().stream()
                    .filter(p -> p.getId() == ID)
                    .findFirst()
                    .orElse(null);
            if (paciente == null) {
                System.out.println("Paciente com ID " + ID + " não encontrado. Ignorando linha.");
                continue;
            }

            TecnicoDeSaude tecnico = hospital.getLstTecnicos().stream()
                    .filter(t -> t.getNome().equalsIgnoreCase(nomeTecnico))
                    .findFirst()
                    .orElse(null);
            if (tecnico == null) {
                System.out.println("Técnico " + nomeTecnico + " não encontrado. Ignorando linha.");
                continue;
            }

            hospital.getLstFreqCard().add(new FrequenciaCardiaca(dataRegisto, frequenciaCardiaca, paciente, tecnico));
            hospital.getLstSaturacao().add(new SaturacaoOxigenio(dataRegisto, saturacaoOxigenio, paciente, tecnico));
            hospital.getLstTemperatura().add(new Temperatura(dataRegisto, temperatura, paciente, tecnico));
        }
        br.close();
    }
    public static void alterarSinaisVitais(Hospital hospital, TecnicoDeSaude tecnico) {
        // Exemplo simples para simular alteração dos sinais vitais:
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o ID do paciente para alterar sinais vitais: ");
        int idPaciente = sc.nextInt();
        sc.nextLine();

        Paciente paciente = hospital.getLstPacientes().stream()
                .filter(p -> p.getId() == idPaciente)
                .findFirst()
                .orElse(null);

        if (paciente == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }

        System.out.print("Nova frequência cardíaca: ");
        double novaFreq = sc.nextDouble();
        System.out.print("Nova saturação de oxigênio: ");
        double novaSaturacao = sc.nextDouble();
        System.out.print("Nova temperatura: ");
        double novaTemp = sc.nextDouble();
        sc.nextLine();

        Data hoje = new Data(); // supondo que o construtor Data() pega a data atual

        hospital.getLstFreqCard().add(new FrequenciaCardiaca(hoje, novaFreq, paciente, tecnico));
        hospital.getLstSaturacao().add(new SaturacaoOxigenio(hoje, novaSaturacao, paciente, tecnico));
        hospital.getLstTemperatura().add(new Temperatura(hoje, novaTemp, paciente, tecnico));

        System.out.println("Sinais vitais alterados com sucesso.");
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

    public static void mostrarSinaisVitais(Hospital hospital) {
        System.out.println("----- Frequências Cardíacas -----");
        for (FrequenciaCardiaca f : hospital.getLstFreqCard()) {
            System.out.println(f);
        }

        System.out.println("----- Saturações de Oxigénio -------");
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
            String linha = p.getId() + ";" + p.getNome() + ";" + p.getSexo() + ";" +
                    p.getDataNascimento().toString() + ";" + p.getDataInternamento().toString();
            bw.write(linha);
            bw.newLine();
        }
        bw.close();
    }

    public static void guardarTecnicos(Hospital hospital) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FICHEIRO_TECNICOS));
        for (TecnicoDeSaude t : hospital.getLstTecnicos()) {
            String linha = t.getNome() + "," + t.getDataNascimento().toString() + "," +
                    t.getSexo() + "," + t.getCategoriaProfissional();
            bw.write(linha);
            bw.newLine();
        }
        bw.close();
    }

    public static void guardarSinaisVitais(Hospital hospital) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FICHEIRO_SINAIS_VITAIS));
        for (Temperatura temp : hospital.getLstTemperatura()) {
            bw.write(temp.getPaciente().getId() + ";" + temp.getPaciente().getNome() + ";" +
                    temp.getTemperatura() + ";" + temp.getDataRegisto() + ";" +
                    temp.getTecnicoDeSaude().getNome());
            bw.newLine();
        }

        for (FrequenciaCardiaca fc : hospital.getLstFreqCard()) {
            bw.write(fc.getPaciente().getId() + ";" + fc.getPaciente().getNome() + ";" +
                    fc.getFrequenciaCardiaca() + ";" + fc.getDataRegisto() + ";" +
                    fc.getTecnicoDeSaude().getNome());
            bw.newLine();
        }

        for (SaturacaoOxigenio sat : hospital.getLstSaturacao()) {
            bw.write(sat.getPaciente().getId() + ";" + sat.getPaciente().getNome() + ";" +
                    sat.getSaturacaoOxigenio() + ";" + sat.getDataRegisto() + ";" +
                    sat.getTecnicoDeSaude().getNome());
            bw.newLine();
        }
        bw.close();
    }
}


