public class SubstitutionCipher implements Cipher {
    private final int shift;

    public SubstitutionCipher(int shift) {
        this.shift = ((shift % 26) + 26) % 26;
    }

    @Override
    public String encrypt(String plaintext) {
        if (plaintext == null) return null;
        StringBuilder result = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                char enc = (char) ((c - 'A' + shift) % 26 + 'A');
                result.append(enc);
            } else if (c >= 'a' && c <= 'z') {
                char enc = (char) ((c - 'a' + shift) % 26 + 'a');
                result.append(enc);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    @Override
    public String decrypt(String ciphertext) {
        if (ciphertext == null) return null;
        int inv = (26 - shift) % 26;
        SubstitutionCipher s = new SubstitutionCipher(inv);
        return s.encrypt(ciphertext);
    }

    @Override
    public String toString() {
        return "SubstitutionCipher(shift=" + shift + ")";
    }
}