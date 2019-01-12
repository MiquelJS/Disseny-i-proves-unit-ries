package kiosk;

import data.Party;
import data.MailAddress;
import exceptions.NoPartyException;
import services.ElectoralOrganism;
import services.MailerService;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Implements a simplification of Use Case: Emit eVote
 */
public class VotingKiosk {

   public VoteCounter voteCounter;
   public ElectoralOrganism eO;
   public MailerService mService;

   public VotingKiosk() { }

   public void setElectoralOrganism(ElectoralOrganism eO) { this.eO = eO; }
   public void setMailerService(MailerService mService){ this.mService = mService; }

   // Methods to test
   public void vote(Party party) throws NoPartyException {
      this.voteCounter = new VoteCounter(new HashSet<>(Arrays.asList(party)));
      voteCounter.scrutinize(party);
   }
   public void sendeReceipt(MailAddress address) { }
}
