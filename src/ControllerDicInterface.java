import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ControllerDicInterface {

    @FXML
    private BorderPane borderPane;

    @FXML
    void clickSearch(ActionEvent event) {
        FxmlLoader loader = new FxmlLoader();
        AnchorPane newPane = loader.getPane("Search.fxml");
        borderPane.setCenter(newPane);
    }

    @FXML
    void clickFavoriteWords(ActionEvent event) {
        FxmlLoader loader = new FxmlLoader();
        AnchorPane newPane = loader.getPane("MyWord.fxml");
        borderPane.setCenter(newPane);
    }

    @FXML
    void clickGoogleDich(ActionEvent event) {
        FxmlLoader loader = new FxmlLoader();
        AnchorPane newPane = loader.getPane("GoogleDich.fxml");
        borderPane.setCenter(newPane);
    }

    @FXML
    void clickRecentWords(ActionEvent event) {
        FxmlLoader loader = new FxmlLoader();
        AnchorPane newPane = loader.getPane("History.fxml");
        borderPane.setCenter(newPane);
    }

    @FXML
    void clickAdd(ActionEvent event) {
        FxmlLoader loader = new FxmlLoader();
        AnchorPane newPane = loader.getPane("Add.fxml");
        borderPane.setCenter(newPane);
    }
}
