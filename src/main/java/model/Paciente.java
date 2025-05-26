package model;
import utils.Data;

/**
 * Representa um paciente, que é uma pessoa internada num hospital.
 * Herda de {@link Pessoa}.
 */
public class Paciente extends Pessoa  {
    private Data dataInternamento;

    /**
     * Construtor que inicializa os dados de um paciente.
     *
     * @param id                Identificador único do paciente.
     * @param nome              Nome do paciente.
     * @param sexo              Sexo do paciente ('M' ou 'F').
     * @param dataNascimento    Data de nascimento do paciente.
     * @param dataInternamento  Data de internamento do paciente.
     */
    public Paciente(int id, String nome, char sexo, Data dataNascimento, Data dataInternamento) {
        super(id, nome, sexo, dataNascimento);
        this.dataInternamento = dataInternamento;
    }

    /**
     * Construtor de cópia.
     *
     * @param p Paciente a ser copiado.
     */
    public Paciente(Paciente p) {
        super(p);
        this.dataInternamento = p.dataInternamento;
    }

    /**
     * Devolve a data de nascimento do paciente.
     *
     * @return Objeto {@link Data} com a data de nascimento.
     */
    public Data getDataNascimento() {
        return super.getDataNascimento();
    }

    /**
     * Devolve a data de internamento do paciente.
     *
     * @return Objeto {@link Data} com a data de internamento.
     */
    public Data getDataInternamento() {
        return dataInternamento;
    }

    /**
     * Representação textual do paciente.
     *
     * @return String formatada com os dados do paciente.
     */
    @Override
    public String toString() {
        return "Paciente: " + getId() + ", " + getNome() + ", " + getSexo() + ", " + getDataNascimento().toString() + ", " + dataInternamento.toString();
    }
}
