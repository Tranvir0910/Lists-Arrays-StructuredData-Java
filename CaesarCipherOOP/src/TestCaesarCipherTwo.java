import edu.duke.FileResource;

public class TestCaesarCipherTwo {
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
    public String halfOfString(String message, int start) {
        StringBuilder half = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            half.append(message.charAt(i));
        }
        return half.toString();
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

    public void simpleTests(){
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(14, 24);

        String encrypted = cc.encrypt(input);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
    }

    public void breakCaesarCipher(String input) {
        String half1 = halfOfString(input, 0);
        String half2 = halfOfString(input, 1);

        int[] freq1 = countLetters(half1);
        int[] freq2 = countLetters(half2);

        int maxIndex1 = maxIndex(freq1);
        int maxIndex2 = maxIndex(freq2);

        int key1 = (maxIndex1 - 4 + 26) % 26; // Khóa 1 cần trừ 4 vì thường E là chữ xuất hiện nhiều nhất
        int key2 = (maxIndex2 - 4 + 26) % 26; // Khóa 2 cũng cần trừ 4

        System.out.println("Keys found: " + key1 + ", " + key2);

        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        String decrypted = cc.decrypt(input);
        System.out.println("Decrypted message: " + decrypted);
    }
}
