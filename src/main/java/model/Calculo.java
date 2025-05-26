package model;

/**
 * Interface que define métodos para monitorização de parâmetros clínicos.
 * Permite calcular estatísticas básicas como média, desvio padrão, valor mínimo e máximo
 * de um conjunto de valores clínicos (por exemplo: temperatura, frequência cardíaca, etc.).
 */

public interface Calculo {

    /**
     * Calcula a média de um array de valores.
     *
     * @param valores Array de valores numéricos.
     * @return Média dos valores, ou 0 se o array for nulo ou vazio.
     */
    default double calcularMedia(double[] valores){
        if (valores == null || valores.length == 0) return 0;

        double soma = 0;
        for (double valor : valores) {
            soma += valor;
        }
        return soma / valores.length;
    }

    /**
     * Calcula o desvio padrão de um array de valores.
     *
     * @param valores Array de valores numéricos.
     * @return Desvio padrão, ou 0 se o array for nulo ou vazio.
     */
    default double calcularDesvioPadrao(double[] valores) {
        if (valores == null || valores.length == 0) return 0;

        double media = calcularMedia(valores);
        double somaQuadrados = 0;
        for (double valor : valores) {
            somaQuadrados += Math.pow(valor - media, 2);
        }
        return Math.sqrt(somaQuadrados / valores.length);
    }

    /**
     * Retorna o valor mínimo de um array de valores.
     *
     * @param valores Array de valores numéricos.
     * @return Valor mínimo, ou 0 se o array for nulo ou vazio.
     */
    default double calcularMinimo(double[] valores) {
        if (valores == null || valores.length == 0) return 0;

        double minimo = valores[0];
        for (double valor : valores) {
            if (valor < minimo) {
                minimo = valor;
            }
        }
        return minimo;
    }

    /**
     * Retorna o valor máximo de um array de valores.
     *
     * @param valores Array de valores numéricos.
     * @return Valor máximo, ou 0 se o array for nulo ou vazio.
     */
    default double calcularMaximo(double[] valores) {
        if (valores == null || valores.length == 0) return 0;

        double maximo = valores[0];
        for (double valor : valores) {
            if (valor > maximo) {
                maximo = valor;
            }
        }
        return maximo;
    }
}

