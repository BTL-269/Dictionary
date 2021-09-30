import java.io.*;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    /**
     * Ver 1: Input number of words, target, explain.
     * Add new word into listWord./
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

    /** Ver 2: Read word from file dictionaries.txt. */
    public void insertFromFile() {
        try {
            File dictionaries = new File("dictionaries.txt");
            Scanner sc = new Scanner(dictionaries);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                String[] words = s.split("\t");
                Word w = new Word(words[0], words[1]);
                addWord(w);
            }
            sortListWord();
            sc.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    /** Ver2: Look up explain from the input target. */
    public int dictionaryLookup(String target) {
        return 1;
    }

    /** Ver3: Remove word. */
    public void removeWord(String target) {
        int index = dictionaryLookup(target);
        if (index != -1) {
            getListWord().remove(index);
        } else {
            System.out.printf("%s '%s' %s", "Not have", target, "in dictionary.");
        }
    }

    /** Ver3: Edit word. */
    public void editWord(String target) {
        Scanner sc = new Scanner(System.in);
        String explain = sc.nextLine();
        Word w = new Word(target, explain);
        removeWord(target);
        addWord(w);
    }

    /** Ver3: Write dictionary to file. */
    public void dictionaryExportToFile() {
        try {
            FileWriter fw = new FileWriter("dictionaries.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Word w : getListWord()) {
                bw.write(w.getWord_target() + '\t');
                bw.write(w.getWord_explain() + '\n');
            }
            bw.close();
            fw.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }
}
