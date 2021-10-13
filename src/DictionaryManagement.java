import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
    public void insertFromFile(String fileName) {
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader buffer = new BufferedReader(reader);
            String s = buffer.readLine();
            String[] word1 = s.split("\t");
            while ((s = buffer.readLine()) != null) {
                String[] word2 = s.split("\t");
                while (word2.length != 2) {
                    word1[1] += s;
                    s = buffer.readLine();
                    word2 = s.split("\t");
                }
                Word w = new Word(word1[0], word1[1]);
                listWord.add(w);
                word1 = word2;
            }
            sortListWord();
            buffer.close();
            reader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    /**
     * Ver 2: Look up word from the input.
     * @return the index of word in list
     */
    public int dictionaryLookup(String word) {
        int left = 0, right = getListWord().size() - 1;
        int mid, result = -1;
        while (left <= right) {
            mid = (right + left) / 2;
            int i = word.compareTo(getListWord().get(mid).getWord_target());
            if (i == 0) {
                result = mid;
                return result;
            } else if (i < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    /** Ver3: Remove word. */
    public void removeWord(String target) {
        int index = dictionaryLookup(target);
        if (index != -1) {
            getListWord().remove(index);
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
    public void dictionaryExportToFile(String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter buffer = new BufferedWriter(writer);
            for (Word w : getListWord()) {
                buffer.write(w.getWord_target() + '\t');
                buffer.write(w.getWord_explain() + '\n');
            }
            buffer.close();
            writer.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }
}
