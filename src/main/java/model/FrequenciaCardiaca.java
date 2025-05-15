package model;

import javax.xml.crypto.Data;

public class FrequenciaCardiaca extends Medida {
    private double frequenciaCardiaca;

    public FrequenciaCardiaca(Data dataRegisto, double frequenciaCardiaca, Paciente paciente, TecnicoDeSaude tecnicoDeSaude) {
        super(dataRegisto, paciente, tecnicoDeSaude);
        this.frequenciaCardiaca = frequenciaCardiaca;
    }

    public double getFrequenciaCardiaca() {
        return frequenciaCardiaca;
    }

    public void setFrequenciaCardiaca(double frequenciaCardiaca) {
        this.frequenciaCardiaca = frequenciaCardiaca;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FrequenciaCardiaca{");
        sb.append(super.toString());
        sb.append("frequencia=").append(frequenciaCardiaca);
        sb.append('}');
        return sb.toString();
    }
}


