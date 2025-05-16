package model;

import utils.Data;
import model.Hospital;
import model.Paciente;
import model.TecnicoDeSaude;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Ficheiros {
    private static final String FICHEIRO_PACIENTES = "pacientes.txt";  // Arquivo para pacientes
    public static final String FICHEIRO_TECNICOS = "tecnicos.txt";// Arquivo para técnicos

    public static void mostrarDadosDoFicheiro(Hospital hospital) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Deseja observar os dados dos (1) Pacientes ou dos (2) Técnicos de Saúde?");
        int escolha = sc.nextInt();
        sc.nextLine();

        if (escolha == 1) {
            carregarPacientes(hospital);
            mostrarPacientes(hospital.getLstPacientes());
        } else if (escolha == 2) {
            carregarTecnicos(hospital);
            mostrarTecnicos(hospital.getLstTecnicos());
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
    }
}
