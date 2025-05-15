package model;

import javax.xml.crypto.Data;

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

    public String categoriaProfissional() {
        return categoriaProfissional;
    }

    public void setCategoriaProfissional(String categoriaProfissional) {
        this.categoriaProfissional = categoriaProfissional;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Técnico de Saúde{");
        sb.append(super.toString());
        sb.append("categoriaProfissional=").append(categoriaProfissional);
        sb.append('}');
        return sb.toString();
    }

}

