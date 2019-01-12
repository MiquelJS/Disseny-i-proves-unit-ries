package tests;
import static org.junit.jupiter.api.Assertions.*;

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

        votingKiosk.vote(votedParty);
        votingKiosk.vote(new Party(""));
        votingKiosk.vote(new Party("null"));
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
        assertThrows(NoPartyException.class, () -> votingKiosk.vote(nonExistingParty));

    }
}
