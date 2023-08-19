import edu.duke.FileResource;

public class WordLengths {
    public void countWordLengths(FileResource fr, int[] counts, String[] common ){
        for(String word : fr.words()){
            int wordLength = word.length();
            // Check if the first or last character is not a letter
            if (!Character.isLetter(word.charAt(0))) {
                wordLength--;
            }
            if (!Character.isLetter(word.charAt(word.length() - 1))) {
                wordLength--;
            }
            if (wordLength >= counts.length) {
                counts[counts.length - 1]++;
                common[counts.length - 1] = word;
            } else if (wordLength > 0) {
                counts[wordLength]++;
                common[wordLength] = word;
            }
        }
    }
    public int indexOfMax(int[] values){
        int maxIdx = Integer.MIN_VALUE;
        for(int i = 0; i < values.length; i++){
            if(values[i] > maxIdx){
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        String[] common = new String[counts.length];
        countWordLengths(fr, counts, common);
        for(int i = 0; i < counts.length; i++){
            System.out.println(counts[i] + " " + common[i]);
        }
        System.out.println("Max idx = " + indexOfMax(counts));
    }


}
