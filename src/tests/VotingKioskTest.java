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
        VotingKiosk votingKiosk = new VotingKiosk();
        Party party = new Party("VOX");
        Party anotherParty = new Party("ERC");

        // Case when the vote is scrutinized correctly
        votingKiosk.vote(party);
        VoteCounter voteCounter = votingKiosk.voteCounter;
        assertEquals(1,voteCounter.getVotesFor(party));

        // Case when the vote is on a party that is not in the valid parties
        /*votingKiosk.vote(anotherParty);


        */
    }
}
