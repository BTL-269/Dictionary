import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ControllerDicInterface {

    @FXML
    private BorderPane borderPane;

    @FXML
    public void clickSearch(ActionEvent event) {
        FxmlLoader loader = new FxmlLoader();
        AnchorPane newPane = loader.getPane("Search.fxml");
        borderPane.setCenter(newPane);
    }

    @FXML
    public void clickFavorite(ActionEvent event) {
        FxmlLoader loader = new FxmlLoader();
        AnchorPane newPane = loader.getPane("Favourite.fxml");
        borderPane.setCenter(newPane);
    }

    @FXML
    public void clickGoogleTranslate(ActionEvent event) {
        FxmlLoader loader = new FxmlLoader();
        AnchorPane newPane = loader.getPane("GoogleTranslate.fxml");
        borderPane.setCenter(newPane);
    }

    @FXML
    public void clickRecent(ActionEvent event) {
        FxmlLoader loader = new FxmlLoader();
        AnchorPane newPane = loader.getPane("History.fxml");
        borderPane.setCenter(newPane);
    }

    @FXML
    public void clickAdd(ActionEvent event) {
        FxmlLoader loader = new FxmlLoader();
        AnchorPane newPane = loader.getPane("Add.fxml");
        borderPane.setCenter(newPane);
    }

    @FXML
    public void clickAbout(ActionEvent event) {
        FxmlLoader loader = new FxmlLoader();
        AnchorPane newPane = loader.getPane("About.fxml");
        borderPane.setCenter(newPane);
    }
}
