package tests;

import data.DigitalSignature;
import data.Nif;
import data.Party;
import services.ElectoralOrganism;

import java.util.ArrayList;

public class ElectoralOrganismSpy implements ElectoralOrganism {

    public ArrayList<Nif> voted = new ArrayList<Nif>();

    @Override
    public boolean canVote(Nif nif) {
        if (voted.contains(nif)) return false;
        else return true;
    }

    @Override
    public void disableVoter(Nif nif) {
        voted.add(nif);
    }

    @Override
    public DigitalSignature askForDigitalSignature(Party party) {
        return null;
    }
}
