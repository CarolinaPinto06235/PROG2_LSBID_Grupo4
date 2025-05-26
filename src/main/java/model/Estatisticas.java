package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável por calcular estatísticas e scores relacionados à saúde dos pacientes.
 */
public class Estatisticas implements Calculo {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Classe responsável por calcular estatísticas e scores relacionados à saúde dos pacientes.
     */
    public void mostrarMenu(Hospital hospital) {
        System.out.println("\n------- Estatísticas -------");
        System.out.println("1. Estatísticas de um grupo de pacientes. ");
        System.out.println("2. Estatísticas de todos os pacientes. ");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> calcularEstatisticasGrupo(hospital);
            case 2 -> calcularEstatisticas(hospital);
            default -> System.out.println("Opção inválida.");
        }
    }

    /**
     * Calcula e exibe estatísticas de todos os pacientes.
     *
     * @param hospital Instância do hospital com os dados.
     */
    public void calcularEstatisticas(Hospital hospital) {
        double[] freqArray = extrairFrequencias(hospital);
        double[] tempArray = extrairTemperaturas(hospital);
        double[] satArray = extrairSaturacoes(hospital);

        mostrarEstatisticas(freqArray, tempArray, satArray);
    }

    /**
     * Calcula e exibe estatísticas de um grupo específico de pacientes.
     *
     * @param hospital Instância do hospital com os dados.
     */
    public void calcularEstatisticasGrupo(Hospital hospital) {
        System.out.print("Digite os IDs dos pacientes que deseja selecionar, separados por vírgula: ");
        String linha = scanner.nextLine();
        String[] idTexto = linha.split(",");

        List<Double> freqList = new ArrayList<>();
        List<Double> tempList = new ArrayList<>();
        List<Double> satList = new ArrayList<>();
        List<String> nomesGrupo = new ArrayList<>();

        for (int i = 0; i < idTexto.length; i++) {
            String texto = idTexto[i];
            texto = texto.trim();

            if (texto.length() > 0) {
                int ID = Integer.parseInt(texto);

                Paciente paciente = null;
                for (Paciente p : hospital.getLstPacientes()) {
                    if (p.getId() == ID) {
                        paciente = p;
                        nomesGrupo.add(p.getNome());
                        break;
                    }
                }

                if (paciente == null) {
                    System.out.println("Paciente com ID " + ID + " não encontrado.");
                    continue;
                }

                for (FrequenciaCardiaca f : hospital.getLstFreqCard()) {
                    if (f.getIDpaciente() == ID) {
                        freqList.add(f.getFrequenciaCardiaca());
                    }
                }


                for (Temperatura t : hospital.getLstTemperatura()) {
                    if (t.getIDpaciente() == ID) {
                        tempList.add(t.getTemperatura());
                    }
                }

                for (SaturacaoOxigenio s : hospital.getLstSaturacao()) {
                    if (s.getIDpaciente() == ID) {
                        satList.add(s.getSaturacaoOxigenio());
                    }
                }
            }
        }

        double[] freqArray = listToArray(freqList);
        double[] tempArray = listToArray(tempList);
        double[] satArray = listToArray(satList);

        mostrarEstatisticas(freqArray, tempArray, satArray);

        System.out.println("\nPacientes incluídos no grupo: ");
        for (String nome : nomesGrupo) {
            System.out.println((" - " + nome));
        }
    }

    /**
     * Exibe estatísticas (média, desvio padrão, mínimo e máximo) dos sinais vitais.
     *
     * @param freq Array de frequências cardíacas.
     * @param temp Array de temperaturas.
     * @param sat  Array de saturações de oxigênio.
     */
    private void mostrarEstatisticas(double[] freq, double[] temp, double[] sat) {

        System.out.println("\n-------Estatísticas dos Sinais Vitais -------\n");

        System.out.println("Frequência Cardíaca - Média: " + calcularMedia(freq)
                + ", Desvio Padrão: " + calcularDesvioPadrao(freq)
                + ", Mínimo: " + calcularMinimo(freq)
                + ", Máximo: " + calcularMaximo(freq));

        System.out.println("Temperatura - Média: " + calcularMedia(temp)
                + ", Desvio Padrão: " + calcularDesvioPadrao(temp)
                + ", Mínimo: " + calcularMinimo(temp)
                + ", Máximo: " + calcularMaximo(temp));

        System.out.println("Saturação de Oxigénio - Média: " + calcularMedia(sat)
                + ", Desvio Padrão: " + calcularDesvioPadrao(sat)
                + ", Mínimo: " + calcularMinimo(sat)
                + ", Máximo: " + calcularMaximo(sat));
    }

    /**
     * Extrai os valores de frequência cardíaca de todos os pacientes.
     *
     * @param hospital Instância do hospital.
     * @return Array de frequências cardíacas.
     */
    private double[] extrairFrequencias(Hospital hospital) {
        List<Double> lista = new ArrayList<>();
        for (FrequenciaCardiaca f : hospital.getLstFreqCard()) {
            lista.add(f.getFrequenciaCardiaca());
        }
        return listToArray(lista);
    }

    /**
     * Extrai os valores de temperatura de todos os pacientes.
     *
     * @param hospital Instância do hospital.
     * @return Array de temperaturas.
     */
    private double[] extrairTemperaturas(Hospital hospital) {
        List<Double> lista = new ArrayList<>();
        for (Temperatura t : hospital.getLstTemperatura()) {
            lista.add(t.getTemperatura());
        }
        return listToArray(lista);
    }

    /**
     * Extrai os valores de saturação de oxigênio de todos os pacientes.
     *
     * @param hospital Instância do hospital.
     * @return Array de saturações de oxigênio.
     */
    private double[] extrairSaturacoes(Hospital hospital) {
        List<Double> lista = new ArrayList<>();
        for (SaturacaoOxigenio s : hospital.getLstSaturacao()) {
            lista.add(s.getSaturacaoOxigenio());
        }
        return listToArray(lista);
    }

    /**
     * Converte uma lista de Double para um array de double.
     *
     * @param lista Lista a ser convertida.
     * @return Array equivalente.
     */
    private double[] listToArray(List<Double> lista) {
        double[] array = new double[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            array[i] = lista.get(i);
        }
        return array;
    }

    /**
     * Calcula e exibe o score de gravidade de um paciente com base nos sinais vitais.
     *
     * @param hospital Instância do hospital com os dados.
     */
    public void calcularScoreGravidade(Hospital hospital) {

        List<Paciente> lstPacientes = hospital.getLstPacientes();
        List<Temperatura> lstTemperaturas = hospital.getLstTemperatura();
        List<FrequenciaCardiaca> lstFrequencias = hospital.getLstFreqCard();
        List<SaturacaoOxigenio> lstSaturacoes = hospital.getLstSaturacao();

        if (lstPacientes.isEmpty()) {
            System.out.println("Não há pacientes registados.");
            return;
        }

        System.out.println("Lista de pacientes:");
        for (int i = 0; i < lstPacientes.size(); i++) {
            Paciente paciente = lstPacientes.get(i);
            System.out.println("ID: " + paciente.getId() + " - " + paciente.getNome());
        }

        System.out.print("Digite o ID do paciente: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        Paciente p = lstPacientes.stream()
                .filter(paciente -> paciente.getId() == opcao)
                .findFirst()
                .orElse(null);

        if (p == null) {
            System.out.println("Paciente com o ID indicado não foi encontrado.");
            return;
        }

        int IDpaciente = p.getId();

        Double temp = null;
        Double freq = null;
        Double spo2 = null;

        for (int i = lstTemperaturas.size() - 1; i >= 0; i--) {
            if (lstTemperaturas.get(i).getIDpaciente() == IDpaciente) {
                temp = lstTemperaturas.get(i).getTemperatura();
                break;
            }
        }

        for (int i = lstFrequencias.size() - 1; i >= 0; i--) {
            if (lstFrequencias.get(i).getIDpaciente() == IDpaciente) {
                freq = lstFrequencias.get(i).getFrequenciaCardiaca();
                break;
            }
        }

        for (int i = lstSaturacoes.size() - 1; i >= 0; i--) {
            if (lstSaturacoes.get(i).getIDpaciente() == IDpaciente) {
                spo2 = lstSaturacoes.get(i).getSaturacaoOxigenio();
                break;
            }
        }

        if (temp == null || freq == null || spo2 == null) {
            System.out.println("Não foram registadas medições para este paciente.");
            return;
        }

        int tempScore = pontuarTemperatura(temp);
        int freqScore = pontuarFrequencia(freq);
        int spo2Score = pontuarSaturacao(spo2);

        double scoreFinal = (freqScore * 0.3) + (tempScore * 0.4) + (spo2Score * 0.3);

        System.out.println("\nScore de Gravidade do paciente \"" + p.getNome() + "\": " + String.format("%.2f", scoreFinal));

        if (scoreFinal <= 2) {
            System.out.println("Gravidade Baixa");
        } else if (scoreFinal <= 3.5) {
            System.out.println("Gravidade Moderada");
        } else {
            System.out.println("Gravidade Alta");
        }
    }

    /**
     * Retorna a pontuação com base na temperatura corporal.
     *
     * @param t Temperatura.
     * @return Score correspondente.
     */
    int pontuarTemperatura(double t) {
        if (t < 35.0 || t > 40.0) return 5;
        if (t > 39.0) return 4;
        if (t > 38.0) return 3;
        if (t >= 36.0) return 1;
        return 2;
    }

    /**
     * Retorna a pontuação com base na frequência cardíaca.
     *
     * @param f Frequência cardíaca.
     * @return Score correspondente.
     */
    int pontuarFrequencia(double f) {
        if (f < 40 || f > 140) return 5;
        if ((f >= 121 && f <= 140) || (f >= 40 && f <= 49)) return 4;
        if ((f >= 101 && f <= 120) || (f >= 50 && f <= 59)) return 3;
        return 1;
    }

    /**
     * Retorna a pontuação com base na saturação de oxigênio.
     *
     * @param s Saturação.
     * @return Score correspondente.
     */
    int pontuarSaturacao(double s) {
        if (s < 85) return 5;
        if (s < 90) return 4;
        if (s <= 92) return 3;
        if (s <= 94) return 2;
        return 1;
    }

    /**
     * Verifica se um paciente é considerado crítico com base em seus sinais vitais.
     *
     * @param p        Paciente.
     * @param hospital Instância do hospital.
     * @return Verdadeiro se o paciente for crítico.
     */
    public boolean isCritico(Paciente p, Hospital hospital) {
        double temp = hospital.getLstTemperatura().stream()
                .filter(t -> t.getIDpaciente() == p.getId())
                .mapToDouble(Temperatura::getTemperatura)
                .max().orElse(0);

        double freq = hospital.getLstFreqCard().stream()
                .filter(f -> f.getIDpaciente() == p.getId())
                .mapToDouble(FrequenciaCardiaca::getFrequenciaCardiaca)
                .max().orElse(0);

        double sat = hospital.getLstSaturacao().stream()
                .filter(s -> s.getIDpaciente() == p.getId())
                .mapToDouble(SaturacaoOxigenio::getSaturacaoOxigenio)
                .max().orElse(100);

        return (temp < 35 || temp > 38) || (freq < 50 || freq > 120) || (sat < 90);
    }

    /**
     * Exibe um gráfico de barras textual dos sinais vitais de um paciente.
     *
     * @param hospital Instância do hospital com os dados.
     */
    public void mostrarGraficoBarras(Hospital hospital) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira o Id do paciente que deseja para visualizar o gráfico de barras: ");
        int IDselecionado = scanner.nextInt();

        List<Paciente> pacientes = hospital.getLstPacientes();
        Paciente pacienteSelecionado = null;

        for (Paciente p : pacientes) {
            if (p.getId() == IDselecionado) {
                pacienteSelecionado = p;
                break;
            }
        }

        if (pacienteSelecionado == null) {
            System.out.println("Paciente com ID " + IDselecionado + " não encontrado.");
            return;
        }

        System.out.println("\n-------  Gráfico de Barras  -------\n");
        System.out.println("Paciente: " + pacienteSelecionado.getNome());

        double freq = -1;
        double temp = -1;
        double sat = -1;
        int id = pacienteSelecionado.getId();

        List<FrequenciaCardiaca> listaFreq = hospital.getLstFreqCard();
        List<Temperatura> listaTemp = hospital.getLstTemperatura();
        List<SaturacaoOxigenio> listaSat = hospital.getLstSaturacao();

        for (FrequenciaCardiaca f : listaFreq) {
            if (f.getIDpaciente() == id) {
                freq = f.getFrequenciaCardiaca();
                break;
            }
        }

        for (Temperatura t : listaTemp) {
            if (t.getIDpaciente() == id) {
                temp = t.getTemperatura();
                break;
            }
        }

        for (SaturacaoOxigenio s : listaSat) {
            if (s.getIDpaciente() == id) {
                sat = s.getSaturacaoOxigenio();
                break;
            }
        }

        if (freq != -1) {
            System.out.print("Frequência Cardíaca: ");
            desenharBarra(freq);
        }

        if (temp != -1) {
            System.out.print("Temperatura: ");
            desenharBarra(temp);
        }

        if (sat != -1) {
            System.out.print("Saturação de Oxigénio: ");
            desenharBarra(sat);
        }

        System.out.println();
    }

    /**
     * Desenha uma barra proporcional ao valor informado.
     *
     * @param valor Valor para representar com a barra.
     */
    private void desenharBarra(double valor) {
        int numAsteriscos = (int) (valor / 10);
        for (int i = 0; i < numAsteriscos; i++) {
            System.out.print("*");
        }
        System.out.println(" (" + valor + ")");
    }

    /**
     * Calcula e exibe a percentagem de pacientes considerados críticos.
     *
     * @param hospital Instância do hospital.
     */
    public void mostrarPercentagemCriticos(Hospital hospital) {
        List<Paciente> pacientes = hospital.getLstPacientes();

        if (pacientes.isEmpty()) {
            System.out.println("Não há pacientes registados.");
            return;
        }

        int criticos = 0;
        for (Paciente p : pacientes) {
            if (isCritico(p, hospital)) {
                criticos++;
            }
        }

        double percentagem = (criticos * 100.0) / pacientes.size();
        System.out.printf("Percentagem de pacientes críticos: %.2f%% (%d de %d)%n",
                percentagem, criticos, pacientes.size());
    }
}

