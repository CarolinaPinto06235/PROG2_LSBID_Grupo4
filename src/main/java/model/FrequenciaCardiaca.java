package model;

import utils.Data;

public class FrequenciaCardiaca extends Medida {
    private double frequenciaCardiaca;
    private static final double MIN_FC = 40.0;
    private static final double MAX_FC = 160.0;

    public FrequenciaCardiaca(Data dataRegisto, double frequenciaCardiaca, Paciente paciente, TecnicoDeSaude tecnicoDeSaude) {
        super(dataRegisto, paciente, tecnicoDeSaude);
        if (frequenciaCardiaca >= MIN_FC && frequenciaCardiaca <= MAX_FC) {
            this.frequenciaCardiaca = frequenciaCardiaca;
        } else {
            System.out.println("Frequência cardíaca fora dos limites válidos (40-160 bpm). Insira um valor dentro dos limites válidos.");
            this.frequenciaCardiaca = MIN_FC;
        }
    }

    public double getFrequenciaCardiaca() {
        return frequenciaCardiaca;
    }

    public void setFrequenciaCardiaca(double frequenciaCardiaca) {
        if (frequenciaCardiaca >= MIN_FC && frequenciaCardiaca <= MAX_FC) {
            this.frequenciaCardiaca = frequenciaCardiaca;
        } else {
            System.out.println("Frequência cardíaca fora dos limites válidos (40-160 bpm). Insira um valor dentro dos limites válidos.");
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Frequência Cardíaca:  Data: %s | Paciente: %s (ID: %d) | Técnico: %s (ID: %d) | Valor: %d bpm",
                this.getDataRegisto(),
                this.getPaciente().getNome(),
                this.getPaciente().getId(),
                this.getTecnicoDeSaude().getNome(),
                this.getTecnicoDeSaude().getId(),
                this.getFrequenciaCardiaca()
        );
    }
}


