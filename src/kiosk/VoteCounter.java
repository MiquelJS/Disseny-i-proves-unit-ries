package kiosk;
import data.Party;
import exceptions.NoPartyException;
import java.util.Set;
/**
 * A class that represents the result in an election site
 */
public class VoteCounter {

    private Set<Party> validParties;
    private int partyVotes = 0;
    private int nullVotes = 0;
    private int blankVotes = 0;

    public VoteCounter(Set<Party> validParties) { this.validParties = validParties; }

    public void countParty(Party party) throws NoPartyException {
        if (validParties.contains(party)) { this.partyVotes++; }
        else { throw new NoPartyException(); }
    }
    public void countNull() { this.nullVotes++; }
    public void countBlank() { this.blankVotes++; }
    public void scrutinize(Party party) throws NoPartyException {
        if (party.getName().equals("null")) { countNull(); }
        else if (party.getName().equals("")) { countBlank(); }
        else { countParty(party); }
    }
    public int getVotesFor(Party party) { return this.partyVotes; }
    public int getNulls() { return this.nullVotes; }
    public int getBlanks() { return this.blankVotes; }
    public int getTotal() { return this.partyVotes + this.nullVotes + this.blankVotes; }
}
