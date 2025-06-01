package utils;

/**
 * Classe que representa uma data (ano, mês, dia)
 * Permite operações como comparação, cálculo de diferença, e formatação.
 *
 * @author mdm
 */
public class Data implements Comparable<Data> {

    private int ano;
    private int mes;
    private int dia;

    private static final int ANO_POR_OMISSAO = 1;
    private static final int MES_POR_OMISSAO = 1;
    private static final int DIA_POR_OMISSAO = 1;

    private static final String[] nomeDiaDaSemana = {
            "Domingo", "Segunda-feira", "Terça-feira", "Quarta-feira",
            "Quinta-feira", "Sexta-feira", "Sábado"
    };

    private static final int[] diasPorMes = {
            0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    private static final String[] nomeMes = {
            "Inválido", "Janeiro", "Fevereiro", "Março", "Abril", "Maio",
            "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
    };

    // Construtores

    public Data(int ano, int mes, int dia) {
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
    }
    public Data(Data outraData) {
        this.ano = outraData.ano;
        this.mes = outraData.mes;
        this.dia = outraData.dia;
    }

    public Data(String data) {
        data = data.trim();
        String[] partes = data.split("/");
        if (partes.length != 3) {
            System.out.println("Formato de data inválido. Use DD/MM/AAAA. Erro: " + data + ".");
            this.dia = DIA_POR_OMISSAO;
            this.mes = MES_POR_OMISSAO;
            this.ano = ANO_POR_OMISSAO;
        } else {
            this.dia = Integer.parseInt(partes[0]);
            this.mes = Integer.parseInt(partes[1]);
            this.ano = Integer.parseInt(partes[2]);
        }
    }

    public Data() {
        this.ano = ANO_POR_OMISSAO;
        this.mes = MES_POR_OMISSAO;
        this.dia = DIA_POR_OMISSAO;
    }

    // Getters

    public int getAno() {
        return ano;
    }

    public int getMes() {
        return mes;
    }

    public int getDia() {
        return dia;
    }

    // Setters

    public void setData(int ano, int mes, int dia) {
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
    }

    // Métodos auxiliares

    /**
     * Calcula o número total de dias desde 1/1/1 até esta data.
     */
    private int contarDias() {
        int totalDias = 0;

        for (int i = 1; i < ano; i++) {
            totalDias += isAnoBissexto(i) ? 366 : 365;
        }
        for (int i = 1; i < mes; i++) {
            totalDias += diasPorMes[i];
        }
        if (isAnoBissexto(ano) && mes > 2) {
            totalDias++;
        }
        totalDias += dia;

        return totalDias;
    }

    /**
     * Determina o dia da semana da data.
     * Usa o método contarDias() para calcular o índice do dia da semana.
     */
    public String determinarDiaDaSemana() {
        int totalDias = contarDias();
        int indice = totalDias % 7;
        return nomeDiaDaSemana[indice];
    }

    /**
     * Verifica se o ano é bissexto.
     */
    public static boolean isAnoBissexto(int ano) {
        return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
    }

    // Operações principais

    public boolean isMaior(Data outraData) {
        return this.contarDias() > outraData.contarDias();
    }

    public int calcularDiferenca(Data outraData) {
        return Math.abs(this.contarDias() - outraData.contarDias());
    }

    public int calcularDiferenca(int ano, int mes, int dia) {
        Data outraData = new Data(ano, mes, dia);
        return calcularDiferenca(outraData);
    }

    @Override
    public boolean equals(Object outroObjeto) {
        if (this == outroObjeto) return true;
        if (outroObjeto == null || getClass() != outroObjeto.getClass()) return false;

        Data other = (Data) outroObjeto;

        return this.ano == other.ano && this.mes == other.mes && this.dia == other.dia;
    }

    @Override
    public int compareTo(Data outraData) {
        if (this.equals(outraData)) return 0;
        return this.isMaior(outraData) ? 1 : -1;
    }

    @Override
    public String toString() {
        return determinarDiaDaSemana() + ", " + dia + " de " + nomeMes[mes] + " de " + ano;
    }

    public String toAnoMesDiaString() {
        return String.format("%04d/%02d/%02d", ano, mes, dia);
    }

    /**
     * Formato simples para guardar em ficheiros: dd/MM/yyyy
     */
    public String toFileString() {
        return String.format("%02d/%02d/%04d", dia, mes, ano);
    }

    /**
     * Constrói um objeto Data a partir de uma string no formato dd/MM/yyyy
     */
    public static Data fromFileString(String data) {
        String[] partes = data.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int ano = Integer.parseInt(partes[2]);
        return new Data(ano, mes, dia);
    }
}
