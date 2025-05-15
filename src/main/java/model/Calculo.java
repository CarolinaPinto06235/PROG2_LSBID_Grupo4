package model;

/**
 * Interface que define métodos para monitorização de parâmetros clínicos.
 * Permite calcular estatísticas básicas como média, desvio padrão, valor mínimo e máximo
 * de um conjunto de valores clínicos (por exemplo: temperatura, frequência cardíaca, etc.).
 */

public interface Calculo {

    default double calcularMedia(double[] valores){
        if (valores == null || valores.length == 0) return 0;

        double soma = 0;
        for (double valor : valores) {
            soma += valor;
        }
        return soma / valores.length;
    }

    default double calcularDesvioPadrao(double[] valores) {
        if (valores == null || valores.length == 0) return 0;

        double media = calcularMedia(valores);
        double somaQuadrados = 0;
        for (double valor : valores) {
            somaQuadrados += Math.pow(valor - media, 2);
        }
        return Math.sqrt(somaQuadrados / valores.length);
    }

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

