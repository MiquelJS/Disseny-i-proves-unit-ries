package tests;

import data.DigitalSignature;
import data.MailAddress;
import org.junit.jupiter.api.Test;
import services.MailerService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MailerServiceTest {

    @Test
    void sendTest() {
        MailerService ms = new MailerServiceSpy();
        MailAddress mail =new MailAddress("mjs2@alumnes.udl.cat");
        ms.send(mail ,new DigitalSignature("Digital Signature for ERC".getBytes()));
        assertEquals(mail, ((MailerServiceSpy) ms).mailAddress);
    }
}
