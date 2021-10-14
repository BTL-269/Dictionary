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
        System.out.printf("%-10s%-25s%s\n", i, "| " + this.getWord_target(), "| " + this.printWordExplain());
    }

    /** Print Explain to format. */
    public String printWordExplain() {
        String explain = getWord_explain();
        explain = explain.replaceFirst("@", "");
        explain = explain.replaceAll("(Chuyên ngành).+(n-)", "");
        explain = explain.replaceAll("n@", "\n");
        explain = explain.replaceAll("n\\*", "\n");
        explain = explain.replaceAll("n-", "\n\t- ");
        explain = explain.replaceAll("n=","\n\t\t+ ");
        explain = explain.replaceAll("n!","\n\t\t+ ");
        explain = explain.replace('\\',' ');
        if (explain.startsWith(getWord_target())) {
            explain = explain.substring(getWord_target().length());
        }
        return explain;
    }

    @Override
    public int compareTo(Word other) {
        return this.word_target.compareTo(other.getWord_target());
    }

    @Override
    public String toString() {
        return word_target + "          " + word_explain;
    }
}
