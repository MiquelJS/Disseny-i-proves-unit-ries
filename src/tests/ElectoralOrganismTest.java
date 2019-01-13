package tests;

import data.Nif;
import data.Party;
import exceptions.IncorrectNifException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ElectoralOrganismTest {

    private ElectoralOrganismSpy eO;

    @BeforeEach
    void init() {
        this.eO = new ElectoralOrganismSpy();
    }

    @Test
    void disableVoterTest() throws IncorrectNifException {
        Nif nif = new Nif("48250721X");
        eO.disableVoter(nif);
        assertTrue(eO.voted.contains(nif));
    }

    @Test
    void canVoteTest() throws IncorrectNifException {
        assertTrue(eO.canVote(new Nif("48250721X")));
    }

    @Test
    void cantVoteTest() throws IncorrectNifException {
        Nif nif = new Nif("48250721X");
        eO.disableVoter(nif);
        assertFalse(eO.canVote(nif));
    }

    @Test
    void askForDigitalSignatureTest() {
        Party party = new Party("Cs");
        eO.askForDigitalSignature(party);
        assertEquals("Digital Signature for Cs", eO.digitalSignature);
    }
}
