import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.net.URL;

// Create a class to load multiple stages
public class FxmlLoader {
    private AnchorPane pane;
    // A method to get the stage
    public AnchorPane getPane(String fileFxml) {
        try {
            URL url = DictionaryApplication.class.getResource(fileFxml);
            if (url == null) {
                throw new FileNotFoundException("File is not found!");
            }
            pane = new FXMLLoader().load(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pane;
    }
}
