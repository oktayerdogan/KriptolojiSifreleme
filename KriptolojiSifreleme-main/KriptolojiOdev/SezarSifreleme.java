public class SezarSifreleme implements Cipher {
    private final int shift;

    public SezarSifreleme(int shift) {
        this.shift = ((shift % 26) + 26) % 26;
    }

    @Override
    public String encrypt(String plaintext) {
        if (plaintext == null) return null;
        StringBuilder sonuc = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                char sifreli = (char) ((c - 'A' + shift) % 26 + 'A');
                sonuc.append(sifreli);
            } else if (c >= 'a' && c <= 'z') {
                char sifreli = (char) ((c - 'a' + shift) % 26 + 'a');
                sonuc.append(sifreli);
            } else {
                sonuc.append(c);
            }
        }
        return sonuc.toString();
    }

    @Override
    public String decrypt(String ciphertext) {
        if (ciphertext == null) return null;
        int inverseShift = (26 - shift) % 26;
        SezarSifreleme inv = new SezarSifreleme(inverseShift);
        return inv.encrypt(ciphertext);
    }

    @Override
    public String toString() {
        return "SezarSifreleme(shift=" + shift + ")";
    }
}