package ui;
import model.Hospital;
import model.Paciente;
import model.TecnicoDeSaude;
import utils.Data;
import utils.Utils;

public class RegistarPaciente_UI {
    private Hospital hospital;

    /**
     * Construtor que associa o registo de paciente a um hospital.
     * @param hospital Hospital onde serão adicionados os pacientes
     */
    public RegistarPaciente_UI(Hospital hospital) {
        this.hospital = hospital;
    }

    /**
     * Método principal para registar um paciente novo, validar os dados e adicionar medições.
     */
    public void run() {
        Paciente novoPaciente = introduzDados();
        apresentaDados(novoPaciente);

        if (Utils.confirma("Confirma os dados? (S/N)")) {
            if (hospital.adicionarPaciente(novoPaciente)) {
                System.out.println("Dados do paciente guardados com sucesso.");
                adicionarMedicoes(novoPaciente);
            } else {
                System.out.println("Não foi possível guardar os dados do paciente.");
            }
        }
    }

    /**
     * Lê os dados do paciente a partir da consola e cria um novo objeto Paciente.
     * @return Paciente criado com os dados introduzidos
     */
    private static Paciente introduzDados() {
        int id = Utils.readIntFromConsole("Introduza o ID do paciente: ");
        String nome = Utils.readLineFromConsole("Introduza o nome do paciente: ");

        String s;
        do {
            s = Utils.readLineFromConsole("Sexo (M/F): ").trim();
            if (!s.equalsIgnoreCase("M") && !s.equalsIgnoreCase("F")) {
                System.out.println("Sexo inválido. Insira M (masculino) ou F(feminino).");
            }
        } while (!s.equalsIgnoreCase("M") && !s.equalsIgnoreCase("F"));

        char sexo = s.toUpperCase().charAt(0);

        Data dataNascimento = new Data(Utils.readLineFromConsole("Data de nascimento (DD/MM/AAAA): "));
        Data dataInternamento = new Data(Utils.readLineFromConsole("Data de internamento (DD/MM/AAAA): "));
        return new Paciente(id,nome,sexo,dataNascimento, dataInternamento);
    }

    /**
     * Permite adicionar medições dos sinais vitais para um paciente, solicitando técnico responsável e valores.
     * @param paciente Paciente para o qual as medições serão adicionadas
     */
    private void adicionarMedicoes(Paciente paciente) {
        if (hospital.getLstTecnicos().isEmpty()) {
            System.out.println("Não existem técnicos registados. Não é possível adicionar medições.");
            return;
        }

        System.out.println("A adicionar medições dos sinais vitais para o paciente: " + paciente.getNome());

        Data dataRegisto = new Data(Utils.readLineFromConsole("Data da medição (DD/MM/AAAA): "));

        // Frequência Cardíaca
        System.out.println("Selecione o técnico responsável pela frequência cardíaca:");
        mostrarTecnicos();
        int opcaoFreq = Utils.readIntFromConsole("Número do técnico: ") - 1;
        if (!validarTecnico(opcaoFreq)) return;
        TecnicoDeSaude tecnicoFreq = hospital.getLstTecnicos().get(opcaoFreq);
        double freqCard = Utils.readDoubleFromConsole("Frequência cardíaca (bpm): ");
        hospital.adicionarFreqCardiaca(dataRegisto, freqCard, paciente, tecnicoFreq);

        // Saturação de Oxigénio
        System.out.println("Selecione o técnico responsável pela saturação de oxigénio:");
        mostrarTecnicos();
        int opcaoSaturacao = Utils.readIntFromConsole("Número do técnico: ") - 1;
        if (!validarTecnico(opcaoSaturacao)) return;
        TecnicoDeSaude tecnicoSaturacao = hospital.getLstTecnicos().get(opcaoSaturacao);
        double saturacao = Utils.readDoubleFromConsole("Saturação de oxigénio (%): ");
        hospital.adicionarSaturacaoOxigenio(dataRegisto, saturacao, paciente, tecnicoSaturacao);

        // Temperatura
        System.out.println("Selecione o técnico responsável pela temperatura:");
        mostrarTecnicos();
        int opcaoTemp = Utils.readIntFromConsole("Número do técnico: ") - 1;
        if (!validarTecnico(opcaoTemp)) return;
        TecnicoDeSaude tecnicoTemp = hospital.getLstTecnicos().get(opcaoTemp);
        double temperatura = Utils.readDoubleFromConsole("Temperatura (°C): ");
        hospital.adicionarTemperatura(dataRegisto, temperatura, paciente, tecnicoTemp);

        System.out.println("Medições adicionadas com sucesso.");
    }


    /**
     * Exibe a lista dos técnicos de saúde registados no hospital.
     */
    private void mostrarTecnicos() {
        for (int i = 0; i < hospital.getLstTecnicos().size(); i++) {
            System.out.println((i + 1) + ". " + hospital.getLstTecnicos().get(i).getNome());
        }
    }

    /**
     * Valida se o índice do técnico selecionado está dentro do intervalo válido.
     * @param index índice do técnico selecionado.
     * @return true se válido, false caso contrário.
     */
    private boolean validarTecnico(int index) {
        if (index < 0 || index >= hospital.getLstTecnicos().size()) {
            System.out.println("Técnico inválido. Medições não adicionadas.");
            return false;
        }
        return true;
    }

    /**
     * Apresenta os dados do paciente no ecrã.
     * @param paciente Paciente cujos dados serão apresentados.
     */
        private void apresentaDados(Paciente paciente) {
        System.out.println("Paciente: " + paciente.toString());
    }

}


