import java.util.Scanner;

public class AffineCipher {
    public static String sifrele(String metin, int a, int b){
        StringBuilder sonuc = new StringBuilder();

        for(char c : metin.toCharArray()){
            if(c >= 'A' && c <= 'Z'){
                int p = c - 'A'; //'H' için: 'H' ASCII değil, ama Java char ile çıkarma yapınca 'H' - 'A' = 72 - 65 = 7. Bu p = 7.
                int ch = (a * p + b) % 26; //Affine formülü. Örn a=5,b=8,p=7
                //a * p = 5 * 7 = 35
                //a * p + b = 35 + 8 = 43
                //43 % 26 = 43 - 26 * 1 = 43 - 26 = 17 → ch = 17
                sonuc.append((char)(ch + 'A'));
            }
            else if(c >= 'a' && c <= 'z'){ //küçük harfler için benzer, 'a' tabanlı
                int p = c - 'a'; //harfi sayıya çevir.
                int ch = (a * p + b) % 26;
                sonuc.append((char)(ch + 'a'));
            }
            else{
                sonuc.append(c);
            }
        }
        return sonuc.toString();
    }

    // Çarpma tersi bulma (a^-1 mod 26)
    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) return x;
        }
        return -1; // yoksa
    }

    // Deşifreleme fonksiyonu
    public static String desifrele(String sifreli, int a, int b) {
        StringBuilder sonuc = new StringBuilder();
        int a_inverse = modInverse(a, 26); // a^-1 mod 26

        for (char c : sifreli.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                int y = c - 'A';
                int p = (a_inverse * (y - b + 26)) % 26;
                sonuc.append((char)(p + 'A'));
            } else if (c >= 'a' && c <= 'z') {
                int y = c - 'a';
                int p = (a_inverse * (y - b + 26)) % 26;
                sonuc.append((char)(p + 'a'));
            } else {
                sonuc.append(c);
            }
        }
        return sonuc.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Metni giriniz: ");
        String metin = sc.nextLine();

        System.out.print("a değerini giriniz (26 ile aralarında asal olmalı): ");
        int a = sc.nextInt();

        System.out.print("b değerini giriniz (0-25): ");
        int b = sc.nextInt();

        String sifreli = sifrele(metin, a, b);
        System.out.println("Şifreli metin: " + sifreli);

        String cozulmus = desifrele(sifreli, a, b);
        System.out.println("Çözülmüş metin: " + cozulmus);

        sc.close();
    }
}
