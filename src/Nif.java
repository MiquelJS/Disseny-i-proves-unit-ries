import data.Party;

import java.util.Objects;

public final class Nif {

    private final String Nif;

    public Nif(String nif) {
        Nif = nif;
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
        return "Nif{" +
                "Nif='" + Nif + '\'' +
                '}';
    }
}
