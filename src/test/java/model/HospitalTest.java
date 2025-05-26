package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Data;

import static org.junit.jupiter.api.Assertions.*;

class HospitalTest {

    private Hospital hospital;
    private Paciente paciente1;
    private Paciente paciente2;
    private TecnicoDeSaude tecnico1;
    private TecnicoDeSaude tecnico2;

    @BeforeEach
    void setUp() {
        hospital = new Hospital("Hospital Teste");

        paciente1 = new Paciente(1, "Ana Magalhães", 'F', new Data(1990, 5, 20), new Data(2025, 5, 25));
        paciente2 = new Paciente(2, "Bruno Fernandes", 'M', new Data(1985, 8, 15), new Data(2025, 5, 25));

        tecnico1 = new TecnicoDeSaude(100, "Carlos Maia", 'M', new Data(1975, 3, 10), "Enfermeiro");
        tecnico2 = new TecnicoDeSaude(101, "Daniela Carvalho", 'F', new Data(1980, 7, 22), "Técnico");
    }

    @Test
    void testAdicionarPaciente() {
        assertTrue(hospital.adicionarPaciente(paciente1));
        assertFalse(hospital.adicionarPaciente(paciente1), "Não deve adicionar paciente com ID duplicado");
        assertTrue(hospital.adicionarPaciente(paciente2));
        assertEquals(2, hospital.getLstPacientes().size());
    }

    @Test
    void testAdicionarFreqCardiaca() {
        hospital.adicionarPaciente(paciente1);
        hospital.getLstTecnicos().add(tecnico1);

        assertTrue(hospital.adicionarFreqCardiaca(new Data(2025, 5, 25), 75, paciente1, tecnico1));
        assertFalse(hospital.adicionarFreqCardiaca(new Data(2025, 5, 25), 80, null, tecnico1), "Paciente null deve retornar false");
        assertFalse(hospital.adicionarFreqCardiaca(new Data(2025, 5, 25), 80, paciente1, null), "Tecnico null deve retornar false");
        assertEquals(1, hospital.getLstFreqCard().size());
    }

    @Test
    void testAdicionarSaturacaoOxigenio() {
        hospital.adicionarPaciente(paciente1);
        hospital.getLstTecnicos().add(tecnico1);

        assertTrue(hospital.adicionarSaturacaoOxigenio(new Data(2025, 5, 25), 95, paciente1, tecnico1));
        assertFalse(hospital.adicionarSaturacaoOxigenio(new Data(2025, 5, 25), 95, null, tecnico1));
        assertFalse(hospital.adicionarSaturacaoOxigenio(new Data(2025, 5, 25), 95, paciente1, null));
        assertEquals(1, hospital.getLstSaturacao().size());
    }

    @Test
    void testAdicionarTemperatura() {
        hospital.adicionarPaciente(paciente1);
        hospital.getLstTecnicos().add(tecnico1);

        assertTrue(hospital.adicionarTemperatura(new Data(2025, 5, 25), 36.5, paciente1, tecnico1));
        assertFalse(hospital.adicionarTemperatura(new Data(2025, 5, 25), 36.5, null, tecnico1));
        assertFalse(hospital.adicionarTemperatura(new Data(2025, 5, 25), 36.5, paciente1, null));
        assertEquals(1, hospital.getLstTemperatura().size());
    }

    @Test
    void testOrdenarPacientesPorDataNascimento() {
        hospital.adicionarPaciente(paciente2);
        hospital.adicionarPaciente(paciente1);

        hospital.ordenarPacientesPorDataNascimento();

        assertEquals(paciente2, hospital.getLstPacientes().get(0), "Paciente com mais idade deve vir primeiro");
        assertEquals(paciente1, hospital.getLstPacientes().get(1));
    }

    @Test
    void testOrdenarTecnicosPorNome() {
        hospital.getLstTecnicos().add(tecnico2);
        hospital.getLstTecnicos().add(tecnico1);

        hospital.ordenarTecnicosPorNome();

        assertEquals(tecnico1, hospital.getLstTecnicos().get(0), "Carlos (C) vem antes de Daniela (D) alfabeticamente");
        assertEquals(tecnico2, hospital.getLstTecnicos().get(1));
    }
}
