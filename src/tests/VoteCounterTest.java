package tests;

import data.Party;
import exceptions.CantVoteException;
import exceptions.IncorrectNifException;
import exceptions.NoPartyException;
import kiosk.VoteCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VoteCounterTest {

    private Party votedParty;
    private Party anotherParty;
    private VoteCounter voteCounter;

    @BeforeEach
    void set() {
        this.votedParty = new Party("VOX");
        this.anotherParty = new Party("ERC");
        this.voteCounter = new VoteCounter(new HashSet<>(Arrays.asList(votedParty,anotherParty)));
    }

    @Test
    void votePartyTest() throws NoPartyException {
        voteCounter.scrutinize(votedParty);

        // Case when the vote is scrutinized correctly
        assertEquals(1,voteCounter.getVotesFor(votedParty));

        // Case when a party is not voted
        assertEquals(0,voteCounter.getVotesFor(anotherParty));
    }

    @Test
    void blankVoteTest() throws NoPartyException {

        voteCounter.scrutinize(new Party(""));
        assertEquals(1, voteCounter.getBlanks());
    }

    @Test
    void nullVoteTest() throws NoPartyException {

        voteCounter.scrutinize(new Party("null"));
        assertEquals(1, voteCounter.getNulls());
    }

    @Test
    void totalVotesTest() throws NoPartyException, CantVoteException, IncorrectNifException {
        voteCounter.scrutinize(votedParty);
        voteCounter.scrutinize(anotherParty);
        voteCounter.scrutinize(new Party(""));
        voteCounter.scrutinize(new Party("null"));

        assertEquals(4,voteCounter.getTotal());

    }
}
