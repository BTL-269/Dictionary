public class Word implements Comparable<Word> {
    private String word_target;
    private String word_explain;

    /** Constructor 1.*/
    public Word() {}

    /** Constructor 2. */
    public Word(String target, String explain) {
        this.word_target = target;
        this.word_explain = explain;
    }

    /** Getter & Setter of word_target. */
    public void setWord_target(String target) {
        this.word_target = target;
    }

    public String getWord_target() {
        return this.word_target;
    }

    /** Getter & Setter of word_explain. */
    public void setWord_explain(String explain) {
        this.word_explain = explain;
    }

    public String getWord_explain() {
        return this.word_explain;
    }

    /** Print word. */
    public void printWord(int i) {
        System.out.printf("%-10s%-25s%s\n", i, "| " + this.getWord_target(), "| " + this.getWord_explain());
    }

    @Override
    public int compareTo(Word other) {
        return this.word_target.compareTo(other.getWord_target());
    }
}
