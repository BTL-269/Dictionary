import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerSearch implements Initializable {

    @FXML
    private TextField inputWord;

    @FXML
    private TextArea outputText;

    private final DictionaryCommandLine listDic = new DictionaryCommandLine("dictionaries.txt");
    private final DictionaryCommandLine listFavourite = new DictionaryCommandLine("listFavourite.txt");
    private static final int NUM_OF_HISTORY = 30;
    private ArrayList<String> listDicSearch = new ArrayList<>();

    @FXML
    private ListView<String> listSearch = new ListView<>();
    private ObservableList<String> list = FXCollections.observableArrayList(listDicSearch);

    @FXML
    public void clickSearchButton(ActionEvent event) {
        String target = inputWord.getText();
        listSearch.setVisible(false);
        int index = listDic.dictionaryLookup(target);
        if (index == -1) {
            outputText.setText("Not found.");
        } else {
            // Print word_explain at outputText
            Word word = listDic.getListWord().get(listDic.dictionaryLookup(target));
            outputText.setText(word.printWordExplain());
            // add word into History
            addHistory(word);
        }
    }

    @FXML
    public void clickSpeaker(MouseEvent event) {
        Audio audio = new Audio(inputWord.getText());
    }

    @FXML
    public void clickEdit(MouseEvent event) {
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
        alert.getButtonTypes().setAll(buttonTypeOK , buttonTypeCancel);
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
    public void clickLike(MouseEvent event) {
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
        alert.getButtonTypes().setAll(buttonTypeOK , buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOK) {
            String word_target = target.getText();
            String word_explain = convertExplain(explain.getText());
            if (word_explain.isEmpty() || word_target.isEmpty()) {
                notification("You have not entered the target or the explain");
            } else {
                int index = listFavourite.dictionaryLookup(word_target);
                if (index == -1) {
                    listFavourite.addWord(new Word(word_target, word_explain));
                    listFavourite.dictionaryExportToFile("listFavourite.txt");
                } else {
                    if (index != -1 && listFavourite.getListWord().get(index).getWord_explain().compareTo(word_explain) == 0) {
                        notification("This word is already in the My Favorite");
                    } else {
                        listFavourite.addWord(new Word(word_target, word_explain));
                        listFavourite.dictionaryExportToFile("listFavourite.txt");
                    }
                }
            }
        }
    }

    @FXML
    public void clickDelete(MouseEvent event) {
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

    @FXML
    public void listView(KeyEvent event) {
        listDic.dictionarySearcher(inputWord.getText().toString());
        list = FXCollections.observableArrayList(listDic.dictionarySearcher(inputWord.getText()));
        listSearch.setItems(list);
        if (event.getCode() != KeyCode.ENTER) {
            listSearch.setVisible(true);
        }
    }

    @FXML
    public void search(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            listSearch.setVisible(false);
            String target = inputWord.getText();
            int index = listDic.dictionaryLookup(target);
            if (index == -1) {
                outputText.setText("Not found");
            } else {
                Word word = listDic.getListWord().get(listDic.dictionaryLookup(target));
                outputText.setText(word.printWordExplain());
                addHistory(word);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listSearch.setVisible(false);
        listSearch.setItems(list);
    }

    @FXML
    public void selected(Event event) {
        String selected = listSearch.getSelectionModel().getSelectedItem();
        if (!selected.isEmpty()) {
            inputWord.setText(selected);
            Word word = listDic.getListWord().get(listDic.dictionaryLookup(selected));
            outputText.setText(word.printWordExplain());
            addHistory(word);
            listSearch.setVisible(false);
        }
    }

    /** T???o th??ng b??o "message". */
    public void notification (String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(message);
        alert.show();
    }

    /** covert Explain to format. */
    public String convertExplain(String explain) {
        explain = explain.replaceAll("\n\t- ", "n-");
        explain = explain.replaceAll("\n\t\t[+] ","n=");
        explain = explain.replaceAll("\n", "n*");
        explain = explain.replaceAll("\t", "");
        return explain;
    }

    /** Add word into listHistory. */
    public void addHistory(Word word) {
        // Add searched word into listHistory.
        DictionaryCommandLine listHistory = new DictionaryCommandLine();

        // Find index of target in listHistory.
        int index = -1;
        for (Word w : listHistory.getListWord()) {
            if (w.getWord_target().equals(word.getWord_target())) {
                index = listHistory.getListWord().indexOf(w);
                break;
            }
        }
        if (index == -1) {
            if (listHistory.getListWord().size() == NUM_OF_HISTORY) {
                listHistory.getListWord().remove(NUM_OF_HISTORY - 1);
            }
        } else {
            listHistory.getListWord().remove(index);
        }
        listHistory.getListWord().add(0, word);
        listHistory.dictionaryExportToFile("listHistory.txt");
    }
}
