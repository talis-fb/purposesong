package imd.ufrn.br.purposesong.view;

import java.util.List;
import java.util.UUID;

import imd.ufrn.br.purposesong.App;
import imd.ufrn.br.purposesong.database.RepositoryFactory;
import imd.ufrn.br.purposesong.entity.Playlist;
import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.use_case.GetAllUserPlaylists;
import imd.ufrn.br.purposesong.utils.OpenChooseFileDialog;
import imd.ufrn.br.purposesong.utils.OpenChooseFolderDialog;
import imd.ufrn.br.purposesong.utils.UserAlerts;
import imd.ufrn.br.purposesong.view.session.PlaylistStore;
import imd.ufrn.br.purposesong.view.session.SongStore;
import imd.ufrn.br.purposesong.view.session.UserStore;

public class MenuVipViewModel {
    private App app = App.getInstance();
    private SongStore songStore = SongStore.getInstance();
    private PlaylistStore playlistStore = PlaylistStore.getInstance();

    // !Playlists -----
    public Playlist addNewPlaylist(UUID userID, String name, List<Song> list) {
        // !Setting playlist
        Playlist playlist = new Playlist();
        playlist.setUserID(userID);
        playlist.setName(name);
        playlist.setSongsList(list);

        var saveInDB = this.playlistStore.savePlaylistInDB(playlist);
        return saveInDB;
    }

    public List<Playlist> getUserPlaylists() {
        return new GetAllUserPlaylists(RepositoryFactory.getPlaylistRepository())
                .execute(UserStore.getInstance().getUser().get());
    }

    // Player -------
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
