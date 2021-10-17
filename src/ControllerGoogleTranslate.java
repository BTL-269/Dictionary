import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGoogleTranslate implements Initializable {

    @FXML
    private TextArea output;

    @FXML
    private TextArea input;

    @FXML
    private ComboBox<String> box1, box2;

    @FXML
    void clickTranslate(ActionEvent event) {
        if (box1.getSelectionModel().isSelected(1)) {
            output.setText(GGTranslate.transText("vi", input.getText()));
        } else  {
            output.setText(GGTranslate.transText("en", input.getText()));
        }
    }

    @FXML
    void clickSpeaker1() {
        if (box1.getSelectionModel().isSelected(0)) {
            new Audio(output.getText());
        }
    }

    @FXML
    void clickSpeaker2() {
        if (box2.getSelectionModel().isSelected(0)) {
            new Audio(input.getText());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList("Vietnamese", "English");
        box1.setItems(list);
        box1.getSelectionModel().select(0);

        box2.setItems(list);
        box2.getSelectionModel().select(1);
    }
}
