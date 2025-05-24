package model;

import utils.Data;

public class Temperatura extends Medida {
    private double temperatura;
    private static final double MIN_TEMP = 33.0;
    private static final double MAX_TEMP = 40.0;

    public Temperatura(Data dataRegisto, double temperatura, Paciente paciente, TecnicoDeSaude tecnicoDeSaude) {
        super(dataRegisto, paciente, tecnicoDeSaude);
        if (temperatura >= MIN_TEMP && temperatura <= MAX_TEMP) {
            this.temperatura = temperatura;
        } else {
            System.out.println("Temperatura fora dos limites válidos (33.0-40.0 ºC). Insira um valor dentro dos limites válidos.");
            this.temperatura = MIN_TEMP;
        }
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        if (temperatura >= MIN_TEMP && temperatura <= MAX_TEMP) {
            this.temperatura = temperatura;
        } else {
            System.out.println("Temperatura fora dos limites válidos (33.0-40.0 ºC). Insira um valor dentro dos limites válidos.");
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Temperatura:  Data: %s | Paciente: %s (ID: %d) | Técnico: %s (ID: %d) | Valor: %.1f ºC",
                this.getDataRegisto(),
                this.getPaciente().getNome(),
                this.getPaciente().getId(),
                this.getTecnicoDeSaude().getNome(),
                this.getTecnicoDeSaude().getId(),
                this.getTemperatura()
        );
    }
}

