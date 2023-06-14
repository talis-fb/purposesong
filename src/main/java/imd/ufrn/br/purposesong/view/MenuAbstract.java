package imd.ufrn.br.purposesong.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/*It wasnt implemented yet */

public class MenuAbstract {

    private MenuAbstractModel viewModel = new MenuAbstractModel();
    @FXML
    private ListView<String> SongView;

    @FXML
    private ImageView atual_imagem;

    @FXML
    private Label myLabel;

    @FXML
    private VBox currentSong;

    @FXML
    private Button play;

    @FXML
    private Label nameActiiveUser;

    @FXML
    protected void goToLogin() {
        currentSong.visibleProperty().set(false);
        this.viewModel.goToLogin();

    }

    @FXML
    protected void goToSettings() {
        this.viewModel.goToSettings();
    }

    @FXML
    protected void addNewFile() {
        this.viewModel.addNewFile();
        this.viewModel.updateListSongName();
    }

    @FXML
    protected void addNewFolder() {
        this.viewModel.addNewFolder();
    }

}
