import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerMyWord implements Initializable{

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane grid;

    @FXML
    private TextField WordIn;

    @FXML
    void clickSearchBut(ActionEvent event) {

    }
    private ArrayList<Word> words = new ArrayList<>();
    private ArrayList<Word> getWords() {
        ArrayList<Word> words = new ArrayList<>();
        Word word;
        for (int i = 0; i < 10; i++) {
            word = new Word();
            word.setWord_target("Number " + String.valueOf(i));
            word.setWord_explain(String.valueOf(i));
            words.add(word);
        }
        return words;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        words.addAll(getWords());
        int row = 1;
        try {
            for(int i = 0; i < words.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Word.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                ControllerWord controller = fxmlLoader.getController();
                controller.setData(words.get(i));
                grid.add(anchorpane, 0, row);
                row++;

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorpane, new Insets(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
