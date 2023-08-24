import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;
    public WordsInFiles(){
        map = new HashMap<>();
    }
    public void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for(String word : fr.words()){
            if(map.containsKey(word)){
                ArrayList<String> fileList = map.get(word);
                if (!fileList.contains(f.getName())) {
                    fileList.add(f.getName());
                }
            }else{
                ArrayList<String> fileList = new ArrayList<>();
                fileList.add(f.getName());
                map.put(word, fileList);
            }
        }
    }
    public void buildWordFileMap(){
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    public int maxNumber(){
        int maxIdx = Integer.MIN_VALUE;
        for(String s : map.keySet()) {
            ArrayList<String> tmp = map.get(s);
            if(tmp.size() > maxIdx){
                maxIdx = tmp.size();
            }
        }
        return maxIdx;
    }
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> word = new ArrayList<>();
        for(String s : map.keySet()){
            ArrayList<String> tmp = map.get(s);
            if(tmp.size() == number){
                word.add(s);
            }
        }
        return word;
    }
    public ArrayList<String> printFilesIn(String word){
        ArrayList<String> fileName = new ArrayList<>();
        for(String s : map.keySet()){
            if(s.equals(word.toLowerCase())){
                fileName = map.get(s);
            }
        }
        return fileName;
    }
    private int countWords() {
        int count = 0;
        for (String word : map.keySet()) {
            List<String> currentList = map.get(word);
            count += currentList.size();
        }
        return count;
    }
    public void tester(){
        buildWordFileMap();
        System.out.println(countWords());
        System.out.println("-------------------------------------------------------");
        int maxNumber = maxNumber();
        System.out.println("-------------------------------------------------------");
        System.out.println("max : " + maxNumber);
        System.out.println("-------------------------------------------------------");
        ArrayList<String> result1 = wordsInNumFiles(4);
        System.out.println(result1.size());
        for (String s : result1) {
            System.out.println(s);
        }
        System.out.println("-------------------------------------------------------");
        ArrayList<String> result2 = wordsInNumFiles(3);
        for (String s : result2) {
            System.out.println(s);
        }
        System.out.println("-------------------------------------------------------");
        ArrayList<String> fileName = printFilesIn("tree");
        for (String s : fileName) {
            System.out.println(s);
        }
        System.out.println("-------------------------------------------------------");

    }
}
