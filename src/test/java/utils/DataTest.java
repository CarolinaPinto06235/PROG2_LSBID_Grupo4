package utils;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DataTest {

    @Test
    public void testConstrutorComStringValida() {
        Data d = new Data("15/08/2023");
        assertEquals(15, d.getDia());
        assertEquals(8, d.getMes());
        assertEquals(2023, d.getAno());
    }

    @Test
    public void testConstrutorComStringInvalida() {
        Data d = new Data("invalida");
        assertEquals(1, d.getDia());
        assertEquals(1, d.getMes());
        assertEquals(1, d.getAno());
    }

    @Test
    public void testIsAnoBissexto() {
        assertTrue(Data.isAnoBissexto(2020));
        assertFalse(Data.isAnoBissexto(2019));
        assertFalse(Data.isAnoBissexto(1900));
        assertTrue(Data.isAnoBissexto(2000));
    }

    @Test
    public void testEqualsEPersistencia() {
        Data d1 = new Data(2023, 5, 10);
        Data d2 = new Data(2023, 5, 10);
        Data d3 = new Data(2024, 5, 10);

        assertEquals(d1, d2);
        assertNotEquals(d1, d3);
    }

    @Test
    public void testCompareTo() {
        Data d1 = new Data(2023, 5, 10);
        Data d2 = new Data(2023, 5, 11);
        Data d3 = new Data(2023, 5, 10);

        assertTrue(d1.compareTo(d2) < 0);
        assertTrue(d2.compareTo(d1) > 0);
        assertEquals(0, d1.compareTo(d3));
    }

    @Test
    public void testCalcularDiferenca() {
        Data d1 = new Data(2023, 5, 10);
        Data d2 = new Data(2023, 5, 15);

        assertEquals(5, d1.calcularDiferenca(d2));
        assertEquals(5, d2.calcularDiferenca(d1));
        assertEquals(0, d1.calcularDiferenca(d1));
        assertEquals(5, d1.calcularDiferenca(2023, 5, 15));
    }

    @Test
    public void testDeterminarDiaDaSemana() {
        Data d = new Data(2023, 8, 15);
        assertEquals("Terça-feira", d.determinarDiaDaSemana());

        Data d2 = new Data(1, 1, 1);
        assertEquals("Domingo", d2.determinarDiaDaSemana());
    }

    @Test
    public void testToString() {
        Data d = new Data(2023, 8, 15);
        String s = d.toString();
        assertTrue(s.contains("Terça-feira"));
        assertTrue(s.contains("15"));
        assertTrue(s.contains("Agosto"));
        assertTrue(s.contains("2023"));
    }

    @Test
    public void testToFileStringEFromFileString() {
        Data d = new Data(2023, 8, 15);
        String fileStr = d.toFileString();
        assertEquals("15/08/2023", fileStr);

        Data d2 = Data.fromFileString(fileStr);
        assertEquals(d, d2);
    }
}
