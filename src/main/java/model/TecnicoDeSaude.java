package model;
import utils.Data;


public class TecnicoDeSaude extends Pessoa{
    private String categoriaProfissional;

    public TecnicoDeSaude(int id, String nome, char sexo, Data dataNascimento, String categoriaProfissional) {
        super(id, nome, sexo, dataNascimento);
        this.categoriaProfissional = categoriaProfissional;
    }
    public TecnicoDeSaude(TecnicoDeSaude ts) {
        super(ts);
        this.categoriaProfissional = ts.categoriaProfissional;
    }

    public String getCategoriaProfissional() {
        return categoriaProfissional;
    }

    public void setCategoriaProfissional(String categoriaProfissional) {
        this.categoriaProfissional = categoriaProfissional;
    }

    @Override
    public String toString() {
       return "Técnico de Saúde: " +  getId() + ", " + getNome() + ", " + getSexo() + ", " + getDataNascimento().toString() + ", " + categoriaProfissional;
    }

}

