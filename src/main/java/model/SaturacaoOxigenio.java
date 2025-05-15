package model;

import javax.xml.crypto.Data;

public class SaturacaoOxigenio extends Medida {
    private double saturacaoOxigenio;

    public SaturacaoOxigenio(Data dataRegisto, double saturacaoOxigenio, Paciente paciente, TecnicoDeSaude tecnicoDeSaude) {
        super(dataRegisto, paciente, tecnicoDeSaude);
        this.saturacaoOxigenio = saturacaoOxigenio;
    }

    public double getSaturacaoOxigenio() {
        return saturacaoOxigenio;
    }

    public void setSaturacaoOxigenio(double saturacaoOxigenio) {
        this.saturacaoOxigenio = saturacaoOxigenio;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SaturacaoOxigenio{");
        sb.append(super.toString());
        sb.append("saturacaoOxigenio=").append(saturacaoOxigenio);
        sb.append('}');
        return sb.toString();
    }
}
