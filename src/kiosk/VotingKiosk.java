package kiosk;

import data.Nif;
import data.Party;
import data.MailAddress;
import exceptions.NoPartyException;
import services.ElectoralOrganism;
import services.MailerService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Implements a simplification of Use Case: Emit eVote
 */
public class VotingKiosk {

   public VoteCounter voteCounter;
   public ElectoralOrganism eO;
   public MailerService mService;
   public Nif nif;

   public VotingKiosk() { }

   public VotingKiosk(Set<Party> parties) {
       this.voteCounter = new VoteCounter(parties);
   }

   public void setElectoralOrganism(ElectoralOrganism eO) { this.eO = eO; }
   public void setMailerService(MailerService mService){ this.mService = mService; }
   public void setNif(Nif nif) {this.nif = nif;}

   // Methods to test
   public void vote(Party party) throws NoPartyException {
       if(eO.canVote(nif)) {
           voteCounter.scrutinize(party);
           eO.disableVoter(nif);
       }
   }
   public void sendeReceipt(MailAddress address) {
       if(!eO.canVote(nif)) eO.disableVoter(nif);
   }
}
