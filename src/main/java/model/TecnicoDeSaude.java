package model;
import utils.Data;


/**
 * Representa um Técnico de Saúde, que é uma especialização da classe Pessoa.
 */
public class TecnicoDeSaude extends Pessoa{
    private String categoriaProfissional;

    /**
     * Construtor principal da classe Técnico de Saúde.
     *
     * @param id Identificador único do técnico.
     * @param nome Nome completo do técnico.
     * @param sexo Sexo do técnico (M/F).
     * @param dataNascimento Data de nascimento do técnico.
     * @param categoriaProfissional Categoria profissional do técnico.
     */
    public TecnicoDeSaude(int id, String nome, char sexo, Data dataNascimento, String categoriaProfissional) {
        super(id, nome, sexo, dataNascimento);
        this.categoriaProfissional = categoriaProfissional;
    }

    /**
     * Construtor de cópia que cria uma nova instância baseada em outro TécnicoDeSaude.
     *
     * @param ts TécnicoDeSaude a copiar.
     */
    public TecnicoDeSaude(TecnicoDeSaude ts) {
        super(ts);
        this.categoriaProfissional = ts.categoriaProfissional;
    }

    /**
     * Devolve a categoria profissional do técnico
     *
     * @return Categoria profissional.
     */
    public String getCategoriaProfissional() {
        return categoriaProfissional;
    }

    /**
     * Representação textual do Técnico de Saúde.
     *
     * @return String com os dados principais do técnico.
     */
    @Override
    public String toString() {
       return "Técnico de Saúde: " +  getId() + ", " + getNome() + ", " + getSexo() + ", " + getDataNascimento().toString() + ", " + categoriaProfissional;
    }

}

