import java.util.ArrayList;
import java.util.Collections;

public class Dictionary{
    // Word Array
    protected ArrayList<Word> listWord = new ArrayList<>();

    /** Getter listWord. */
    public ArrayList<Word> getListWord() {
        return this.listWord;
    }

    /** Sort list of word. */
    public void sortListWord() {
        Collections.sort(listWord);
    }

    /** Add new word into listWord. */
    public void addWord(Word word) {
        boolean check = false;
        for (Word w : listWord) {
            if (word.compareTo(w) == 0) {
                check = true;
                break;
            }
        }
        if (!check) {
            listWord.add(word);
        }
    }
}