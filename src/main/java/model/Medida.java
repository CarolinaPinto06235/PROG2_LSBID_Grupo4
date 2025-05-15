package model;
import javax.xml.crypto.Data;

public abstract class Medida {
    private Data dataRegisto;
    private Paciente paciente;
    private TecnicoDeSaude tecnicoDeSaude;

    public Medida(Data dataRegisto, Paciente paciente, TecnicoDeSaude tecnicoDeSaude) {
        this.dataRegisto = dataRegisto;
        this.paciente = paciente;
        this.tecnicoDeSaude = tecnicoDeSaude;
    }
    public Data getDataRegisto() {
        return dataRegisto;
    }
    public void setDataRegisto(Data dataRegisto) {
        this.dataRegisto = dataRegisto;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Medida{");
        sb.append("dataRegisto=").append(dataRegisto);
        sb.append(", paciente=").append(paciente);
        sb.append(", tecnicoDeSaUDE=").append(tecnicoDeSaude);
        sb.append('}');
        return sb.toString();
    }
}
