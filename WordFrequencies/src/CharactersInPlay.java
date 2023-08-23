import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> character;
    private ArrayList<Integer> charAppear;
    public CharactersInPlay(){
        character = new ArrayList<String>();
        charAppear = new ArrayList<Integer>();
    }
    public void update(String person){
        int index = character.indexOf(person);
        if(index == -1){
            character.add(person);
            charAppear.add(1);
        }else{
            int cA = charAppear.get(index);
            charAppear.set(index, cA + 1);
        }
    }
    public void findAllCharacters(){
        character.clear();
        charAppear.clear();
        FileResource fr = new FileResource();
        for(String s : fr.lines()){
            int idx = s.indexOf(".");
            if(idx != -1){
                String newChar = s.substring(0, idx).trim();
                update(newChar);
            }
        }
    }
    public void tester(){
        findAllCharacters();
        for(int i = 0; i < character.size(); i++){
            if(charAppear.get(i) > 1)
                System.out.println(character.get(i) + " " + charAppear.get(i));
        }
        System.out.println("-----------------------------------------------------");
        charactersWithNumParts(10, 15);
    }
    public void charactersWithNumParts(int num1, int num2){
        for(int i = 0; i < character.size(); i++){
            if(charAppear.get(i) > num1 && charAppear.get(i) < num2){
                if(charAppear.get(i) > 1)
                    System.out.println(character.get(i) + " " + charAppear.get(i));
            }
        }
    }
}
