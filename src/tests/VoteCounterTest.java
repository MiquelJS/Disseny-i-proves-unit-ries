package tests;

import data.Nif;
import data.Party;
import exceptions.CantVoteException;
import exceptions.NoPartyException;
import kiosk.VoteCounter;
import kiosk.VotingKiosk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VoteCounterTest {

    private Party votedParty;
    private Party anotherParty;
    private VotingKiosk votingKiosk;
    private VoteCounter voteCounter;

    @BeforeEach
    void set() {
        this.votedParty = new Party("VOX");
        this.anotherParty = new Party("ERC");

        this.votingKiosk = new VotingKiosk(new HashSet<>(Arrays.asList(votedParty,anotherParty)));
        votingKiosk.setElectoralOrganism(new ElectoralOrganismSpy());
        this.voteCounter = votingKiosk.voteCounter;
    }

    @Test
    void votePartyTest() throws NoPartyException, CantVoteException {

        votingKiosk.setNif(new Nif("48250721X"));
        votingKiosk.vote(votedParty);

        // Case when the vote is scrutinized correctly
        assertEquals(1,voteCounter.getVotesFor(votedParty));

        // Case when a party is not voted
        assertEquals(0,voteCounter.getVotesFor(anotherParty));
    }

    @Test
    void blankVoteTest() throws NoPartyException, CantVoteException {

        votingKiosk.setNif(new Nif("48250721X"));
        votingKiosk.vote(new Party(""));
        assertEquals(1, voteCounter.getBlanks());
    }

    @Test
    void nullVoteTest() throws NoPartyException, CantVoteException {

        votingKiosk.setNif(new Nif("48250721X"));
        votingKiosk.vote(new Party("null"));
        VoteCounter voteCounter = votingKiosk.voteCounter;
        assertEquals(1, voteCounter.getNulls());
    }

    @Test
    void totalVotesTest() throws NoPartyException, CantVoteException {
        votingKiosk.setNif(new Nif("48250721X"));
        votingKiosk.vote(votedParty);
        votingKiosk.setNif(new Nif("44444444A"));
        votingKiosk.vote(anotherParty);
        votingKiosk.setNif(new Nif("77777777B"));
        votingKiosk.vote(anotherParty);
        votingKiosk.setNif(new Nif("88888888Y"));
        votingKiosk.vote(votedParty);
        assertEquals(4,voteCounter.getTotal());

    }
}
