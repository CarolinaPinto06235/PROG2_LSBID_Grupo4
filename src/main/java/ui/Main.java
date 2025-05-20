package ui;

import model.Hospital;

/**
 * Classe principal do programa.
 * Responsável por executar o sistema de gestão de pacientes e técnicos de saúde numa UCI,
 * incluindo registo de dados, estatísticas e manipulação de ficheiros.
 */

public class Main {
    public static void main(String[] args) {
        try {

            Hospital h = new Hospital("Hospital ICAFRA");
            System.out.println(h);

            MenuUI uiMenu = new MenuUI(h);
            uiMenu.run();

            System.out.println(h);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
