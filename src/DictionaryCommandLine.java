public class DictionaryCommandLine extends DictionaryManagement{

    /** Print all the words in the given format (ver 1). */
    public void showAllWords() {
        System.out.printf("%-10s%-25s%s\n", "No", "| English", "| Vietnamese");
        int i = 1;
        for (Word w : getListWord()) {
            w.printWord(i);
            i++;
        }
    }

    /** ver 1: Hàm dictionaryBasic() gọi 2 hàm: insertFromCommandline() và showAllWords(). */
    public void dictionaryBasic() {
        insertFromCommandLine();
        showAllWords();
    }
}
