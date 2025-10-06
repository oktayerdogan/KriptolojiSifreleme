public class AffineCipher implements Cipher {
    private final int a;
    private final int b;

    public AffineCipher(int a, int b) {
        if (modInverse(a, 26) == -1) {
            throw new IllegalArgumentException("a must be invertible modulo 26 (i.e. coprime with 26)");
        }
        this.a = a;
        this.b = ((b % 26) + 26) % 26;
    }

    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) return x;
        }
        return -1;
    }

    @Override
    public String encrypt(String plaintext) {
        if (plaintext == null) return null;
        StringBuilder result = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                int p = c - 'A';
                int ch = (a * p + b) % 26;
                result.append((char) (ch + 'A'));
            } else if (c >= 'a' && c <= 'z') {
                int p = c - 'a';
                int ch = (a * p + b) % 26;
                result.append((char) (ch + 'a'));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    @Override
    public String decrypt(String ciphertext) {
        if (ciphertext == null) return null;
        StringBuilder result = new StringBuilder();
        int aInv = modInverse(a, 26);
        for (char c : ciphertext.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                int y = c - 'A';
                int p = (aInv * (y - b + 26)) % 26;
                result.append((char) (p + 'A'));
            } else if (c >= 'a' && c <= 'z') {
                int y = c - 'a';
                int p = (aInv * (y - b + 26)) % 26;
                result.append((char) (p + 'a'));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return "AffineCipher(a=" + a + ", b=" + b + ")";
    }
}

