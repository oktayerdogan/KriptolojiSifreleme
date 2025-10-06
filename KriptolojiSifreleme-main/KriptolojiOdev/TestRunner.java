public class TestRunner {
    static void check(String name, Cipher c, String plaintext) {
        System.out.println("--- " + name + " ---");
        System.out.println("Plaintext: " + plaintext);
        String enc = c.encrypt(plaintext);
        System.out.println("Encrypted: " + enc);
        String dec = c.decrypt(enc);
        System.out.println("Decrypted: " + dec);
        System.out.println("Round-trip equal: " + plaintext.equals(dec));
        System.out.println();
    }

    public static void main(String[] args) {
        check("Caesar (shift=3)", new SezarSifreleme(3), "Hello, World!");
        check("Substitution (shift=3)", new SubstitutionCipher(3), "Hello, World!");
        check("Affine (a=5,b=8)", new AffineCipher(5,8), "Hello, World!");
        check("Vigenere (key=KEY)", new VigenereCipher("KEY"), "attack at dawn");
    }
}
