package model;
import utils.Data;

/**
 * Classe abstrata que representa uma medição feita a um paciente,
 * realizada por um técnico de saúde, numa data específica.
 */
public abstract class Medida {
    private Data dataRegisto;
    private Paciente paciente;
    private TecnicoDeSaude tecnicoDeSaude;

    /**
     * Construtor da classe Medida.
     *
     * @param dataRegisto      Data em que a medição foi realizada.
     * @param paciente         Paciente ao qual a medição foi realizada.
     * @param tecnicoDeSaude   Técnico de saúde responsável pela medição.
     */
    public Medida(Data dataRegisto, Paciente paciente, TecnicoDeSaude tecnicoDeSaude) {
        this.dataRegisto = dataRegisto;
        this.paciente = paciente;
        this.tecnicoDeSaude = tecnicoDeSaude;
    }

    /**
     * Obtém a data do registo da medição.
     *
     * @return {@link Data} da medição.
     */
    public Data getDataRegisto() {
        return dataRegisto;
    }

    /**
     * Obtém o paciente associado à medição.
     *
     * @return {@link Paciente} associado.
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * Obtém o ID do paciente associado à medição.
     *
     * @return ID do paciente.
     */
    public int getIDpaciente() {
        return paciente.getId();
    }

    /**
     * Obtém o técnico de saúde que realizou a medição.
     *
     * @return {@link TecnicoDeSaude} responsável.
     */
    public TecnicoDeSaude getTecnicoDeSaude() {
        return tecnicoDeSaude;
    }

    /**
     * Representação textual da medição.
     *
     * @return String com os dados da medição.
     */
    @Override
    public String toString() {
        return "Medições: " + dataRegisto.toString() + ", " + paciente.getNome() + ", " + paciente.getId() + ", " + tecnicoDeSaude.getNome() + ", " + tecnicoDeSaude.getId();
    }
}
