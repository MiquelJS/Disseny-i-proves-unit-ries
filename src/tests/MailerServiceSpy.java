package tests;

import data.DigitalSignature;
import data.MailAddress;
import services.MailerService;

public class MailerServiceSpy implements MailerService {

    public boolean sentMail = false;
    @Override
    public void send(MailAddress address, DigitalSignature signature) {
        this.sentMail = true;
    }
}
