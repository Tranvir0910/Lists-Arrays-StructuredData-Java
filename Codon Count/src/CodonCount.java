import edu.duke.FileResource;

import java.util.HashMap;

public class CodonCount {
    private HashMap<String, Integer> map;
    public CodonCount(){
        map = new HashMap<String, Integer>();
    }
    public void buildCodonMap(int start, String dna){
//        int length = (dna.length() - start) % 3;
//        if(length == 0) {
//            for (int i = start; i < dna.length(); i += 3) {
//                String codon = dna.substring(i, i + 3);
//                if (!map.containsKey(codon)){
//                    map.put(codon,1);
//                }
//                else {
//                    map.put(codon,map.get(codon)+1);
//                }
//            }
//        }else{
//            for (int i = start; i < dna.length() - length; i += 3) {
//                String codon = dna.substring(i, i + 3);
//                if (!map.containsKey(codon)){
//                    map.put(codon,1);
//                }
//                else {
//                    map.put(codon,map.get(codon)+1);
//                }
//            }
//        }
        dna = dna.toUpperCase();

        // Ensure the start value is valid (0, 1, or 2)
        if (start < 0 || start > 2) {
            throw new IllegalArgumentException("Invalid start value. Must be 0, 1, or 2.");
        }
        // Iterate through the DNA string with a step of 3 to read codons
        for (int i = start; i + 2 < dna.length(); i += 3) {
            String codon = dna.substring(i, i + 3);
            // Check if the codon contains only valid characters
            if (codon.matches("[CGTA]{3}")) {
                map.put(codon, map.getOrDefault(codon, 0) + 1);
            }
        }

    }
    public String getMostCommonCodon(){
        String maxKey = "";
        int maxValue = Integer.MIN_VALUE;
        for(String w : map.keySet()){
            int value = map.get(w);
            if(value > maxValue){
                maxValue = value;
                maxKey = w;
            }
        }
        return maxKey;
    }
    public void printCodonCounts(int start, int end){
        int total = 0;
        for(String w : map.keySet()){
            int value = map.get(w);
            if (value > start && value < end){
                System.out.println( value + "\t" + w );
            }
            total += value;
        }
        System.out.println("total count: " + total + " different = " + map.keySet().size());
    }
    public void tester(){
        map.clear();
        FileResource fr = new FileResource();
        String s = fr.asString().toUpperCase();
        for(int i = 0; i < 3; i++){
            buildCodonMap(i, s);
            printCodonCounts(0, 1000);
        }
        for(String w : map.keySet()){
            int value = map.get(w);
            System.out.println( value + "\t" + w );
        }
        System.out.println("-----------------------------------------------");
        map.clear();
        buildCodonMap(0, s);
        System.out.println(map.size() + getMostCommonCodon());
        for(String w : map.keySet()){
            int value = map.get(w);
            if(value == 7)
                System.out.println( value + "\t" + w );
        }
    }
}
