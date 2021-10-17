import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.util.Optional;

public class ControllerAdd {

    @FXML
    private TextField englishWord;

    @FXML
    private TextArea vietnameseWord;

    private DictionaryCommandLine listDic = new DictionaryCommandLine("dictionaries.txt");

    @FXML
    void clickAddWord(ActionEvent event) {
        int index = listDic.dictionaryLookup(englishWord.getText());
        if (index != -1) {
            Alert.AlertType alertAlertType;
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("This word is already in the dictionary!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Warning information");
            alert.setContentText("Are you sure to add this word to the dictionary?");

            ButtonType buttonYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
            ButtonType buttonCancel = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonYes, buttonCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get().getButtonData() == ButtonBar.ButtonData.YES) {
                String explain = convertExplain(vietnameseWord.getText());
                listDic.addWord(new Word(englishWord.getText(), explain));
                listDic.sortListWord();
                listDic.dictionaryExportToFile("dictionaries.txt");
            }
        }
    }
    /** Convert the style of word.*/

    public String convertExplain(String explain) {
        explain = explain.replaceAll("\n\t- ", "n-");
        explain = explain.replaceAll("\n\t\t[+] ","n=");
        explain = explain.replaceAll("\n", "n*");
        explain = explain.replaceAll("\t", "");
        return explain;
    }
}

