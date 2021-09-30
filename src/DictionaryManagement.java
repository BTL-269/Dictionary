import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DictionaryManagement extends Dictionary {
    /**
     * ver 1: Nhập số lượng từ, nhập từ (tiếng anh, tiếng việt).
     * Add new word into listWord.
     */
    public void insertFromCommandLine() {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < num; i++) {
            String target = sc.nextLine();
            String explain = sc.nextLine();
            Word new_word = new Word(target, explain);
            addWord(new_word);
        }
        sortListWord();
    }

    /** ver 2: nhập từ từ file dictionaries.txt. */
    public void insertFromFile() {
        try {
            File dictionaries = new File("dictionaries.txt");
            Scanner sc = new Scanner((dictionaries));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] word = line.split("\t");
                addWord(new Word(word[0], word[1]));
            }
            sortListWord();
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // PB2: tra cứu từ
    public void dictionaryLookup() {

    }
}
