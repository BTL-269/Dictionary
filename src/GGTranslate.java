import com.darkprograms.speech.translator.GoogleTranslate;

public class GGTranslate {

    public String transText(String langOut, String target) {
        String ans = "";
        try {
            ans = GoogleTranslate.translate(langOut, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }
}
