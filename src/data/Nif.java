package data;

import exceptions.IncorrectNifException;

import java.util.Objects;

public final class Nif {

    private final String Nif;
    public boolean correctNif = false;

    public Nif(String nif) throws IncorrectNifException {
        if (nif.length() != 9) {
            throw new IncorrectNifException();
        } else if (!Character.isLetter(nif.charAt(nif.length() - 1))) {
            throw new IncorrectNifException();
        }
        this.Nif = nif;
        this.correctNif = true;
    }

    public String getNif() {
        return Nif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nif nif = (Nif) o;
        return Objects.equals(Nif, nif.Nif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Nif);
    }

    @Override
    public String toString() {
        return "data.Nif{" +
                "data.Nif='" + Nif + '\'' +
                '}';
    }
}
