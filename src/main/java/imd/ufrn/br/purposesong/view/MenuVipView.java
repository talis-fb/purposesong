package imd.ufrn.br.purposesong.view;

import imd.ufrn.br.purposesong.view.session.PlaylistStore;
import imd.ufrn.br.purposesong.view.session.SongStore;
import imd.ufrn.br.purposesong.view.session.UserStore;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;

import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

import imd.ufrn.br.purposesong.database.RepositoryFactory;
import imd.ufrn.br.purposesong.entity.Playlist;
import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.player.SongPlayer;
import imd.ufrn.br.purposesong.utils.PlaylistCellFactory;
import imd.ufrn.br.purposesong.utils.SongCellFactory;
import imd.ufrn.br.purposesong.utils.UserAlerts;

public class MenuVipView implements Initializable {
    private MenuVipViewModel viewModel = MenuVipViewModel.getInstance();

    @FXML
    private ListView<Song> songView;

    @FXML
    private ListView<Playlist> playlistView;

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
    private TextField playlistNameField;

    @FXML
    private Pane paneCreateNewPlaylist;

    @FXML
    private ListView<Song> newPlaylistField;

    @FXML
    private TextField newPlaylistNameField;

    @FXML
    private Button addNewPlaylist;

    @FXML
    private Button buttonShowAllSongs;

    @FXML
    private Label currentSongName;

    private SongStore songStore = SongStore.getInstance();
    private UserStore userStore = UserStore.getInstance();
    private PlaylistStore playlistStore = PlaylistStore.getInstance();

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
    protected void deleteSong() {
        if (!newPlaylistField.getItems().isEmpty()) {
            var song = newPlaylistField.getSelectionModel().getSelectedItem();
            newPlaylistField.getItems().remove(song);
        }
    }

    @FXML
    protected void addPlaylist() {
        ArrayList<Song> songsSelectedToPlaylist = new ArrayList<Song>();
        songsSelectedToPlaylist.addAll(newPlaylistField.getItems());

        if (newPlaylistNameField.getText().isEmpty() || newPlaylistField.getItems().isEmpty())
            UserAlerts.alertEmpytFields();
        else {
            UUID userId = userStore.getUser().get().getId().get();
            String playlistName = newPlaylistNameField.getText();
            Playlist newPlaylist = this.viewModel.addNewPlaylist(userId, playlistName, songsSelectedToPlaylist);
            if (newPlaylist != null) {
                playlistView.getItems().add(newPlaylist);
                newPlaylistNameField.clear();
                newPlaylistField.getItems().clear();
                openCreatePlaylist();
            } else
                System.out.println("Não adicionou amigo");
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

    @FXML
    protected void openAlertAboutUs() {
        this.viewModel.openAlertAboutUs();
    }

    @FXML
    protected void openCreatePlaylist() {
        if (paneCreateNewPlaylist.isVisible())
            paneCreateNewPlaylist.setVisible(false);
        else
            paneCreateNewPlaylist.setVisible(true);
    }

    @FXML
    protected void showAllUserSongs() {
        this.viewModel.showAllSongsOfUser();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        songView.cellFactoryProperty().set(new SongCellFactory());
        newPlaylistField.cellFactoryProperty().set(new SongCellFactory());
        playlistView.cellFactoryProperty().set(new PlaylistCellFactory());

        songView.itemsProperty().bind(songStore.songs);
        nameActiveUser.textProperty().bind(userStore.activeUserLabelName);
        playlistView.itemsProperty().bind(playlistStore.playlists);

        playlistView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Playlist>() {
                    public void changed(ObservableValue<? extends Playlist> ov,
                            Playlist old_val, Playlist new_val) {
                        viewModel.showOnlySongsOfPlaylist(playlistView.getSelectionModel().getSelectedItem());
                    }
                });

        songView.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start a drag-and-drop gesture */
                /* allow any transfer mode */
                Dragboard db = songView.startDragAndDrop(TransferMode.ANY);

                /* Put a SONGs on a dragboard */
                ClipboardContent content = new ClipboardContent();

                Song songSelected = songView.getSelectionModel().getSelectedItem();

                content.putString(songSelected.getPath());
                db.setContent(content);
                event.consume();
            }
        });

        newPlaylistField.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                /*
                 * accept it only if it is not dragged from the same node
                 * and if it has a string data
                 */
                if (event.getGestureSource() != newPlaylistField &&
                        event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        newPlaylistField.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data dropped */
                /* if there is a string data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    var path = db.getString();
                    var song = songView.getItems().stream().filter(it -> it.getPath().equals(path)).findFirst();

                    if (newPlaylistField.getItems().contains(song.get())) {
                        UserAlerts.alertYouAlreadyAddedThisSong();
                    } else {
                        newPlaylistField.getItems().add(song.get());
                        success = true;
                        System.out.println("sETEI!");
                    }
                }
                /*
                 * let the source know whether the string was successfully
                 * transferred and used
                 */
                event.setDropCompleted(success);

                event.consume();
            }
        });
    }
}
