package cz.cvut.fel.via.zboziforandroid.client.words;

public class WordResponse {
    private Word[] words;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            sb.append(words[i].toString()).append("\n");
        }
        return sb.toString();
    }
    
    public Word[] getWords() {
    	return words;
    }
    
}