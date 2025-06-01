package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Utils {

    /**
     * Lê uma linha da consola após mostrar um prompt.
     *
     * @param strPrompt Texto a mostrar antes de ler a linha.
     * @return A linha lida do console como String, ou null se houver erro.
     */
    static public String readLineFromConsole(String strPrompt) {
        try {
            System.out.println(strPrompt);
            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);
            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Lê um número inteiro da consola com repetição em caso de erro.
     *
     * @param strPrompt Texto a mostrar antes de ler o inteiro.
     * @return O número inteiro lido do console.
     */
    public static int readIntFromConsole(String strPrompt) {
        do {
            try {
                String strInt = readLineFromConsole(strPrompt);
                int iInt = Integer.parseInt(strInt);
                return iInt;
            } catch (NumberFormatException ex) {
                //Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    /**
     * Lê um número decimal (double) do console com repetição em caso de erro.
     *
     * @param strPrompt Texto a mostrar antes de ler o double
     * @return O número double lido da consola.
     */
    public static double readDoubleFromConsole(String strPrompt) {
        do {
            try {
                String strDouble = readLineFromConsole(strPrompt);
                double iDouble = Double.parseDouble(strDouble);
                return iDouble;
            } catch (NumberFormatException ex) {
                //Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    /**
     * Solicita uma confirmação (sim ou não) ao utilizador.
     *
     * @param sMessage Mensagem a mostrar para solicitar confirmação.
     * @return true se o utilizador confirmar com 's' ou 'S', false caso contrário.
     */
    static public boolean confirma(String sMessage) {
        String strConfirma;
        do {
            strConfirma = Utils.readLineFromConsole(sMessage);
        } while (!strConfirma.equalsIgnoreCase("s") && !strConfirma.equalsIgnoreCase("n"));

        return strConfirma.equalsIgnoreCase("s");
    }

}
