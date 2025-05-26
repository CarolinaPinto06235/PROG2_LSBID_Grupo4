package model;
import utils.Data;

public class FrequenciaCardiaca extends Medida {
    private double frequenciaCardiaca;
    private static final double MIN_FC = 40.0;
    private static final double MAX_FC = 160.0;

    /**
     * Representa uma medição de frequência cardíaca de um paciente.
     */
    public FrequenciaCardiaca(Data dataRegisto, double frequenciaCardiaca, Paciente paciente, TecnicoDeSaude tecnicoDeSaude) {
        super(dataRegisto, paciente, tecnicoDeSaude);
        if (frequenciaCardiaca >= MIN_FC && frequenciaCardiaca <= MAX_FC) {
            this.frequenciaCardiaca = frequenciaCardiaca;
        } else {
            System.out.println("Frequência cardíaca fora dos limites válidos (40-160 bpm). Insira um valor dentro dos limites válidos.");
            this.frequenciaCardiaca = MIN_FC;
        }
    }

    /**
     * Devolve o valor da frequência cardíaca.
     *
     * @return Frequência cardíaca em bpm.
     */
    public double getFrequenciaCardiaca() {
        return frequenciaCardiaca;
    }

    /**
     * Devolve uma representação textual da medição de frequência cardíaca.
     *
     * @return String formatada com data, paciente, técnico e valor medido.
     */
    @Override
    public String toString() {
        return String.format(
                "Frequência Cardíaca:  Data: %s | Paciente: %s (ID: %d) | Técnico: %s (ID: %d) | Valor: %.2f bpm",
                this.getDataRegisto(),
                this.getPaciente().getNome(),
                this.getPaciente().getId(),
                this.getTecnicoDeSaude().getNome(),
                this.getTecnicoDeSaude().getId(),
                this.getFrequenciaCardiaca()
        );
    }
}
