package imd.ufrn.br.purposesong.view;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import imd.ufrn.br.purposesong.App;
import imd.ufrn.br.purposesong.database.PlaylistRepository;
import imd.ufrn.br.purposesong.database.RepositoryFactory;
import imd.ufrn.br.purposesong.entity.Playlist;
import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.use_case.AddPlaylist;
import imd.ufrn.br.purposesong.utils.OpenChooseFileDialog;
import imd.ufrn.br.purposesong.utils.OpenChooseFolderDialog;
import imd.ufrn.br.purposesong.utils.UserAlerts;
import imd.ufrn.br.purposesong.view.session.SongStore;
import javafx.scene.image.Image;

public class MenuVipViewModel {
    private App app = App.getInstance();
    private SongStore songStore = SongStore.getInstance();

    private ArrayList<String> playlists;
    private ArrayList<Image> images;

    public Playlist addNewPlaylist(UUID userID, String name, List<Song> list) {
        // !Setting playlist
        Playlist playlist = new Playlist();
        playlist.setId(userID);
        playlist.setName(name);
        playlist.setSongsList(list);

        if (UserAlerts.alertNewPlaylistConfirmation(playlist)) {
            // !Sending to DB
            var repo = RepositoryFactory.getPlaylistRepository();
            new AddPlaylist(repo).execute(playlist);
            return playlist;
        } else
            return null;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public List<Playlist> getUserPlaylists() {
        /** GetAllPlaylistsOfUser */

        return new ArrayList<Playlist>();
    }

    public void playSong(Song song) {
        this.songStore.playSong(song);
    }

    public void stopSong() {
        this.songStore.stopSong();
    }

    // Gerenciamento de arquivos ----------
    public void addNewFile() {
        var file = OpenChooseFileDialog.showChooseFileDialog(this.app.getStage());
        file.ifPresent(this.songStore::saveSongFileInDB);
    }

    public void addNewFolder() {
        var file = OpenChooseFolderDialog.showChooseFolderDialog(this.app.getStage());
        file.ifPresent(this.songStore::saveFolderInDB);
    }

    // Troca de tela ----------------

    public void goToLogin() {

        this.app.changeToLoginScene();
    }

    public void goToSettings() {
        this.app.changeToSettingsScene();
    }

    public void openAlertAboutUs() {
        UserAlerts.alertAboutUs();
    }

    // Singleton ---------------------------
    private static final MenuVipViewModel instance = new MenuVipViewModel();

    private MenuVipViewModel() {
    }

    public static MenuVipViewModel getInstance() {
        return MenuVipViewModel.instance;
    }
}
