package model;

import java.util.List;

public class Estatisticas implements Calculo {

    /**
     * Calcula e exibe estatísticas básicas (média, desvio padrão, mínimo, máximo)
     * para as medições de frequência cardíaca, temperatura e saturação de oxigênio.
     *
     * @param frequencias lista de valores de frequência cardíaca
     * @param temperaturas lista de valores de temperatura
     * @param saturacoes lista de valores de saturação de oxigênio
     */
    public void calcularEstatisticas(List<Double> frequencias, List<Double> temperaturas, List<Double> saturacoes) {
        double[] freqArray = listToArray(frequencias);
        double[] tempArray = listToArray(temperaturas);
        double[] satArray = listToArray(saturacoes);

        System.out.println("Frequência Cardíaca - Média: " + calcularMedia(freqArray)
                + ", Desvio Padrão: " + calcularDesvioPadrao(freqArray)
                + ", Mínimo: " + calcularMinimo(freqArray)
                + ", Máximo: " + calcularMaximo(freqArray));

        System.out.println("Temperatura - Média: " + calcularMedia(tempArray)
                + ", Desvio Padrão: " + calcularDesvioPadrao(tempArray)
                + ", Mínimo: " + calcularMinimo(tempArray)
                + ", Máximo: " + calcularMaximo(tempArray));

        System.out.println("Saturação de Oxigênio - Média: " + calcularMedia(satArray)
                + ", Desvio Padrão: " + calcularDesvioPadrao(satArray)
                + ", Mínimo: " + calcularMinimo(satArray)
                + ", Máximo: " + calcularMaximo(satArray));
    }

    // Método auxiliar para converter List<Double> em double[]
    private double[] listToArray(List<Double> lista) {
        double[] array = new double[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            array[i] = lista.get(i);
        }
        return array;
    }
}

