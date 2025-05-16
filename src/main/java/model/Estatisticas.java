package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Estatisticas implements Calculo {

    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenu(Hospital hospital) {
        System.out.println("\n------- Estatísticas -------");
        System.out.println("1. Estatísticas de um grupo de pacientes. ");
        System.out.println("2. Estatísticas de todos os pacientes. ");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> calcularEstatisticas(hospital);
            case 2 -> calcularEstatisticasGrupo(hospital);
            default -> System.out.println("Opção inválida.");
        }
    }

    public void calcularEstatisticas(Hospital hospital) {
        double[] freqArray = extrairFrequencias(hospital);
        double[] tempArray = extrairTemperaturas(hospital);
        double[] satArray = extrairSaturacoes(hospital);

        mostrarEstatisticas(freqArray, tempArray, satArray);
    }

    public void calcularEstatisticasGrupo(Hospital hospital) {
        System.out.print("Digite os IDs dos pacientes que deseja selecionar, separados por vírgula: ");
        String linha = scanner.nextLine();
        String[] idTexto = linha.split(",");

        List<Double> freqList = new ArrayList<>();
        List<Double> tempList = new ArrayList<>();
        List<Double> satList = new ArrayList<>();
        List<String> nomesGrupo = new ArrayList<>();

        for (int i = 0; i < idTexto.length; i++) {
            String texto = idTexto[i];
            texto = texto.trim();

            if (texto.length() > 0) {
                int ID = Integer.parseInt(texto);

                Paciente paciente = null;
                for (Paciente p : hospital.getLstPacientes()) {
                    if (p.getId() == ID) {
                        paciente = p;
                        nomesGrupo.add(p.getNome());
                        break;
                    }
                }

                if (paciente == null) {
                    System.out.println("Paciente com ID " + ID + " não encontrado.");
                    continue;
                }

                for (FrequenciaCardiaca f : hospital.getLstFreqCard()) {
                    if (f.getIDpaciente() == ID) {
                        freqList.add(f.getFrequenciaCardiaca());
                    }
                }


                for (Temperatura t : hospital.getLstTemperatura()) {
                    if (t.getIDpaciente() == ID) {
                        tempList.add(t.getTemperatura());
                    }
                }

                for (SaturacaoOxigenio s : hospital.getLstSaturacao()) {
                    if (s.getIDpaciente() == ID) {
                        satList.add(s.getSaturacaoOxigenio());
                    }
                }
            }
        }

        double[] freqArray = listToArray(freqList);
        double[] tempArray = listToArray(tempList);
        double[] satArray = listToArray(satList);

        mostrarEstatisticas(freqArray, tempArray, satArray);

        System.out.println("\nPacientes incluídos no grupo: ");
        for (String nome : nomesGrupo) {
            System.out.println((" - " + nome));
        }
    }

    private void mostrarEstatisticas(double[] freq, double[] temp, double[] sat) {

        System.out.println("-------Estatísticas dos Sinais Vitais -------");

        System.out.println("Frequência Cardíaca - Média: " + calcularMedia(freq)
                + ", Desvio Padrão: " + calcularDesvioPadrao(freq)
                + ", Mínimo: " + calcularMinimo(freq)
                + ", Máximo: " + calcularMaximo(freq));

        System.out.println("Temperatura - Média: " + calcularMedia(temp)
                + ", Desvio Padrão: " + calcularDesvioPadrao(temp)
                + ", Mínimo: " + calcularMinimo(temp)
                + ", Máximo: " + calcularMaximo(temp));

        System.out.println("Saturação de Oxigênio - Média: " + calcularMedia(sat)
                + ", Desvio Padrão: " + calcularDesvioPadrao(sat)
                + ", Mínimo: " + calcularMinimo(sat)
                + ", Máximo: " + calcularMaximo(sat));
    }


    private double[] extrairFrequencias(Hospital hospital) {
        List<Double> lista = new ArrayList<>();
        for (FrequenciaCardiaca f : hospital.getLstFreqCard()) {
            lista.add(f.getFrequenciaCardiaca());
        }
        return listToArray(lista);
    }

    private double[] extrairTemperaturas(Hospital hospital) {
        List<Double> lista = new ArrayList<>();
        for (Temperatura t : hospital.getLstTemperatura()) {
            lista.add(t.getTemperatura());
        }
        return listToArray(lista);
    }

    private double[] extrairSaturacoes(Hospital hospital) {
        List<Double> lista = new ArrayList<>();
        for (SaturacaoOxigenio s : hospital.getLstSaturacao()) {
            lista.add(s.getSaturacaoOxigenio());
        }
        return listToArray(lista);
    }

    // Método auxiliar para converter List<Double> em double[]
    private double[] listToArray(List<Double> lista) {
        double[] array = new double[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            array[i] = lista.get(i);
        }
        return array;
    }
}

