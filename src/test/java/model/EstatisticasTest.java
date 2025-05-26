package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstatisticasTest {

    Estatisticas estatisticas = new Estatisticas();

    @Test
    void testPontuarTemperatura() {
        assertEquals(5, estatisticas.pontuarTemperatura(34.0));
        assertEquals(4, estatisticas.pontuarTemperatura(39.5));
        assertEquals(3, estatisticas.pontuarTemperatura(38.5));
        assertEquals(1, estatisticas.pontuarTemperatura(36.5));
        assertEquals(2, estatisticas.pontuarTemperatura(35.5));
    }

    @Test
    void testPontuarFrequencia() {
        assertEquals(5, estatisticas.pontuarFrequencia(30));
        assertEquals(4, estatisticas.pontuarFrequencia(45));
        assertEquals(3, estatisticas.pontuarFrequencia(110));
        assertEquals(1, estatisticas.pontuarFrequencia(75));
    }

    @Test
    void testPontuarSaturacao() {
        assertEquals(5, estatisticas.pontuarSaturacao(80));
        assertEquals(4, estatisticas.pontuarSaturacao(88));
        assertEquals(3, estatisticas.pontuarSaturacao(91));
        assertEquals(2, estatisticas.pontuarSaturacao(93));
        assertEquals(1, estatisticas.pontuarSaturacao(98));
    }

    @Test
    void testIsCriticoPaciente() {
        // Arrange
        Estatisticas estatisticas = new Estatisticas();
        Hospital hospital = new Hospital("Teste");

        Paciente paciente = new Paciente(1, "João Pereira", 'M', new utils.Data(2000, 1, 1), new utils.Data(2024, 5, 25));
        TecnicoDeSaude tecnico = new TecnicoDeSaude(101, "Manuel Silva", 'M', new utils.Data(1980, 6, 1), "Enfermeiro");

        hospital.adicionarPaciente(paciente);
        hospital.getLstTecnicos().add(tecnico);

        hospital.adicionarTemperatura(new utils.Data(2024, 5, 25), 36.5, paciente, tecnico);
        hospital.adicionarFreqCardiaca(new utils.Data(2024, 5, 25), 75, paciente, tecnico);
        hospital.adicionarSaturacaoOxigenio(new utils.Data(2024, 5, 25), 95, paciente, tecnico);

        assertFalse(estatisticas.isCritico(paciente, hospital), "Paciente não deveria ser crítico");

        hospital.adicionarTemperatura(new utils.Data(2024, 5, 26), 34.0, paciente, tecnico);
        assertTrue(estatisticas.isCritico(paciente, hospital), "Paciente deveria ser crítico");
    }

}
