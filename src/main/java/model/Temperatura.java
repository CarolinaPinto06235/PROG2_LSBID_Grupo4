package model;

import utils.Data;

public class Temperatura extends Medida {
    private double temperatura;
    private static final double MIN_TEMP = 33.0;
    private static final double MAX_TEMP = 40.0;

    /**
     * Construtor da classe Temperatura que valida e define o valor da temperatura.
     *
     * @param dataRegisto Data em que a medição foi registada.
     * @param temperatura Valor da temperatura registada (deve estar entre 33.0 e 40.0).
     * @param paciente Paciente a quem pertence a medição
     * @param tecnicoDeSaude Técnico responsável pela medição.
     */
    public Temperatura(Data dataRegisto, double temperatura, Paciente paciente, TecnicoDeSaude tecnicoDeSaude) {
        super(dataRegisto, paciente, tecnicoDeSaude);
        if (temperatura >= MIN_TEMP && temperatura <= MAX_TEMP) {
            this.temperatura = temperatura;
        } else {
            System.out.println("Temperatura fora dos limites válidos (33.0-40.0 ºC). Insira um valor dentro dos limites válidos.");
            this.temperatura = MIN_TEMP;
        }
    }

    /**
     * Devolve o valor da temperatura registada.
     *
     * @return Temperatura em graus Celsius
     */
    public double getTemperatura() {
        return temperatura;
    }

    /**
     * Representação textual da medição de temperatura com informações detalhadas.
     *
     * @return String formatada com data, paciente, técnico e valor da temperatura.
     */
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

