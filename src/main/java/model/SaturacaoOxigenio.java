package model;

import utils.Data;

public class SaturacaoOxigenio extends Medida {
    private double saturacaoOxigenio;
    private static final double MIN_SO2 = 80.0;
    private static final double MAX_SO2 = 100.0;

    public SaturacaoOxigenio(Data dataRegisto, double saturacaoOxigenio, Paciente paciente, TecnicoDeSaude tecnicoDeSaude) {
        super(dataRegisto, paciente, tecnicoDeSaude);
        if (saturacaoOxigenio >= MIN_SO2 && saturacaoOxigenio <= MAX_SO2) {
            this.saturacaoOxigenio = saturacaoOxigenio;
        } else {
            System.out.println("Saturação de oxigénio fora dos limites válidos (80-100%). Insira um valor dentro dos limites válidos.");
            this.saturacaoOxigenio = MIN_SO2;
        }

        public double getSaturacaoOxigenio() {
            return saturacaoOxigenio;
        }

        public void setSaturacaoOxigenio (double saturacaoOxigenio){
            if (saturacaoOxigenio >= MIN_SO2 && saturacaoOxigenio <= MAX_SO2) {
                this.saturacaoOxigenio = saturacaoOxigenio;
            } else {
                System.out.println("Saturação de oxigénio fora dos limites válidos (80-100%). Insira um valor dentro dos limites válidos.");
            }
        }

        @Override
        public String toString () {
            final StringBuilder sb = new StringBuilder("SaturacaoOxigenio{");
            sb.append(super.toString());
            sb.append("saturacaoOxigenio=").append(saturacaoOxigenio);
            sb.append('}');
            return sb.toString();
        }
    }
}