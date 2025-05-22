package model;
import utils.Data;

public class Paciente extends Pessoa  {
    private Data dataInternamento;

    public Paciente(int id, String nome, char sexo, Data dataNascimento, Data dataInternamento) {
        super(id, nome, sexo, dataNascimento);
        this.dataInternamento = dataInternamento;
    }

    public Paciente(Paciente p) {
        super(p);
        this.dataInternamento = p.dataInternamento;
    }

    public Data getDataNascimento() {
        return super.getDataNascimento();
    }

    public Data getDataInternamento() {
        return dataInternamento;
    }

    public void setDataInternamento(Data dataInternamento) {
        this.dataInternamento = dataInternamento;
    }

    @Override
    public String toString() {
        return "Paciente: " + getId() + ", " + getNome() + ", " + getSexo() + ", " + getDataNascimento().toString() + ", " + dataInternamento.toString();
    }
}
