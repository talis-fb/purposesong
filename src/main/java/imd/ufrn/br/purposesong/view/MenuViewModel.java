package imd.ufrn.br.purposesong.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import imd.ufrn.br.purposesong.App;
import imd.ufrn.br.purposesong.database.inmemory.InMemoryFolderRepositoryImpl;
import imd.ufrn.br.purposesong.database.inmemory.InMemorySongRepositoryImpl;
import imd.ufrn.br.purposesong.entity.Folder;
import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.use_case.AddFolder;
import imd.ufrn.br.purposesong.use_case.AddSong;
import imd.ufrn.br.purposesong.use_case.GetAllSongsOfUser;
import imd.ufrn.br.purposesong.utils.OpenChooseFileDialog;
import imd.ufrn.br.purposesong.utils.OpenChooseFolderDialog;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;

public class MenuViewModel {

    private ArrayList<String> playlists;
    private ArrayList<Song> musicas = new ArrayList<>();
    private ArrayList<Image> images;
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

    /* DEPRECATED */
    public void getUserSongs() {
        var repo = InMemorySongRepositoryImpl.getInstance();
        System.out.println(UserSession.getInstance().getUser().getName());
        List<Song> list = new GetAllSongsOfUser(repo).execute(UserSession.getInstance().getUser());
        // list.stream().forEach(this.musicas.add);

        for (Song music : list) {
            this.musicas.add(music);
        }
    }

    public ArrayList<String> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<String> playlists) {
        this.playlists = playlists;
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

    public ArrayList<Image> getImages() {
        return images;
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
        var listOfFolderSongs = this.sendNewFolderOfSongs(file);
        // !Adding new songs to musicas
        for (Song song : listOfFolderSongs) {
            this.musicas.add(song);
        }
        // !Updating listView
        updateListSongName();
    }

    public File openFolderChooser() {
        var stage = this.app.getStage();
        var selectedFolder = OpenChooseFolderDialog.showChooseFolderDialog(stage);
        return selectedFolder;
    }

    public String setNameUser() {
        return "Olá, " + UserSession.getInstance().getUser().getName();
    }

    public void initializePlaylists() {
        playlists = new ArrayList<>();
        playlists.add("Rock Pesadão");
        playlists.add("MPB");
        playlists.add("Paz que acalma a alma");
        playlists.add("Maranata ora vem!!");
        // initializeSongs();
    }

    // Singleton ---------------------------
    private static final MenuViewModel instance = new MenuViewModel();

    private MenuViewModel() {
        initializePlaylists();
        // getUserSongs()?
    }

    public static MenuViewModel getInstance() {
        return MenuViewModel.instance;
    }
}
