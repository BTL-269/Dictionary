import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class ControllerSearch  {

    @FXML
    private ImageView edit;

    @FXML
    private ImageView like;

    @FXML
    private ImageView speaker;

    @FXML
    private TextField inputWord;

    @FXML
    private TextArea outputText;

    @FXML
    private ImageView delete;
    public DictionaryManagement listDic = new DictionaryManagement();

    @FXML
    void clickSearchButton(ActionEvent event) {
        listDic.insertFromFile();
        String s = inputWord.getText();
        Word word = listDic.getListWord().get(listDic.dictionaryLookup(s));
        outputText.setText(word.getWord_explain());
    }

    @FXML
    void clickSpeaker(MouseEvent event) {

    }

    @FXML
    void clickEdit(MouseEvent event) {

    }

    @FXML
    void clickLike(MouseEvent event) {

    }

    @FXML
    void clickDelete(MouseEvent event) {

    }

}
