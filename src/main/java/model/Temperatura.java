package model;

import javax.xml.crypto.Data;

public class Temperatura extends Medida {
    private double temperatura;

    public Temperatura(Data dataRegisto, double temperatura, Paciente paciente, TecnicoDeSaude tecnicoDeSaude) {
        super(dataRegisto, paciente, tecnicoDeSaude);
        this.temperatura = temperatura;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Temperatura{");
        sb.append(super.toString());
        sb.append("temperatura=").append(temperatura);
        sb.append('}');
        return sb.toString();
    }
}
