import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GladLibMap {
    HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedWords;
    private Random myRandom;
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";
    public GladLibMap(){
        myMap = new HashMap<>();
        initializeFromSource(dataSourceDirectory);
        usedWords = new ArrayList<>();
        myRandom = new Random();
    }
    private void initializeFromSource(String source) {
        String[] label = {
                "country", "adjective", "noun"
                ,"color", "name", "animal", "timeframe"
        };
        for(String s : label){
            ArrayList<String> list = readIt(source + "/" + s + ".txt");
            myMap.put(s, list);
        }
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if(label.equals("number")){
            return "" + myRandom.nextInt(50) + 5;
        }else{
            return randomFrom(myMap.get(label));
        }
    }
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    private int totalWordsConsidered() {
        int total = 0;
        // List of categories used in a particular GladLib
        ArrayList<String> categoriesUsed = new ArrayList<>();
        categoriesUsed.add("adjective");
        categoriesUsed.add("noun");
        categoriesUsed.add("color");
        // Add more categories that you have used
        for (String category : categoriesUsed) {
            total += myMap.get(category).size();
        }
        return total;
    }
    public void tester(){
        int result = totalWordsConsidered();
        System.out.println(result);
    }
}
