package model;

import utils.Data;

/**
 * Classe abstrata que representa uma pessoa, com propriedades comuns como ID, nome, sexo e data de nascimento.
 */
public abstract class Pessoa {
    protected int id;
    protected String nome;
    protected char sexo;
    protected Data dataNascimento;

    /**
     * Construtor que inicializa os dados de uma pessoa.
     *
     * @param id              Identificador único da pessoa.
     * @param nome            Nome da pessoa.
     * @param sexo            Sexo da pessoa ('M' ou 'F').
     * @param dataNascimento  Data de nascimento da pessoa.
     */
    public Pessoa(int id, String nome, char sexo, Data dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = new Data(dataNascimento);
    }

    /**
     * Construtor de cópia.
     *
     * @param p Objeto do tipo Pessoa a ser copiado.
     */
    public Pessoa(Pessoa p) {
        this.id = p.id;
        this.nome = p.nome;
        this.sexo = p.sexo;
        this.dataNascimento = new Data(p.dataNascimento);
    }

    /**
     * Devolve o ID da pessoa.
     *
     * @return ID numérico.
     */
    public int getId() {
        return id;
    }

    /**
     * Devolve o nome da pessoa.
     *
     * @return Nome da pessoa.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Devolve o sexo da pessoa.
     *
     * @return 'M' ou 'F'.
     */
    public char getSexo() {
        return sexo;
    }

    /**
     * Devolve a data de nascimento da pessoa.
     *
     * @return Objeto Data representando a data de nascimento.
     */
    public Data getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Representação textual da pessoa.
     *
     * @return String com os dados da pessoa.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pessoa{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", sexo=").append(sexo);
        sb.append(", dataNascimento=").append(dataNascimento);
        sb.append('}');
        return sb.toString();
    }
}

