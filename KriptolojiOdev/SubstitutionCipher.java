import java.util.Scanner;

public class SubstitutionCipher {

    public static String sifreleme(String metin, int kaydirma){
        StringBuilder sonuc = new StringBuilder();

        for(char c : metin.toCharArray()){
            if(c >= 'A' && c <= 'Z'){
                char sifreli = (char)((c - 'A' + kaydirma) % 26 + 'A');
                sonuc.append(sifreli);
            }
            else if(c >= 'a' && c <= 'z'){
                char sifreli = (char)((c - 'a' + kaydirma) % 26 + 'a');
                sonuc.append(sifreli);
            }
            else{
                sonuc.append(c);
            }
        }
        return sonuc.toString();
    } 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Metni girin : ");
        String metin = scanner.nextLine();

        System.out.print("Kaydırma miktarını girin : ");
        int kaydirma = scanner.nextInt();

        String sifreli = sifreleme(metin, kaydirma);
        System.out.println("Şifreli metin : " + sifreli);

        scanner.close();;
    }
}