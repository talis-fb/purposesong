package imd.ufrn.br.purposesong.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import imd.ufrn.br.purposesong.App;
import imd.ufrn.br.purposesong.database.inmemory.InMemoryFolderRepositoryImpl;
import imd.ufrn.br.purposesong.database.inmemory.InMemorySongRepositoryImpl;
import imd.ufrn.br.purposesong.entity.Folder;
import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.use_case.AddFolder;
import imd.ufrn.br.purposesong.use_case.AddSong;
import imd.ufrn.br.purposesong.utils.OpenChooseFileDialog;
import imd.ufrn.br.purposesong.utils.OpenChooseFolderDialog;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class MenuAbstractModel {
    private ArrayList<Song> musicas = new ArrayList<>();
    private String default_song_image = "file:src/main/resources/imd/ufrn/br/purposesong/images/default.png";
    private String current_song = "";
    public ListProperty<String> songNames = new SimpleListProperty<String>(FXCollections.observableArrayList());

    public void updateListSongName() {
        try {
            songNames.clear();
            System.out.println(this.musicas.size());
            for (Song song : this.musicas) {
                this.songNames.get().add(song.name);
                System.out.println("'Addings");
            }
        } catch (Exception e) {
            System.out.println("Error! " + e.toString());
        }
    }

    public Song sendNewMusicFile(File file) {
        var repo = InMemorySongRepositoryImpl.getInstance();
        var song = Song.fromFile(file);
        song.setId(UserSession.getInstance().getUser().getId().get());
        var savedSong = new AddSong(repo).execute(song);
        System.out
                .println("New music file:" + file.toString() + " added to playlist");
        System.out.println(song.getId() + " " + song.name + " " + song.path);
        return savedSong;
    }

    public List<Song> sendNewFolderOfSongs(File file) {
        // !Catching folder files
        Folder folder = new Folder();
        folder.path = file.toPath().toString();
        folder.userID = UserSession.getInstance().getUser().getId().get();
        var songsOfFolder = folder.scanSongFiles();

        // !Adding to dataBase
        var repo = InMemoryFolderRepositoryImpl.getInstance();
        new AddFolder(repo).execute(folder);
        return songsOfFolder;
    }

    public void addNewFile() {
        var file = this.openFileChooser();
        if (file != null) {
            var songFile = this.sendNewMusicFile(file);
            this.musicas.add(songFile);
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
            var listOfFolderSongs = this.sendNewFolderOfSongs(file);
            // !Adding new songs to musicas
            for (Song song : listOfFolderSongs) {
                this.musicas.add(song);
            }
            // !Updating listView
            updateListSongName();
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

    public ArrayList<Song> getMusicas() {
        return musicas;
    }

    public void addSongInMusicas(Song song) {
        this.musicas.add(song);
    }
}
