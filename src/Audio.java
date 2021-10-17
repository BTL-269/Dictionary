import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Audio {
    private static final String VOICENAME = "kevin16";
    /** Convert text to voice.*/

    public Audio(String textToSpeech) {
        try {
            VoiceManager voiceManager = VoiceManager.getInstance();
            Voice voice = voiceManager.getVoice(VOICENAME);
            voice.allocate();
            voice.speak(textToSpeech);
            voice.deallocate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
