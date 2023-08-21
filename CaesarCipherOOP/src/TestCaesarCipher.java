import edu.duke.FileResource;

public class TestCaesarCipher {
    public int[] countLetters(String message) {
        // 26 ky tu
        int[] frequencies = new int[26];
        String lowercaseMessage = message.toLowerCase();
        for (int i = 0; i < lowercaseMessage.length(); i++) {
            char ch = lowercaseMessage.charAt(i);
            if (Character.isLetter(ch)) {
                // Tính chỉ số trong mảng tần suất bằng cách trừ đi mã ASCII của 'a'
                int index = ch - 'a';
                frequencies[index]++;
            }
        }
        return frequencies;
    }

    public int maxIndex(int[] freqs) {
        int maxIdx = 0;
        for (int i = 0; i < freqs.length; i++) {
            if (freqs[i] > freqs[maxIdx]) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    public String decrypt(String input, int key) {
        CaesarCipher cc = new CaesarCipher(26 - key);
        return cc.encrypt(input);
    }
    public int getKey(String s) {
        int[] frequencies = countLetters(s);
        int maxIndex = maxIndex(frequencies);
        int key = maxIndex - 4;
        if (maxIndex < 4) {
            key = 26 - (4 - maxIndex);
        }
        return key;
    }

    public String breakCaesarCipher(String input){
        int keyBreak = getKey(input);
        CaesarCipher cc = new CaesarCipher(keyBreak);
        return cc.encrypt(input);
    }
    public void simpleTests(){
        CaesarCipher cc = new CaesarCipher(15);
        FileResource fr = new FileResource();
        String message = fr.asString();

        String resultEncrypt = cc.encrypt(message);
        System.out.println("encrypt : " + resultEncrypt);

        String resultDecrypt = breakCaesarCipher(resultEncrypt);
        System.out.println("decrypt: " + resultDecrypt);
    }
}
