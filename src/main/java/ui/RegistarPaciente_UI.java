package ui;
import model.Hospital;
import model.Paciente;
import utils.Data;
import utils.Utils;

public class RegistarPaciente_UI {
    private Hospital hospital;

    public RegistarPaciente_UI(Hospital hospital) {
        this.hospital = hospital;
    }

    public void run() {
        System.out.println("Novo Paciente.");

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

    private static Paciente introduzDados() {
        int id = Utils.readIntFromConsole("Introduza o ID do paciente: ");
        if (pacientes.stream().anyMatch(p -> p.getId() == id)) {
            System.out.println("Já existe um paciente com o ID " + id + ". Por favor, introduza um ID diferente.");
            return null;
        }

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

    private void adicionarMedicoes(Paciente paciente) {
        if (hospital.getLstTecnicos().isEmpty()) {
            System.out.println("Não existem técnicos registados. Não é possível adicionar medições.");
            return;
        }

        System.out.println("A adicionar medições dos sinais vitais para o paciente: " + paciente.getNome());

        Data dataRegisto = new Data(Utils.readLineFromConsole("Data da medição (DD/MM/AAAA): "));

        System.out.println("Selecione o técnico responsável:");
        for (int i = 0; i < hospital.getLstTecnicos().size(); i++) {
            System.out.println((i + 1) + ". " + hospital.getLstTecnicos().get(i).getNome());
        }
        int opcao = Utils.readIntFromConsole("Número do técnico: ") - 1;

        if (opcao < 0 || opcao >= hospital.getLstTecnicos().size()) {
            System.out.println("Técnico inválido. Medições não adicionadas.");
            return;
        }

        var tecnico = hospital.getLstTecnicos().get(opcao);

        double freqCard = Utils.readDoubleFromConsole("Frequência cardíaca (bpm): ");
        double saturacao = Utils.readDoubleFromConsole("Saturação de oxigénio (%): ");
        double temperatura = Utils.readDoubleFromConsole("Temperatura corporal (°C): ");

        hospital.adicionarFreqCardiaca(dataRegisto, freqCard, paciente, tecnico);
        hospital.adicionarSaturacaoOxigenio(dataRegisto, saturacao, paciente, tecnico);
        hospital.adicionarTemperatura(dataRegisto, temperatura, paciente, tecnico);

        System.out.println("Medições adicionadas com sucesso.");

    }
        private void apresentaDados(Paciente paciente) {
        System.out.println("Paciente: " + paciente.toString());
    }

}


