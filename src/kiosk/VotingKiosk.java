package kiosk;

import data.DigitalSignature;
import data.Nif;
import data.Party;
import data.MailAddress;
import exceptions.CantVoteException;
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
   public Party partyToSign;
   public boolean votedSuccessfully = false;

    public VotingKiosk() { }

   public VotingKiosk(Set<Party> parties) {
       this.voteCounter = new VoteCounter(parties);
   }

   public void setElectoralOrganism(ElectoralOrganism eO) { this.eO = eO; }
   public void setMailerService(MailerService mService){ this.mService = mService; }
   public void setNif(Nif nif) {this.nif = nif;}
   public void setPartyToSign(Party party) {this.partyToSign = party;}

   // Methods to test
   public void vote(Party party) throws NoPartyException, CantVoteException {
       if(eO.canVote(nif)) {
           voteCounter.scrutinize(party);
           eO.disableVoter(nif);
           this.votedSuccessfully = true;
       } else {
           throw new CantVoteException();
       }
   }
   public void sendeReceipt(MailAddress address) {
       DigitalSignature digitalSignature = eO.askForDigitalSignature(partyToSign);
       mService.send(address,digitalSignature);

   }
}
