
public class Main {
    public static void main(String[] args) {
        WordPlay wp = new WordPlay();
        CaesarCipher cc = new CaesarCipher();
        cc.testEncrypt();
        System.out.println(wp.replaceVowels("Hello World", '*'));
        System.out.println(wp.emphasize("Mary Bella Abracadabra", 'a'));
        cc.testEncryptTwoKey();
    }
}