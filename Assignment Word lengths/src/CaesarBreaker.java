import edu.duke.FileResource;

public class CaesarBreaker {
    public int[] countLetters(String message) {
        // Khởi tạo mảng tần suất với độ dài là 26 (số lượng chữ cái trong bảng tiếng Anh)
        int[] frequencies = new int[26];

        // Chuyển đổi chuỗi message thành chữ cái thường để xử lý dễ dàng
        String lowercaseMessage = message.toLowerCase();

        // Duyệt qua từng ký tự trong chuỗi
        for (int i = 0; i < lowercaseMessage.length(); i++) {
            char ch = lowercaseMessage.charAt(i);

            // Kiểm tra xem ký tự có phải là chữ cái không
            if (Character.isLetter(ch)) {
                // Tính chỉ số trong mảng tần suất bằng cách trừ đi mã ASCII của 'a'
                int index = ch - 'a';

                // Tăng tần suất cho chữ cái tương ứng
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

    public String decrypt(String encrypted, int key) {
        CaesarCipher cc = new CaesarCipher();
        return cc.encrypt(encrypted, 26 - key);
    }
    public void testDecrypt(){
        String result = decrypt("CFOPQ IBDFLK XQQXZH BXPQ CIXKH!", 23);
        System.out.println(result);
    }

    public String halfOfString(String message, int start) {
        StringBuilder half = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            half.append(message.charAt(i));
        }
        return half.toString();
    }

    public void testHalfOfString(){
        System.out.println(halfOfString("Qbkm Zgis", 1));
    }

    public int getKey(String s) {
        int[] frequencies = countLetters(s);
        int maxIndex = maxIndex(frequencies);

        // Tìm khóa dựa trên khoảng cách giữa vị trí của 'e' (4) và vị trí có tần suất cao nhất
        int key = maxIndex - 4;
        if (maxIndex < 4) {
            key = 26 - (4 - maxIndex);
        }

        return key;
    }
    public String decryptTwoKeys(String encrypted) {
        String oddHalf = halfOfString(encrypted, 0);
        String evenHalf = halfOfString(encrypted, 1);

        int key1 = getKey(oddHalf);
        int key2 = getKey(evenHalf);

        System.out.println("Key 1: " + key1);
        System.out.println("Key 2: " + key2);

        CaesarCipher cc = new CaesarCipher();
        return cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
    }
    public void testDecryptTwoKeys(){
        FileResource fr = new FileResource();
        System.out.println(decryptTwoKeys(fr.asString()));
    }
}
