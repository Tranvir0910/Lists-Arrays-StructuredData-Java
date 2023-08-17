import edu.duke.*;
public class CaesarCipher {
    public String encrypt(String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = alphabet.toLowerCase();

        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        String shiftedAlphabetLower = shiftedAlphabet.toLowerCase();

        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            int idxLower = alphabetLower.indexOf(currChar);
            if (idx != -1 || idxLower != -1) {
                if(Character.isUpperCase(currChar)) {
                    char newCharUpper = shiftedAlphabet.charAt(idx);
                    encrypted.setCharAt(i, newCharUpper);
                }else{
                    char newCharLower = shiftedAlphabetLower.charAt(idxLower);
                    encrypted.setCharAt(i, newCharLower);
                }
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside encrypted
        return encrypted.toString();
    }

    public void testEncrypt(){
        int key = 15;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }

    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder();
        for(int i = 0; i < input.length(); i++){
            char currChar = input.charAt(i);
            if( i%2 == 0 ){
                encrypted.append(encrypt(Character.toString(currChar), key1));
            }else{
                encrypted.append(encrypt(Character.toString(currChar), key2));
            }
        }
        return encrypted.toString();
    }

    public void testEncryptTwoKey(){
        String result = encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21);
        System.out.println(result);
    }
}
