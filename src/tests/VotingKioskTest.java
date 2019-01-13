package tests;
import static org.junit.jupiter.api.Assertions.*;

import data.Nif;
import data.Party;
import exceptions.NoPartyException;
import kiosk.VoteCounter;
import kiosk.VotingKiosk;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class VotingKioskTest {

    @Test
    void voteTest() throws NoPartyException {

        Party votedParty = new Party("VOX");
        Party anotherParty = new Party("ERC");
        Party nonExistingParty = new Party("PACMA");

        VotingKiosk votingKiosk = new VotingKiosk(new HashSet<>(Arrays.asList(votedParty,anotherParty)));
        votingKiosk.setElectoralOrganism(new ElectoralOrganismSpy());

        votingKiosk.setNif(new Nif("48250721X"));
        votingKiosk.vote(votedParty);
        votingKiosk.setNif(new Nif("12345678A"));
        votingKiosk.vote(new Party(""));
        votingKiosk.setNif(new Nif("98765432Q"));
        votingKiosk.vote(new Party("null"));
        votingKiosk.setNif(new Nif("44444444A"));
        votingKiosk.vote(new Party("null"));

        VoteCounter voteCounter = votingKiosk.voteCounter;

        // Case when the vote is scrutinized correctly
        assertEquals(1,voteCounter.getVotesFor(votedParty));

        // Case when a party is not voted
        assertEquals(0,voteCounter.getVotesFor(anotherParty));

        // Case when vote is blank
        assertEquals(1, voteCounter.getBlanks());

        // Case when vote is null
        assertEquals(2,voteCounter.getNulls());

        // Case when the vote is on a party that is not in the valid parties
        votingKiosk.setNif(new Nif("77777777B"));
        assertThrows(NoPartyException.class, () -> votingKiosk.vote(nonExistingParty));

        // Check if all the votes are scrutinized correctly
        assertEquals(4, voteCounter.getTotal());
    }

    @Test
    void sendReceiptTest() {
        VotingKiosk votingKiosk = new VotingKiosk();
        ElectoralOrganismSpy eos = new ElectoralOrganismSpy();
        MailerServiceSpy mss = new MailerServiceSpy();
        votingKiosk.setElectoralOrganism(eos);
        votingKiosk.setMailerService(mss);
    }
}
