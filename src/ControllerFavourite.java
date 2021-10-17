import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerFavourite implements Initializable {

    @FXML
    private TextField WordIn;

    @FXML
    private ImageView delete;

    @FXML
    private TableView<Word> listWord;

    @FXML
    private TableColumn<Word, String> english;

    @FXML
    private TableColumn<Word, String> vietnamese;

    private DictionaryCommandLine dic = new DictionaryCommandLine("listFavourite.txt");
    private ObservableList<Word> list = FXCollections.observableArrayList(dic.getListWord());

    @FXML
    void clickSearchBut(ActionEvent event) {
        String s = WordIn.getText();
        int index = dic.dictionaryLookup(s);
        if (index == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("Not found!");
            alert.show();
        } else {
            listWord.getItems().clear();
            listWord.getItems().addAll(dic.getListWord().get(index));
        }
    }

    @FXML
    public void textChange(KeyEvent event) {
        if (WordIn.getText().isEmpty()) {
            list = FXCollections.observableArrayList(dic.getListWord());
            english.setCellValueFactory(new PropertyValueFactory<Word, String>("word_target"));
            vietnamese.setCellValueFactory(new PropertyValueFactory<Word, String>("word_explain"));
            listWord.setItems(list);
        }
    }
    @FXML
    void clickDelete(MouseEvent event) {
        delete.setOnMouseClicked(MouseEvent -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete Word");
            alert.setHeaderText("Are you sure you to delete this word?");
            ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get().getButtonData() == ButtonBar.ButtonData.YES) {
                if (!listWord.getSelectionModel().isEmpty()) {
                    Word w = listWord.getSelectionModel().getSelectedItem();
                    dic.removeWord(w.getWord_target());
                    dic.dictionaryExportToFile("listFavourite.txt");
                    list.remove(listWord.getSelectionModel().getSelectedItem());
                }
                listWord.getSelectionModel().clearSelection();
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        english.setCellValueFactory(new PropertyValueFactory<Word, String>("word_target"));
        vietnamese.setCellValueFactory(new PropertyValueFactory<Word, String>("word_explain"));
        listWord.setItems(list);
    }
}
