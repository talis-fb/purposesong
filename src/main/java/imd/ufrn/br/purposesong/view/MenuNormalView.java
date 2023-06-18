package imd.ufrn.br.purposesong.view;

import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.player.SongPlayer;
import imd.ufrn.br.purposesong.utils.SongCellFactory;
import imd.ufrn.br.purposesong.view.session.SongStore;
import imd.ufrn.br.purposesong.view.session.UserStore;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuNormalView implements Initializable {
    private MenuNormalViewModel viewModel = MenuNormalViewModel.getInstance();

    @FXML
    private ListView<Song> songView;

    @FXML
    private ImageView atual_imagem;

    @FXML
    private Label myLabel;

    @FXML
    private VBox currentSong;

    @FXML
    private Button play;

    @FXML
    private Label nameActiveUser;

    @FXML
    private ImageView buttonPlay;

    @FXML
    protected void playSong() {
        try {
            if (!SongPlayer.getInstance().isPlaying()) {
                buttonPlay.setImage(new Image("file:src/main/resources/imd/ufrn/br/purposesong/images/pausa.png"));
                this.viewModel.playSong(songView.getSelectionModel().getSelectedItem());
            } else {
                buttonPlay.setImage(new Image("file:src/main/resources/imd/ufrn/br/purposesong/images/toque.png"));
                this.viewModel.stopSong();
            }
        } catch (Exception e) {
            System.out.println("We are still working on this....");
            buttonPlay.setImage(new Image("file:src/main/resources/imd/ufrn/br/purposesong/images/toque.png"));
        }
    }

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
    }

    @FXML
    protected void addNewFolder() {
        this.viewModel.addNewFolder();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var songStore = SongStore.getInstance();
        var userStore = UserStore.getInstance();

        songView.itemsProperty().bind(songStore.songs);
        songView.cellFactoryProperty().set(new SongCellFactory());

        nameActiveUser.textProperty().bind(userStore.activeUserLabelName);

        /*
         * // ! Properties of playlist and song listViews
         * SongView.getSelectionModel().selectedItemProperty().addListener(new
         * ChangeListener<String>() {
         * 
         * @Override
         * public void changed(ObservableValue<? extends String> arg0, String arg1,
         * String arg2) {
         * currentSong.visibleProperty().set(true);
         * viewModel.setCurrentSong(SongView.getSelectionModel().getSelectedItem());
         * try {
         * atual_imagem.setImage(viewModel.getImages().get(SongView.getSelectionModel().
         * getSelectedIndex()));
         * } catch (Exception e) {
         * atual_imagem
         * .setImage(new Image(viewModel.getDefaultImage()));
         * }
         * myLabel.setText(viewModel.getCurrentSong());
         * 
         * ;
         * }
         * });
         */

    }
}
