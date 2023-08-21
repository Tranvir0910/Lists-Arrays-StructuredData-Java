import edu.duke.FileResource;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        CaesarBreaker cb = new CaesarBreaker();
        CaesarCipher cc = new CaesarCipher();
        WordLengths wl = new WordLengths();

        wl.testCountWordLengths();
    }
}