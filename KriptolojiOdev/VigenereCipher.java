import java.util.Scanner;

public class VigenereCipher {
    public static String sifreleme(String metin,String anahtar){
        StringBuilder sonuc = new StringBuilder();
        anahtar=anahtar.toLowerCase(); //kaydırma kolaylığı için anahtarı küçük harfe çeviriyoruz.
        int anahtarIndex = 0; //anahtar içindeki harf indexini takip eder

        //metindeki her karakter için döngü
        for(char c : metin.toCharArray()){ //stringi char a çevirdik
            if(c>='A' && c<='Z'){
                int kaydirma = anahtar.charAt(anahtarIndex) - 'a';// Anahtar harfinin kaydırma değerini bul
                                                                  // Örnek: 'k' - 'a' = 10 → kaydırma = 10
                char sifreli = (char)((c - 'A' + kaydirma) % 26 + 'A');
                // Harfi kaydır
                // c - 'A' → harfi 0–25 arası index'e dönüştürür
                // + kaydirma → kaydırma ekler
                // %26 → alfabe sınırını aşarsa başa sarar
                // + 'A' → tekrar ASCII karakterine dönüştürür
                sonuc.append(sifreli);
                anahtarIndex = (anahtarIndex + 1) % anahtar.length();
                 // Anahtar harf indeksini ilerlet
                // Anahtar sonuna gelirse başa dön
            }
            else if(c >= 'a' && c <= 'z'){
                int kaydirma = anahtar.charAt(anahtarIndex) - 'a';
                char sifreli = (char)((c - 'a' + kaydirma) % 26 + 'a');
                sonuc.append(sifreli);
                anahtarIndex = (anahtarIndex + 1) % anahtar.length();
            }
            else{
                sonuc.append(c);
            }
        }
        return sonuc.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Metni giriniz : ");
        String metin = scanner.nextLine();

        System.out.print("Anahtarı giriniz : ");
        String anahtar = scanner.nextLine();

        String sifreliMetin = sifreleme(metin, anahtar);
        System.out.println("Şifreli metin : " + sifreliMetin);
        scanner.close();
    }
}
