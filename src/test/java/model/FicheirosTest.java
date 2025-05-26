package model;

import org.junit.jupiter.api.*;
import utils.Data;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FicheirosTest {

    private Hospital hospital;
    private final String pacientesPath = "resources/Pacientes.txt";
    private final String tecnicosPath = "resources/TecnicosDeSaude.txt";
    private final String sinaisPath = "resources/Sinais_Vitais.txt";

    @BeforeEach
    void setUp() {
        hospital = new Hospital("Teste Hospital");
    }

    @Test
    void testGuardarPacientes() throws IOException {
        Paciente p = new Paciente(1, "Ana", 'F', new Data(1990, 1, 1), new Data(2024, 5, 26));
        hospital.adicionarPaciente(p);

        Ficheiros.guardarPacientes(hospital);

        List<String> linhas = Files.readAllLines(new File(pacientesPath).toPath());
        assertFalse(linhas.isEmpty());
        assertTrue(linhas.get(0).contains("Ana"));
    }

    @Test
    void testGuardarTecnicos() throws IOException {
        TecnicoDeSaude t = new TecnicoDeSaude(101, "Carlos", 'M', new Data(1985, 2, 2), "Enfermeiro");
        hospital.getLstTecnicos().add(t);

        Ficheiros.guardarTecnicos(hospital);

        List<String> linhas = Files.readAllLines(new File(tecnicosPath).toPath());
        assertFalse(linhas.isEmpty());
        assertTrue(linhas.get(0).contains("Carlos"));
    }

    @Test
    void testGuardarSinaisVitais() throws IOException {
        Paciente p = new Paciente(1, "Ana", 'F', new Data(1990, 1, 1), new Data(2024, 5, 26));
        TecnicoDeSaude t = new TecnicoDeSaude(101, "Carlos", 'M', new Data(1985, 2, 2), "Enfermeiro");

        hospital.adicionarPaciente(p);
        hospital.getLstTecnicos().add(t);
        hospital.adicionarTemperatura(new Data(2024, 5, 26), 36.5, p, t);
        hospital.adicionarFreqCardiaca(new Data(2024, 5, 26), 70, p, t);
        hospital.adicionarSaturacaoOxigenio(new Data(2024, 5, 26), 98, p, t);

        Ficheiros.guardarSinaisVitais(hospital);

        List<String> linhas = Files.readAllLines(new File(sinaisPath).toPath());
        assertEquals(3, linhas.size());
        assertTrue(linhas.get(0).contains("temperatura"));
        assertTrue(linhas.get(1).contains("frequência"));
        assertTrue(linhas.get(2).contains("saturação"));
    }

    @AfterEach
    void tearDown() throws IOException {
        // Limpa os ficheiros usados nos testes
        Files.write(new File(pacientesPath).toPath(), "".getBytes());
        Files.write(new File(tecnicosPath).toPath(), "".getBytes());
        Files.write(new File(sinaisPath).toPath(), "".getBytes());
    }
}