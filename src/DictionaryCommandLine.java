import java.util.Scanner;

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
        //insertFromCommandLine();
        showAllWords();
    }

    /** tìm vị trí từ đầu tiên chứa xâu s ở đầu. */
    public int findMinIndex(String s){
        int i = -1;
        int left = 0;
        int right = getListWord().size() - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (getListWord().get(mid).getWord_target().startsWith(s)) {
                i = mid;
                break;
            } else {
                if (getListWord().get(mid).getWord_target().compareTo(s) > 0) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        if (i != -1) {
            while (i > 0 && getListWord().get(i - 1).getWord_target().startsWith(s)){
                i--;
            }
        }
        return i;
    }

    /** Ver 3: tìm kiếm từ bắt đầu từ xâu cho trước. */
    void dictionarySearcher() {
        String s;
        Scanner sc = new Scanner(System.in);
        s = sc.next();
        int i = findMinIndex(s);
        if (i == -1) {
            System.out.println("Not found.");
        } else {
            while (i < getListWord().size() && getListWord().get(i).getWord_target().startsWith(s)) {
                System.out.printf("%-20s%s\n", getListWord().get(i).getWord_target(), getListWord().get(i).getWord_explain());
                i++;
            }
        }
    }
}
