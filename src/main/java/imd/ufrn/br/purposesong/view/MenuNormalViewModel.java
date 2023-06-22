package imd.ufrn.br.purposesong.view;

import java.util.ArrayList;

import imd.ufrn.br.purposesong.App;
import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.utils.OpenChooseFileDialog;
import imd.ufrn.br.purposesong.utils.OpenChooseFolderDialog;
import imd.ufrn.br.purposesong.utils.UserAlerts;
import imd.ufrn.br.purposesong.view.session.SongStore;
import javafx.scene.image.Image;

public class MenuNormalViewModel {
    private App app = App.getInstance();
    private SongStore songStore = SongStore.getInstance();

    private ArrayList<Image> images;

    public ArrayList<Image> getImages() {
        return images;
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
    private static final MenuNormalViewModel instance = new MenuNormalViewModel();

    private MenuNormalViewModel() {
    }

    public static MenuNormalViewModel getInstance() {
        return MenuNormalViewModel.instance;
    }
}
