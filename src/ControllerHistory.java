import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerHistory implements Initializable {

    @FXML
    private TextField WordIn;

    @FXML
    private TreeView<String> treeView;

    @FXML
    void clickSearchBut(ActionEvent event) {

    }

    private DictionaryCommandLine listHistory = new DictionaryCommandLine();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> root = new TreeItem<>("HistoryWords");

        for (Word w : listHistory.getListWord()) {
            TreeItem<String> branch = new TreeItem<>(w.getWord_target());
            TreeItem<String> branchItem = new TreeItem<>(w.printWordExplain());
            branch.getChildren().add(branchItem);
            root.getChildren().add(branch);
        }

        treeView.setRoot(root);
        treeView.setShowRoot(false);
    }
}
