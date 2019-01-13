package data;

import java.util.Arrays;

public final class DigitalSignature {
    private final byte[] DigitalSignature;

    public DigitalSignature(byte[] digitalSignature) {
        DigitalSignature = digitalSignature;
    }

    public byte[] getDigitalSignature() {
        return DigitalSignature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigitalSignature that = (DigitalSignature) o;
        return Arrays.equals(DigitalSignature, that.DigitalSignature);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(DigitalSignature);
    }

    @Override
    public String toString() {
        return "data.DigitalSignature{" +
                "data.DigitalSignature=" + Arrays.toString(DigitalSignature) +
                '}';
    }
}
