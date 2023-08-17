public class WordPlay {

//    public boolean isVowel(char ch){
//        String vowel = "aeiouAEIOU";
//        for(int i = 0; i < vowel.length(); i++){
//            char currChar = vowel.charAt(i);
//            if(ch == currChar){
//                return true;
//            }
//        }
//        return false;
//    }


    public boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

//    public String replaceVowels(String phrase, char ch){
//        for(int i = 0; i < phrase.length(); i++){
//            char currChar = phrase.charAt(i);
//            if(isVowel(currChar)){
//                phrase = phrase.replace(currChar, ch);
//            }
//        }
//        return phrase;
//    }
    public String replaceVowels(String phrase, char ch) {
        StringBuilder result = new StringBuilder();
        for (char c : phrase.toCharArray()) {
            if (isVowel(c)) {
                result.append(ch);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public String emphasize(String phrase, char ch){
        StringBuilder result = new StringBuilder(phrase);
        for(int i = 0; i < result.length(); i++){
            char currChar = result.charAt(i);
            currChar = Character.toLowerCase(currChar);
            if(currChar == ch){
                if( (i + 1) % 2 == 0 ){
                    result.setCharAt(i, '+');
                }else{
                    result.setCharAt(i, '*');
                }
            }
        }
        return result.toString();
    }
}
