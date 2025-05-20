package model;

import utils.Data;

public abstract class Pessoa {
    protected int id;
    protected String nome;
    protected char sexo;
    protected Data dataNascimento;

    public Pessoa(int id, String nome, char sexo, Data dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = new Data(dataNascimento); // agora funciona, pois existe o construtor Data(Data)
    }

    public Pessoa(Pessoa p) {
        this.id = p.id;
        this.nome = p.nome;
        this.sexo = p.sexo;
        this.dataNascimento = new Data(p.dataNascimento);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public char getSexo() {
        return sexo;
    }

    public Data getDataNascimento() {
        return dataNascimento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public void setDataNascimento(Data dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

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

