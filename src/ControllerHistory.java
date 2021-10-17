import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerHistory implements Initializable {

    @FXML
    private TextField inputWord;

    @FXML
    private TreeView<String> treeView;

    private final DictionaryCommandLine listHistory = new DictionaryCommandLine();

    @FXML
    public void clickSearchButton(ActionEvent event) {
        String target = inputWord.getText();
        TreeItem<String> root = new TreeItem<>("HistoryWords");

        // Find index of target in listHistory.
        int index = -1;
        for (Word w : listHistory.getListWord()) {
            if (w.getWord_target().equals(target)) {
                index = listHistory.getListWord().indexOf(w);
                break;
            }
        }

        // Show word.
        if (index == -1) {
            TreeItem<String> branch1 = new TreeItem<>("Not found.");
            root.getChildren().add(branch1);
        } else {
            Word word = listHistory.getListWord().get(index);
            TreeItem<String> branch2 = new TreeItem<>(word.getWord_target());
            TreeItem<String> branchItem = new TreeItem<>(word.printWordExplain());
            branch2.getChildren().add(branchItem);
            root.getChildren().add(branch2);
        }

        treeView.setRoot(root);
        treeView.setShowRoot(false);
    }

    @FXML
    public void textChange(KeyEvent event) {
        if (inputWord.getText().toString().isEmpty()) {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Show word of listHistory
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
