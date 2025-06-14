package model;
import utils.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;


/**
 * Classe que representa um hospital, contendo listas de pacientes, técnicos de saúde
 * e registos de sinais vitais como frequência cardíaca, saturação de oxigénio e temperatura.
 */
public class Hospital {

    private String nome;
    private final List<Paciente> lstPacientes;
    private final List<FrequenciaCardiaca> lstFreqCard;
    private final List<SaturacaoOxigenio> lstSaturacao;
    private final List<Temperatura> lstTemperatura;
    private final List<TecnicoDeSaude> lstTecnicos;

    /**
     * Construtor do hospital.
     *
     * @param nome Nome do hospital.
     */
    public Hospital(String nome) {
        this.nome = nome;
        this.lstPacientes = new ArrayList<>();
        this.lstFreqCard = new ArrayList<>();
        this.lstSaturacao = new ArrayList<>();
        this.lstTemperatura = new ArrayList<>();
        this.lstTecnicos = new ArrayList<>();
    }

    /** @return Lista de pacientes registados no hospital. */
    public List<Paciente> getLstPacientes() {
        return lstPacientes;
    }

    /** @return Lista de pacientes registados no hospital. */
    public List<TecnicoDeSaude> getLstTecnicos() {
        return lstTecnicos;
    }

    /** @return Lista de registos de frequência cardíaca. */
    public List<FrequenciaCardiaca> getLstFreqCard() {
        return lstFreqCard;
    }

    /** @return Lista de registos de saturação de oxigénio. */
    public List<SaturacaoOxigenio> getLstSaturacao() {
        return lstSaturacao;
    }

    /** @return Lista de registos de temperatura. */
    public List<Temperatura> getLstTemperatura() {
        return lstTemperatura;
    }

    /**
     * Adiciona um novo paciente à lista se o ID não estiver já presente.
     *
     * @param paciente Paciente a adicionar.
     * @return true se foi adicionado com sucesso, false se o ID já existe.
     */
    public boolean adicionarPaciente(Paciente paciente) {
        for (Paciente p : lstPacientes) {
            if (p.getId() == paciente.getId()) {
                return false;
            }
        }
        lstPacientes.add(paciente);
        return true;
    }

    /**
     * Adiciona um registo de frequência cardíaca.
     *
     * @param dataRegisto Data do registo.
     * @param frequencia Valor da frequência cardíaca.
     * @param paciente Paciente a quem o registo pertence.
     * @param tecnicoDeSaude Técnico responsável.
     * @return true se foi adicionado com sucesso, false se dados inválidos.
     */
    public boolean adicionarFreqCardiaca(Data dataRegisto, double frequencia, Paciente paciente, TecnicoDeSaude tecnicoDeSaude) {
        if (paciente == null || tecnicoDeSaude == null)
            return false;
        FrequenciaCardiaca frequenciaCardiaca1 = new FrequenciaCardiaca(dataRegisto, frequencia, paciente, tecnicoDeSaude);
        return lstFreqCard.add(frequenciaCardiaca1);
    }

    /**
     * Adiciona um registo de saturação de oxigénio.
     *
     * @param dataRegisto Data do registo.
     * @param saturacaoOxigenio Valor da saturação de oxigénio.
     * @param paciente Paciente a quem o registo pertence.
     * @param tecnicoDeSaude Técnico responsável.
     * @return true se foi adicionado com sucesso, false se dados inválidos.
     */
    public boolean adicionarSaturacaoOxigenio(Data dataRegisto, double saturacaoOxigenio, Paciente paciente, TecnicoDeSaude tecnicoDeSaude) {
        if (paciente == null || tecnicoDeSaude == null)
            return false;
        SaturacaoOxigenio saturacaoOxigenio1 = new SaturacaoOxigenio(dataRegisto, saturacaoOxigenio, paciente, tecnicoDeSaude);
        return lstSaturacao.add(saturacaoOxigenio1);
    }

    /**
     * Adiciona um registo de temperatura.
     *
     * @param dataRegisto Data do registo.
     * @param temperatura Valor da temperatura.
     * @param paciente Paciente a quem o registo pertence.
     * @param tecnicoDeSaude Técnico responsável.
     * @return true se foi adicionado com sucesso, false se dados inválidos.
     */
    public boolean adicionarTemperatura(Data dataRegisto, double temperatura, Paciente paciente, TecnicoDeSaude tecnicoDeSaude) {
        if (paciente == null || tecnicoDeSaude == null)
            return false;
        Temperatura temperatura1 = new Temperatura(dataRegisto, temperatura, paciente, tecnicoDeSaude);
        return lstTemperatura.add(temperatura1);
    }

    /**
     * Menu para visualização de dados do hospital: pacientes, técnicos ou sinais vitais.
     */
    public void visualizarDados() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n------------ Visualizar dados: ------------");
            System.out.println("-------| 1. Pacientes                |-------");
            System.out.println("-------| 2. Técnicos de Saúde        |-------");
            System.out.println("-------| 3. Sinais Vitais            |-------");
            System.out.println("-------| 4. Todos                    |-------");
            System.out.println("-------| 0. Voltar ao menu principal |-------");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    mostrarPacientes();
                    break;
                case 2:
                    mostrarTecnicosSaude();
                    break;
                case 3:
                    mostrarSinaisVitais();
                    break;
                case 4:
                    mostrarPacientes();
                    mostrarTecnicosSaude();
                    mostrarSinaisVitais();
                    break;
                case 0:
                    System.out.println("A regressar ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);
    }

    /**
     * Menu para visualização de dados do hospital: pacientes, técnicos ou sinais vitais.
     */
    public void mostrarPacientes() {
        if (lstPacientes.isEmpty()) {
            System.out.println("Não há pacientes registados.");
        } else {
            System.out.println("\n------- Lista de Pacientes: -------\n");
            for (Paciente p : lstPacientes) {
                System.out.println(p);
            }
        }
    }

    /**
     * Mostra todos os técnicos de saúde registados.
     */
    public void mostrarTecnicosSaude() {
        if (lstTecnicos.isEmpty()) {
            System.out.println("Não há técnicos de saúde para mostrar.");
        } else {
            System.out.println("\n------- Lista de Técnicos de Saúde: -------\n");
            for (TecnicoDeSaude t : lstTecnicos) {
                System.out.println(t);
            }
        }
    }

    /**
     * Mostra todos os sinais vitais registados (frequência, saturação e temperatura).
     */
    public void mostrarSinaisVitais() {
        System.out.println("\n------- Frequências Cardíacas -------\n");
        for (FrequenciaCardiaca f : lstFreqCard) {
            System.out.println(f);
        }

        System.out.println("\n------- Saturações de Oxigénio -------\n");
        for (SaturacaoOxigenio s : lstSaturacao) {
            System.out.println(s);
        }

        System.out.println("\n------- Temperaturas -------\n");
        for (Temperatura t : lstTemperatura) {
            System.out.println(t);
        }
    }

    /**
     * Ordena os pacientes por data de nascimento (mais antigos primeiro).
     */
    public void ordenarPacientesPorDataNascimento() {
        if (lstPacientes.isEmpty()) {
            System.out.println("Não há pacientes para ordenar.");
            return;
        }

        lstPacientes.sort((p1, p2) -> p1.getDataNascimento().compareTo(p2.getDataNascimento()));

        System.out.println("Pacientes ordenados por data de nascimento:");
        mostrarPacientes();
    }

    /**
     * Ordena os técnicos de saúde por nome (ordem alfabética, sem distinguir maiúsculas de minúsculas).
     */
    public void ordenarTecnicosPorNome() {
        if (lstTecnicos.isEmpty()) {
            System.out.println("Não há técnicos para ordenar.");
            return;
        }

        lstTecnicos.sort(Comparator.comparing(TecnicoDeSaude::getNome, String.CASE_INSENSITIVE_ORDER));
        System.out.println("Técnicos de Saúde ordenados por nome:");
        mostrarTecnicosSaude();
    }
}



