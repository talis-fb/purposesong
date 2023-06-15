package imd.ufrn.br.purposesong.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.player.SongPlayer;
import imd.ufrn.br.purposesong.utils.SongCellFactory;

public class MenuView implements Initializable {
    private MenuViewModel viewModel = MenuViewModel.getInstance();

    @FXML
    private ListView<Song> songView;

    @FXML
    private ListView<String> playlistView;

    @FXML
    private ImageView currentImage;

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
    private AnchorPane anchor;

    @FXML
    private void playSong() {
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
    protected void goToRegister() {
        this.viewModel.goToRegister();
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
        songView.itemsProperty().bind(this.viewModel.musicas);
        songView.cellFactoryProperty().set(new SongCellFactory());
        nameActiveUser.textProperty().bindBidirectional(
                MenuAbstractModel.ActiveUserLabelName == null ? new SimpleStringProperty("default")
                        : MenuAbstractModel.ActiveUserLabelName);

        // ! Properties of playlist and song listViews

        playlistView.getItems().addAll(viewModel.getPlaylists());
        playlistView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                // songView.getItems().add("Adicionando nova música...");
                ;
            }
        });

        playlistView.getSelectionModel().selectedItemProperty().addListener((e) -> {
            // Song e o troço ai q precisar botar na funcao
        });

        /*
         * songView.getSelectionModel().selectedItemProperty().addListener(new
         * ChangeListener<String>() {
         * 
         * @Override
         * public void changed(ObservableValue<? extends String> arg0, String arg1,
         * String arg2) {
         * currentSong.visibleProperty().set(true);
         * viewModel.setCurrentSong(songView.getSelectionModel().getSelectedItem());
         * try {
         * currentImage.setImage(viewModel.getImages().get(songView.getSelectionModel().
         * getSelectedIndex()));
         * } catch (Exception e) {
         * currentImage
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
