package imd.ufrn.br.purposesong.view;

import java.util.ArrayList;

import imd.ufrn.br.purposesong.App;
import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.utils.OpenChooseFileDialog;
import imd.ufrn.br.purposesong.utils.OpenChooseFolderDialog;
import imd.ufrn.br.purposesong.view.session.SongStore;
import javafx.scene.image.Image;

public class MenuVipViewModel {
    private App app = App.getInstance();
    private SongStore songStore = SongStore.getInstance();

    private ArrayList<String> playlists;
    private ArrayList<Image> images;

    public ArrayList<String> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<String> playlists) {
        this.playlists = playlists;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void initializePlaylists() {
        playlists = new ArrayList<>();
        playlists.add("Rock Pesadão");
        playlists.add("MPB");
        playlists.add("Paz que acalma a alma");
        playlists.add("Maranata ora vem!!");
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
    public void goToRegister() {
        this.app.changeToRegisterScene();
    }

    public void goToLogin() {
        this.app.changeToLoginScene();
    }

    public void goToSettings() {
        this.app.changeToSettingsScene();
    }

    // Singleton ---------------------------
    private static final MenuVipViewModel instance = new MenuVipViewModel();

    private MenuVipViewModel() {
        initializePlaylists();
    }

    public static MenuVipViewModel getInstance() {
        return MenuVipViewModel.instance;
    }
}
