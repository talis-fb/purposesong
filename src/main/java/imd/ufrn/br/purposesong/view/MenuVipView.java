package imd.ufrn.br.purposesong.view;

import imd.ufrn.br.purposesong.view.session.SongStore;
import imd.ufrn.br.purposesong.view.session.UserStore;
import javafx.beans.Observable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import imd.ufrn.br.purposesong.database.RepositoryFactory;
import imd.ufrn.br.purposesong.entity.Folder;
import imd.ufrn.br.purposesong.entity.Playlist;
import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.player.SongPlayer;
import imd.ufrn.br.purposesong.use_case.GetAllSongsOfUser;
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
    protected void deleteSong() {
        if (!newPlaylistField.getItems().isEmpty()) {
            var song = newPlaylistField.getSelectionModel().getSelectedItem();
            newPlaylistField.getItems().remove(song);
        }
    }

    @FXML
    protected void addPlaylist() {
        ArrayList<Song> newList = new ArrayList<Song>();
        newList.addAll(newPlaylistField.getItems());

        if (newPlaylistNameField.getText().isEmpty() || newPlaylistField.getItems().isEmpty())
            UserAlerts.alertEmpytUser(); // !MUDARR
        else {

            var newPlaylist = this.viewModel.addNewPlaylist(UserStore.getInstance().getUser().get().getId().get(),
                    newPlaylistNameField.getText().toString(), newList);
            if (newPlaylist != null) {
                playlistView.getItems().add(newPlaylist);
                newPlaylistNameField.clear();
                newPlaylistField.getItems().clear();
                openCreatePlaylist();
            } else
                System.out.println("Não adicionou amigo");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var songStore = SongStore.getInstance();
        var userStore = UserStore.getInstance();

        songView.itemsProperty().bindBidirectional(songStore.songs);
        songView.cellFactoryProperty().set(new SongCellFactory());
        newPlaylistField.cellFactoryProperty().set(new SongCellFactory());
        playlistView.cellFactoryProperty().set(new PlaylistCellFactory());

        nameActiveUser.textProperty().bindBidirectional(userStore.activeUserLabelName);
        playlistView.getItems().addAll(viewModel.getUserPlaylists());

        songView.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start a drag-and-drop gesture */
                /* allow any transfer mode */
                Dragboard db = songView.startDragAndDrop(TransferMode.ANY);

                /* Put a SONGs on a dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(songView.getSelectionModel().getSelectedItem().getId().get().toString());
                // content.putUrl(songView.getSelectionModel().getSelectedItem().path);
                db.setContent(content);
                System.out.println("Opa! Tenho: " + content.getString());
                event.consume();
            }
        });

        newPlaylistField.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                System.out.println("Eu sinto você!!s");
                /* data is dragged over the target */
                /*
                 * accept it only if it is not dragged from the same node
                 * and if it has a string data
                 */
                if (event.getGestureSource() != newPlaylistField &&
                        event.getDragboard().hasString()) {
                    System.out.println("Recebendoo");
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        newPlaylistField.setOnDragEntered(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the target */
                /* show to the user that it is an actual gesture target */
                if (event.getGestureSource() != newPlaylistField &&
                        event.getDragboard().hasString()) {
                    // newPlaylistField.setFill(Color.GREEN);
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
                    var song = RepositoryFactory.getSongRepository().findById(UUID.fromString(db.getString()));
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

        songView.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag and drop gesture ended */
                /* if the data was successfully moved, clear it */
                System.out.println("AGORA");
                if (event.getTransferMode() == TransferMode.MOVE) {
                    // songStore.songs.remove(songView.getSelectionModel().getSelectedIndex());
                    // songView.getItems().remove(songView.getSelectionModel().getSelectedIndex());
                }
                event.consume();
            }
        });

    }
}
