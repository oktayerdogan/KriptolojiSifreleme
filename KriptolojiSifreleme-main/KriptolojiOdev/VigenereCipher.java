import java.util.Locale;

public class VigenereCipher implements Cipher {
    private final String key; // stored lower-case letters only

    public VigenereCipher(String key) {
        if (key == null || key.isEmpty()) throw new IllegalArgumentException("Key must be non-empty");
        this.key = key.toLowerCase(Locale.ROOT).replaceAll("[^a-z]", "");
        if (this.key.isEmpty()) throw new IllegalArgumentException("Key must contain alphabetic characters");
    }

    @Override
    public String encrypt(String plaintext) {
        if (plaintext == null) return null;
        StringBuilder out = new StringBuilder();
        int keyIndex = 0;
        for (char c : plaintext.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                int shift = key.charAt(keyIndex) - 'a';
                char enc = (char) ((c - 'A' + shift) % 26 + 'A');
                out.append(enc);
                keyIndex = (keyIndex + 1) % key.length();
            } else if (c >= 'a' && c <= 'z') {
                int shift = key.charAt(keyIndex) - 'a';
                char enc = (char) ((c - 'a' + shift) % 26 + 'a');
                out.append(enc);
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                out.append(c);
            }
        }
        return out.toString();
    }

    @Override
    public String decrypt(String ciphertext) {
        if (ciphertext == null) return null;
        StringBuilder out = new StringBuilder();
        int keyIndex = 0;
        for (char c : ciphertext.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                int shift = key.charAt(keyIndex) - 'a';
                char dec = (char) ((c - 'A' - shift + 26) % 26 + 'A');
                out.append(dec);
                keyIndex = (keyIndex + 1) % key.length();
            } else if (c >= 'a' && c <= 'z') {
                int shift = key.charAt(keyIndex) - 'a';
                char dec = (char) ((c - 'a' - shift + 26) % 26 + 'a');
                out.append(dec);
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                out.append(c);
            }
        }
        return out.toString();
    }

    @Override
    public String toString() {
        return "VigenereCipher(key=" + key + ")";
    }
}

