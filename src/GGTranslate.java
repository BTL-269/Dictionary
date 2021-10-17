import com.darkprograms.speech.translator.GoogleTranslate;

public class GGTranslate {

    /** Search by Google Translate.*/
    public static String transText(String langOut, String text) {
        String ans = "";
        try {
            String[] listRow = text.split("\n");
            for (String s : listRow) {
                String[] list = s.split("\\.");
                for (String t : list) {
                    ans += GoogleTranslate.translate(langOut, t);
                }
                ans += "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }
}
