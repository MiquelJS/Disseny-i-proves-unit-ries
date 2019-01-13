package kiosk;
import data.Party;
import exceptions.NoPartyException;

import java.util.ArrayList;
import java.util.Set;
/**
 * A class that represents the result in an election site
 */
public class VoteCounter {

    private Set<Party> validParties;
    private ArrayList<Integer> partiesVotes;
    private int nullVotes = 0;
    private int blankVotes = 0;

    public VoteCounter(Set<Party> validParties) {
        this.validParties = validParties;
        this.partiesVotes = new ArrayList<Integer>();
        for (Party party : validParties) {
            this.partiesVotes.add(0);
        }
    }

    public void countParty(Party party) throws NoPartyException {
        if (validParties.contains(party)) {
            int i = 0;
            for (Party p : validParties) {
                if (p.equals(party)) partiesVotes.set(i,partiesVotes.get(i) + 1);
                i++;
            }
        } else {
            throw new NoPartyException();
        }
    }
    public void countNull() { this.nullVotes++; }
    public void countBlank() { this.blankVotes++; }

    public void scrutinize(Party party) throws NoPartyException {
        if (party.getName().equals("null")) { countNull(); }
        else if (party.getName().equals("")) { countBlank(); }
        else { countParty(party); }
    }

    public int getVotesFor(Party party) throws NoPartyException {
        if (validParties.contains(party)) {
            return partiesVotes.get(getIndexOfParty(party));
        }
        throw new NoPartyException();
    }

    // Gets the index of the party in validParties
    private int getIndexOfParty(Party party) {
        int i = 0;
        for (Party p : validParties) {
            if (p.equals(party)) return i;
            i++;
        }
        return -1;
    }
    public int getNulls() { return this.nullVotes; }
    public int getBlanks() { return this.blankVotes; }
    public int getTotal() { return sumPartiesVotes() + this.nullVotes + this.blankVotes; }

    // Sum all the current votes of all parties
    private int sumPartiesVotes() {
        int sum = 0;
        for (Integer i : partiesVotes)
            sum += i;
        return sum;
    }
}
