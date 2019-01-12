import java.util.Objects;

public final class MailAdress {
    private final String Mail;

    public MailAdress(String mail) {
        Mail = mail;
    }

    public String getMail() {
        return Mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailAdress that = (MailAdress) o;
        return Objects.equals(Mail, that.Mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Mail);
    }

    @Override
    public String toString() {
        return "MailAdress{" +
                "Mail='" + Mail + '\'' +
                '}';
    }
}
