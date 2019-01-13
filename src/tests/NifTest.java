package tests;

import data.Nif;
import exceptions.IncorrectNifException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NifTest {

    @Test
    void correctNifTest() throws IncorrectNifException {
        Nif nif = new Nif("48250721X");
        assertTrue(nif.correctNif);
    }

    @Test
    void incorrectNifTest() {
        assertThrows(IncorrectNifException.class, () -> new Nif("123"));
    }
}
