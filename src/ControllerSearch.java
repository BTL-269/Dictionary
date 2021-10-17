import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.*;

public class ControllerSearch {

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

    private DictionaryCommandLine listDic = new DictionaryCommandLine("dictionaries.txt");
    private DictionaryCommandLine listMyWord = new DictionaryCommandLine("listMyWord.txt");

    @FXML
    void clickSearchButton(ActionEvent event) {
        String target = inputWord.getText();
        int index = listDic.dictionaryLookup(target);
        if (index == -1) {
            outputText.setText("Not found");
        } else {
            Word word = listDic.getListWord().get(listDic.dictionaryLookup(target));
            outputText.setText(word.printWordExplain());
        }
    }

    @FXML
    void clickSpeaker(MouseEvent event) {
        Audio audio = new Audio(inputWord.getText());
    }

    @FXML
    void clickEdit(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Edit Word");
        ButtonType buttonTypeOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        GridPane gird = new GridPane();
        gird.setHgap(10);
        gird.setVgap(10);
        gird.setPadding(new Insets(20, 150, 10, 10));

        TextField target = new TextField();
        target.setText(inputWord.getText());
        TextArea explain = new TextArea();
        explain.setText(outputText.getText());

        gird.add(new Label("Target"), 0, 0);
        gird.add(target, 1, 0);
        gird.add(new Label("Explain"), 0, 1);
        gird.add(explain, 1, 1);

        alert.getDialogPane().setContent(gird);
        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOK) {
            String word_target = target.getText();
            String word_explain = convertExplain(explain.getText());
            if (word_explain.isEmpty() || word_target.isEmpty()) {
                notification("You have not entered the target or the explain");
            } else {
                int index = listDic.dictionaryLookup(word_target);
                if (index == -1) {
                    notification("There is no '" + word_target + "' in the dictionary");
                } else {
                    listDic.editWord(target.getText(), word_explain, index);
                    listDic.dictionaryExportToFile("dictionaries.txt");
                }
            }
        }
    }

    @FXML
    void clickLike(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Add Word into My Favorite");
        ButtonType buttonTypeOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        GridPane gird = new GridPane();
        gird.setHgap(10);
        gird.setVgap(10);
        gird.setPadding(new Insets(20, 150, 10, 10));

        TextField target = new TextField();
        target.setText(inputWord.getText());
        TextField explain = new TextField();

        gird.add(new Label("Target"), 0, 0);
        gird.add(target, 1, 0);
        gird.add(new Label("Explain"), 0, 1);
        gird.add(explain, 1, 1);

        alert.getDialogPane().setContent(gird);
        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOK) {
            String word_target = target.getText();
            String word_explain = convertExplain(explain.getText());
            if (word_explain.isEmpty() || word_target.isEmpty()) {
                notification("You have not entered the target or the explain");
            } else {
                int index = listMyWord.dictionaryLookup(word_target);
                if (index == -1) {
                    listMyWord.addWord(new Word(word_target, word_explain));
                    listMyWord.dictionaryExportToFile("listMyWord.txt");
                } else {
                    if (index != -1 && listMyWord.getListWord().get(index).getWord_explain().compareTo(word_explain) == 0) {
                        notification("This word is already in the My Favorite");
                    } else {
                        listMyWord.addWord(new Word(word_target, word_explain));
                        listMyWord.dictionaryExportToFile("listMyWord.txt");
                    }
                }
            }
        }
    }

    @FXML
    void clickDelete(MouseEvent event) {
        String wordDel = inputWord.getText();
        if (wordDel.isEmpty()) {
            notification("You have not entered the word");
            return;
        }
        int index = listDic.dictionaryLookup(wordDel);
        if (index != -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete Word");
            alert.setHeaderText("Are you sure you want to delete \'" + wordDel + "\'");
            ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                listDic.removeWord(wordDel);
                listDic.dictionaryExportToFile("dictionaries.txt");
            }
        } else {
            notification("No data for word '" + wordDel + "'");
        }
    }

    /**
     * Tạo thông báo "message".
     */
    public void notification(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(message);
        alert.show();
    }

    /**
     * covert Explain to format.
     */
    public String convertExplain(String explain) {
        explain = explain.replaceAll("\n\t- ", "n-");
        explain = explain.replaceAll("\n\t\t[+] ", "n=");
        explain = explain.replaceAll("\n", "n*");
        explain = explain.replaceAll("\t", "");
        return explain;
    }
}
