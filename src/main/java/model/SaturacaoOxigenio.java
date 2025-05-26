package model;
import utils.Data;

/**
 * Representa a medição da saturação de oxigénio de um paciente.
 */
public class SaturacaoOxigenio extends Medida {
    private double saturacaoOxigenio;
    private static final double MIN_SO2 = 80.0;
    private static final double MAX_SO2 = 100.0;

    /**
     * Construtor que cria uma medição de saturação de oxigénio.
     * Se o valor estiver fora dos limites válidos, define para o valor mínimo e emite aviso.
     *
     * @param dataRegisto Data da medição.
     * @param saturacaoOxigenio Valor da saturação de oxigénio (%).
     * @param paciente Paciente ao qual a medição pertence.
     * @param tecnicoDeSaude Técnico responsável pela medição.
     */
    public SaturacaoOxigenio(Data dataRegisto, double saturacaoOxigenio, Paciente paciente, TecnicoDeSaude tecnicoDeSaude) {
        super(dataRegisto, paciente, tecnicoDeSaude);
        if (saturacaoOxigenio >= MIN_SO2 && saturacaoOxigenio <= MAX_SO2) {
            this.saturacaoOxigenio = saturacaoOxigenio;
        } else {
            System.out.println("Saturação de oxigénio fora dos limites válidos (80-100%). Insira um valor dentro dos limites válidos.");
            this.saturacaoOxigenio = MIN_SO2;
        }
    }

    /**
     * Devolve o valor da saturação de oxigénio.
     *
     * @return Valor da saturação de oxigénio em percentagem.
     */
    public double getSaturacaoOxigenio() {
        return saturacaoOxigenio;
    }

    /**
     * Representação textual da medição de saturação de oxigénio.
     *
     * @return String formatada com detalhes da medição.
     */
    @Override
    public String toString() {
        return String.format(
                "Saturação de Oxigénio: Data: %s | Paciente: %s (ID: %d) | Técnico: %s (ID: %d) | Valor: %.1f%%",
                this.getDataRegisto(),
                this.getPaciente().getNome(),
                this.getPaciente().getId(),
                this.getTecnicoDeSaude().getNome(),
                this.getTecnicoDeSaude().getId(),
                this.getSaturacaoOxigenio()
        );
    }
}
