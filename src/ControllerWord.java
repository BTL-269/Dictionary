
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ControllerWord {

    @FXML
    private Label englishWord;

    @FXML
    private ImageView deleteImg;

    @FXML
    private Label vietnameseWord;
    private Word word;
    // A method to set data for word
    public void setData(Word word) {
        this.word = word;
        englishWord.setText(word.getWord_target());
        vietnameseWord.setText(word.getWord_explain());
    }

    @FXML
    void clickDelete(ActionEvent event) {

    }

}
