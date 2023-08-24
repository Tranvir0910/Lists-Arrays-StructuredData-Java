import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies (){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for(String s : fr.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if(index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }else{
                int freq = myFreqs.get(index);
                myFreqs.set(index, freq + 1);
            }
        }
    }
    public int findIndexOfMax(){
        int maxIdx = 0;
        for(int i = 0; i < myWords.size(); i++){
            if(myFreqs.get(maxIdx) < myFreqs.get(i)){
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    public void tester(){
        findUnique();
        System.out.println("unique word = " + myWords.size());
        for(int i = 0; i < myWords.size(); i++){
            System.out.println(myWords.get(i) + " " + myFreqs.get(i));
        }
        myFreqs.clear();
        myWords.clear();
        findUnique();
        int result = findIndexOfMax();
        System.out.println("--------------------------------------------");
        System.out.println(myWords.get(result) + " " + myFreqs.get(result));
    }
}
