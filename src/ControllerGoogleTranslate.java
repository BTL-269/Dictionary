import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ControllerGoogleTranslate {

    @FXML
    private TextArea output;

    @FXML
    private TextArea input;

    @FXML
    void clickTranslate(ActionEvent event) {
        output.setText(GGTranslate.transText("vi", input.getText()));
    }

}
