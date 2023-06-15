package imd.ufrn.br.purposesong.view;

import java.io.File;
import java.util.List;

import imd.ufrn.br.purposesong.App;
import imd.ufrn.br.purposesong.UserSession;
import imd.ufrn.br.purposesong.database.inmemory.InMemoryFolderRepositoryImpl;
import imd.ufrn.br.purposesong.database.inmemory.InMemorySongRepositoryImpl;
import imd.ufrn.br.purposesong.entity.Folder;
import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.player.SongPlayer;
import imd.ufrn.br.purposesong.use_case.AddFolder;
import imd.ufrn.br.purposesong.use_case.AddSong;
import imd.ufrn.br.purposesong.use_case.GetAllSongsOfFolder;
import imd.ufrn.br.purposesong.use_case.GetAllSongsOfUser;
import imd.ufrn.br.purposesong.utils.OpenChooseFileDialog;
import imd.ufrn.br.purposesong.utils.OpenChooseFolderDialog;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class MenuAbstractModel {
    public ListProperty<Song> musicas = new SimpleListProperty<Song>(FXCollections.observableArrayList());
    private String default_song_image = "file:src/main/resources/imd/ufrn/br/purposesong/images/default.png";
    private String current_song = "";
    public ListProperty<String> songNames = new SimpleListProperty<String>(FXCollections.observableArrayList());
    public static StringProperty ActiveUserLabelName;

    public void playSong(Song song) {
        SongPlayer.getInstance().play(song);
    }

    public void stopSong() {
        SongPlayer.getInstance().pause();
    }

    public void updateListSongView() {
        var repo = InMemorySongRepositoryImpl.getInstance();
        this.musicas.addAll(new GetAllSongsOfUser(repo).execute(UserSession.getInstance().getUser()));
        System.out.println(this.musicas.size());
    }

    public void updateListSongViewNewSong(Song song) {
        this.musicas.add(song);
    }

    public void updateListSongViewFolder(Folder folder) {
        var songOfFOLDER = new GetAllSongsOfFolder().execute(folder);
        this.musicas.addAll(songOfFOLDER);
    }

    public void sendNewMusicFileInMemory(File file) {
        var repo = InMemorySongRepositoryImpl.getInstance();
        var song = Song.fromFile(file);
        song.setUserID(UserSession.getInstance().getUser().getId().get());
        new AddSong(repo).execute(song);

        // ! Updating song in ListView
        this.updateListSongViewNewSong(song);
    }

    public void sendNewFolderOfSongs(File file) {
        // !Catching folder files
        Folder folder = new Folder();
        folder.path = file.toPath().toString();
        folder.userID = UserSession.getInstance().getUser().getId().get();
        folder.scanSongFiles();

        // !Adding to dataBase
        var repo = InMemoryFolderRepositoryImpl.getInstance();
        new AddFolder(repo).execute(folder);

        // !Updating songs in ListView
        this.updateListSongViewFolder(folder);
    }

    public void addNewFile() {
        var file = this.openFileChooser();
        if (file != null) {
            this.sendNewMusicFileInMemory(file);
        }
    }

    public File openFileChooser() {
        var stage = this.app.getStage();
        var selectedFile = OpenChooseFileDialog.showChooseFileDialog(stage);
        if (selectedFile.isPresent()) {
            return selectedFile.get();
        } else {
            return null;
        }
    }

    public void addNewFolder() {
        var file = this.openFolderChooser();
        if (file != null) {
            this.sendNewFolderOfSongs(file);
        }
    }

    public File openFolderChooser() {
        var stage = this.app.getStage();
        var selectedFolder = OpenChooseFolderDialog.showChooseFolderDialog(stage);
        if (selectedFolder.isPresent()) {
            return selectedFolder.get();
        } else {
            return null;
        }
    }

    // Fields
    private App app = App.getInstance();

    public void goToRegister() {
        this.app.changeToRegisterScene();
    }

    public void goToLogin() {
        this.musicas.clear();
        this.app.changeToLoginScene();
    }

    public void goToSettings() {
        this.app.changeToSettingsScene();
    }

    public String getCurrentSong() {
        return this.current_song;
    }

    public String getDefaultImage() {
        return default_song_image;
    }

    public void setCurrentSong(String currentSong) {
        this.current_song = currentSong;
    }
}
