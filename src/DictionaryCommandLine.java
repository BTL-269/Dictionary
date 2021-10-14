import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandLine extends DictionaryManagement {

    public DictionaryCommandLine() {
        File file = new File("listHistory.txt");
        if (file.length() != 0) {
            insertFromFile("listHistory.txt");
        }
    }

    public DictionaryCommandLine(String filePath) {
        File file = new File(filePath);
        if (file.length() != 0) {
            insertFromFile(filePath);
            sortListWord();
        }
    }

    /** Ver1: Print all the words with the given format. */
    public void showAllWords() {
        System.out.printf("%-10s%-25s%s\n", "No", "| English", "| Vietnamese");
        int i = 1;
        for (Word w : getListWord()) {
            w.printWord(i);
            i++;
        }
    }

    /** Ver1: Function dictionaryBasic() called: insertFromCommandline() and showAllWords(). */
    public void dictionaryBasic() {
        insertFromCommandLine();
        showAllWords();
    }

    /** Ver2: Function dictionaryAdvanced() called: insertFromFile(), showAllWords() and dictionaryLookup(). */
    public void dictionaryAdvanced() {
        //insertFromFile();
        showAllWords();
        //dictionaryLookup();
    }

    /** Find first index of word_target start with s. */
    public int findMinIndex(String s){
        int left = 0, right = getListWord().size() - 1;
        int mid, result = -1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (getListWord().get(mid).getWord_target().startsWith(s)) {
                result = mid;
                break;
            } else {
                if (getListWord().get(mid).getWord_target().compareTo(s) > 0) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        if (result != -1) {
            while (result > 0 && getListWord().get(result - 1).getWord_target().startsWith(s)){
                result--;
            }
        }
        return result;
    }

    /** Ver3: Print the list of word_target start with s. */
    public ArrayList<String> dictionarySearcher() {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int i = findMinIndex(s);
        ArrayList<String> list = new ArrayList<>();
        if (i == -1) {
            System.out.println("Not found.");
        } else {
            while (i < getListWord().size() && getListWord().get(i).getWord_target().startsWith(s)) {
                list.add(getListWord().get(i).getWord_target());
                i++;
            }
        }
        return list;
    }
}
