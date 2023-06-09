package imd.ufrn.br.purposesong.view;

import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.player.SongPlayer;
import imd.ufrn.br.purposesong.utils.SongCellFactory;
import imd.ufrn.br.purposesong.view.session.SongStore;
import imd.ufrn.br.purposesong.view.session.UserStore;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private Label currentSongName;

    @FXML
    private Label currentSongPath;

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

    private void resetPlayerIcon() {
        buttonPlay.setImage(new Image("file:src/main/resources/imd/ufrn/br/purposesong/images/toque.png"));
    }

    @FXML
    protected void goToLogin() {
        this.resetPlayerIcon();
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

    @FXML
    protected void openAlertAboutUs() {
        this.viewModel.openAlertAboutUs();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var songStore = SongStore.getInstance();
        var userStore = UserStore.getInstance();

        songView.itemsProperty().bind(songStore.songs);
        songView.cellFactoryProperty().set(new SongCellFactory());

        nameActiveUser.textProperty().bindBidirectional(userStore.activeUserLabelName);

        songView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>() {
            public void changed(ObservableValue<? extends Song> ov,
                    Song old_val, Song new_val) {
                if (!songView.getSelectionModel().isEmpty()) {
                    currentSongName.setText(songView.getSelectionModel().getSelectedItem().name);
                    currentSongPath.setText("Path: " + songView.getSelectionModel().getSelectedItem().path);
                } else {
                    currentSongName.setText(" ");
                    currentSongPath.setText(" ");
                }
            }
        });
    }
}
