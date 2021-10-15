import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerFavourite implements Initializable {

    @FXML
    private TextField WordIn;

    @FXML
    private ImageView delete;

    @FXML
    private ListView<Word> listWord = new ListView<>();

    private DictionaryCommandLine dic = new DictionaryCommandLine("listFavourite.txt");
    private ObservableList<Word> list = FXCollections.observableArrayList(dic.getListWord());

    @FXML
    void clickSearchBut(ActionEvent event) {
        String s = WordIn.getText();
    }

    @FXML
    void clickDelete(MouseEvent event) {
        delete.setOnMouseClicked(MouseEvent -> {
            if (!listWord.getSelectionModel().isEmpty()) {
                Word w = listWord.getSelectionModel().getSelectedItem();
                dic.removeWord(w.getWord_target());
                dic.dictionaryExportToFile("listFavourite.txt");
                list.remove(listWord.getSelectionModel().getSelectedItem());
            }
            listWord.getSelectionModel().clearSelection();
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listWord.setItems(list);
    }
}
