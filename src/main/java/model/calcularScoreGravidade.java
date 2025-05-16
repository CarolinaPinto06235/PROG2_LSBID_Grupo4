package model;

import model.Paciente;
import model.Hospital;
import utils.Utils;

public class calcularScoreGravidade {

     if (lstPacientes.isEmpty()) {
        System.out.println("Não há pacientes registados.");
        return;
    }

    System.out.println("Lista de pacientes:");
    for (int i = 0; i < lstPacientes.size(); i++) {
        System.out.println((i + 1) + ". " + lstPacientes.get(i).getNome());
    }

    int opcao = Utils.readIntFromConsole("Escolha o número do paciente: ") - 1;

    if (opcao < 0 || opcao >= lstPacientes.size()) {
        System.out.println("Opção inválida.");
        return;
    }

    Paciente p = lstPacientes.get(opcao);

    // Obter as últimas medições
    double temp = getUltimaMedicao(mapTemperatura, p);
    double freq = getUltimaMedicao(mapFreqCardiaca, p);
    double spo2 = getUltimaMedicao(mapSaturacaoOxigenio, p);

    if (temp == -1 || freq == -1 || spo2 == -1) {
        System.out.println("Faltam medições para este paciente.");
        return;
    }

    int tempScore = pontuarTemperatura(temp);
    int freqScore = pontuarFrequencia(freq);
    int spo2Score = pontuarSaturacao(spo2);

    double scoreFinal = (freqScore * 0.3) + (tempScore * 0.4) + (spo2Score * 0.3);

    System.out.println("\nScore de Gravidade do paciente \"" + p.getNome() + "\": " + String.format("%.2f", scoreFinal));

    if (scoreFinal <= 2) {
        System.out.println("Gravidade Baixa");
    } else if (scoreFinal <= 3.5) {
        System.out.println("Gravidade Moderada");
    } else {
        System.out.println("Gravidade Alta");
    }
}

// Métodos auxiliares:

private double getUltimaMedicao(Map<Paciente, List<? extends Medicao>> mapa, Paciente p) {
    List<? extends Medicao> medicoes = mapa.get(p);
    if (medicoes == null || medicoes.isEmpty()) return -1;
    return medicoes.get(medicoes.size() - 1).getValor();
}

private int pontuarTemperatura(double t) {
    if (t < 35.0 || t > 40.0) return 5;
    if (t > 39.0) return 4;
    if (t > 38.0) return 3;
    if (t >= 36.0) return 1;
    return 2;
}

private int pontuarFrequencia(double f) {
    if (f < 40 || f > 140) return 5;
    if ((f >= 121 && f <= 140) || (f >= 40 && f <= 49)) return 4;
    if ((f >= 101 && f <= 120) || (f >= 50 && f <= 59)) return 3;
    return 1;
}

private int pontuarSaturacao(double s) {
    if (s < 85) return 5;
    if (s < 90) return 4;
    if (s <= 92) return 3;
    if (s <= 94) return 2;
    return 1;
}

