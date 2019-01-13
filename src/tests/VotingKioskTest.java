package tests;
import static org.junit.jupiter.api.Assertions.*;

import data.MailAddress;
import data.Nif;
import data.Party;
import exceptions.CantVoteException;
import exceptions.IncorrectNifException;
import exceptions.NoPartyException;
import kiosk.VotingKiosk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

public class VotingKioskTest {

    private Party votedParty;
    private VotingKiosk votingKiosk;
    private ElectoralOrganismSpy eO;

    @BeforeEach
    void set() {
        this.votedParty = new Party("ERC");
        Party anotherParty = new Party("VOX");

        this.votingKiosk = new VotingKiosk(new HashSet<>(Arrays.asList(votedParty,anotherParty)));
        this.eO = new ElectoralOrganismSpy();
        this.votingKiosk.setElectoralOrganism(eO);
    }

    @Test
    void votedSuccessfully() throws NoPartyException, CantVoteException, IncorrectNifException {
        votingKiosk.setNif(new Nif("48250721X"));
        votingKiosk.vote(votedParty);
        assertTrue(votingKiosk.votedSuccessfully);
    }

    @Test
    void voteNonExitingParty() throws IncorrectNifException {
        votingKiosk.setNif(new Nif("48250721X"));
        Party nonExistingParty = new Party("Perico");
        assertThrows(NoPartyException.class, () -> votingKiosk.vote(nonExistingParty));
    }

    @Test
    void voteWithTheSameNif() throws NoPartyException, CantVoteException, IncorrectNifException {
        votingKiosk.setNif(new Nif("48250721X"));
        votingKiosk.vote(votedParty);
        assertThrows(CantVoteException.class, () -> votingKiosk.vote(votedParty));
    }

    @Test
    void sendReceiptTest() {
        MailerServiceSpy mss = new MailerServiceSpy();
        votingKiosk.setMailerService(mss);
        votingKiosk.setPartyToSign(new Party("Miquel's potatoes"));

        MailAddress mailAddress = new MailAddress("mjs2@alumnes.udl.cat");
        votingKiosk.sendeReceipt(mailAddress);
        assertTrue(votingKiosk.receiptSent);
    }
}
