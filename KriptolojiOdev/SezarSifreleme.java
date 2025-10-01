import java.util.Scanner;

public class SezarSifreleme {

    public static String sifrele(String metin,int kaydirma){
        StringBuilder sonuc = new StringBuilder(); //sürekli yeni string nesnesi oluşmayacak var olan güncellenecek

        for(char c : metin.toCharArray()){ //stringi karakter dizisine dönüştürür.
           if(c >='A' && c<= 'Z'){
            // Kaydırma işlemi: (c - 'A') harfi 0-25 arası bir sayıya çevirir
            char sifreli =(char)((c-'A' + kaydirma) % 26 + 'A');
            sonuc.append(sifreli);
           }
           else if(c>='a' && c<='z'){
            char sifreli = (char)((c-'a' + kaydirma) % 26 + 'a');
            sonuc.append(sifreli);
           }
           else{
            sonuc.append(c);
           }      
        }
        return sonuc.toString();
    }

    public static String coz(String sifreliMetin,int kaydirma){
        return sifrele(sifreliMetin, 26-(kaydirma % 26));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Lütfen bir metin giriniz : ");
        String metin = scanner.nextLine();

        System.out.print("kaydırma değerini giriniz : ");
        int kaydirmaDegeri = scanner.nextInt();

        String sifreli = sifrele(metin, kaydirmaDegeri);
        System.out.println("şifreli metin : " + sifreli);

        System.out.println("Çözülmüş : " + coz(sifreli, kaydirmaDegeri));

        scanner.close();
        
    }
}