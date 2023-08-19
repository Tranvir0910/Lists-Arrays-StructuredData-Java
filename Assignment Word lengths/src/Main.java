import edu.duke.FileResource;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        CaesarBreaker cb = new CaesarBreaker();
        WordLengths wl = new WordLengths();
        cb.testDecryptTwoKeys();
    }
}