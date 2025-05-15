package model;

import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private String nome;

    private final List<Paciente> lstPacientes;
    private final List<FrequenciaCardiaca> lstFreqCard;
    private final List<SaturacaoOxigenio> lstSaturacao;
    private final List<Temperatura> lstTemperatura;
    private final List<TecnicoDeSaude> lstTecnicos;
    // Completar

    public Hospital(String nome) {
        this.nome = nome;
        this.lstPacientes = new ArrayList<>();
        this.lstFreqCard = new ArrayList<>();
        this.lstSaturacao = new ArrayList<>();
        this.lstTemperatura = new ArrayList<>();
        this.lstTecnicos = new ArrayList<>();
    }
    // A completar
    public boolean adicionarPaciente(Paciente paciente) {
        // completar
        return false;
    }
    public boolean adicionarFreqCardiaca(Data dataRegisto, double frequencia, Paciente paciente, TecnicoDeSaude tecnicoSaude) {
        //.....
        return false;
    }
    // Alternativa
    public boolean adicionarFreqCardiaca1(Data dataRegisto, double frequencia, int idPaciente, int idTecnicoDeSaude) {
        //Paciente p = procurarPaciente(idPaciente);
        //TecnicoPaciente t = procurarTecnico(idTecnico);
        //....
        return false;
    }

    public boolean listaContemPaciente(int id) {
        for(Paciente paciente : lstPacientes){
            if(paciente.getId()==id){
                return true;
            }
        }
        return false;
    }

// Completar com outras funcionalidades



