package tests;

import data.DigitalSignature;
import data.MailAddress;
import services.MailerService;

public class MailerServiceSpy implements MailerService {

    public MailAddress mailAddress;

    @Override
    public void send(MailAddress address, DigitalSignature signature) {
        this.mailAddress = address;
    }
}
