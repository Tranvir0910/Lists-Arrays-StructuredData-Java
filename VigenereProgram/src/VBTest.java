import edu.duke.FileResource;

import java.util.HashSet;

public class VBTest {
    public int Caesarkey = 5 ;
    public int Vigenerekey[] = {17,14,12,4};
    public String[] theKey = {"flute"};
    VigenereBreaker VigenereBreaker;
    CaesarCracker CaesarCracker = new CaesarCracker();
    CaesarCipher CaesarCipher;
    VigenereCipher VigenereCipher;

    public void testCaesarCipher(){
        CaesarCipher = new CaesarCipher(Caesarkey);
        //FileResource fr = new FileResource("ViginereMessages/titus-small.txt");
        FileResource fr = new FileResource("messages/secretmessage1.txt");
        String message = fr.asString();
        String encrypted =  CaesarCipher.encrypt(message);
        System.out.println("The encryption result is " +encrypted);
        String decrypted = CaesarCipher.decrypt(encrypted);
        System.out.println("The decryption outcome is "+decrypted);
    }

    public void testCaesarCracker(){
        //FileResource fr = new FileResource("ViginereMessages/titus-small_key5.txt");
        FileResource fr = new FileResource("messages/secretmessage1.txt");
        String message = fr.asString();
        String decrypted = CaesarCracker.decrypt(message);
        System.out.println("The CaesarCracker decryption result is "+decrypted);
        int key = CaesarCracker.getKey(message);
        System.out.println("The CaesarCracker key is "+key);
    }

    public void TestVigenereCipher(){
        VigenereCipher = new VigenereCipher(Vigenerekey);
        //FileResource fr = new FileResource("ViginereMessages/titus-small.txt");
        FileResource fr = new FileResource("messages/secretmessage1.txt");
        String message  = fr.asString();
        String encrypted =  VigenereCipher.encrypt(message);
        System.out.println("The Vigenere encryption result is " +encrypted);
        String decrypted = VigenereCipher.decrypt(encrypted);
        System.out.println("The Vigenere decryption outcome is "+decrypted);
    }

    public void TestSliceString(){
        VigenereBreaker = new VigenereBreaker();
        System.out.println("Should return adgjm = "+VigenereBreaker.sliceString("abcdefghijklm",0,3));
        System.out.println("Should return behk = "+VigenereBreaker.sliceString("abcdefghijklm",1,3));
        System.out.println("Should return cfil = "+VigenereBreaker.sliceString("abcdefghijklm",2,3));
        System.out.println("Should return aeim = "+VigenereBreaker.sliceString("abcdefghijklm",0,4));
        System.out.println("Should return bjf = "+VigenereBreaker.sliceString("abcdefghijklm",1,4));
        System.out.println("Should return cgk = "+VigenereBreaker.sliceString("abcdefghijklm",2,4));
        System.out.println("Should return dhl = "+VigenereBreaker.sliceString("abcdefghijklm",3,4));
        System.out.println("Should return afk = "+VigenereBreaker.sliceString("abcdefghijklm",0,5));
        System.out.println("Should return bgl = "+VigenereBreaker.sliceString("abcdefghijklm",1,5));
        System.out.println("Should return chm = "+VigenereBreaker.sliceString("abcdefghijklm",2,5));
        System.out.println("Should return di = "+VigenereBreaker.sliceString("abcdefghijklm",3,5));
        System.out.println("Should return ej = "+VigenereBreaker.sliceString("abcdefghijklm",4,5));
    }

    public void TestTryKeyLength(){
        VigenereBreaker VB = new VigenereBreaker();
        //FileResource fr = new FileResource("ViginereMessages/athens_keyflute.txt");
        FileResource fr = new FileResource("messages/secretmessage2.txt");
        String message  = fr.asString();
        int keyReturn[] = new int[4];
        keyReturn = VB.tryKeyLength(message,4,'e');
        for (int i =0 ;i < keyReturn.length; i += 1){
            System.out.println("Return Keys "+ keyReturn[i] + "\t");
        }
    }

    public void TestCountWords(){
        int count = 0;
        String message = "Enter BRUTUS and CASSIUS, and a throng of Citizens";
        HashSet<String> DictContent = new HashSet<String>();
        VigenereBreaker VBR = new VigenereBreaker();
        FileResource dictResource = new FileResource("dictionaries/English");
        DictContent = VBR.readDictionary(dictResource);
        count = VBR.countWords(message, DictContent);
        System.out.println("Number of words counted "+ count);

    }

    public void VigenereBreakerTest(){
        int keyReturn[] = new int[4];
        String decrypted = new String();
        VigenereBreaker anotherVigenere = new VigenereBreaker();
        String message = anotherVigenere.previousbreakVigenere();
        keyReturn = anotherVigenere.tryKeyLength(message,4,'e');
        VigenereCipher VigenereCipher  = new VigenereCipher(keyReturn) ;
        decrypted += VigenereCipher.decrypt(message);
        /*
        for (int i =0 ;i < keyReturn.length;i+=1){
            System.out.println("Return Keys "+ keyReturn[i] + "\t");
            decrypted += VigenereCipher.decrypt(anotherVigenere.sliceString(message,i,5));
        }
        */
        System.out.println("The VigenereCipher decryption : "+decrypted);

    }
    public void BreakVigenereTest(){
        String MaxDecryption = new String();
        VigenereBreaker MyBreaker = new VigenereBreaker();
        MyBreaker.breakVigenere();
        System.out.println("The decrypted message is : " + MaxDecryption);
    }
}
