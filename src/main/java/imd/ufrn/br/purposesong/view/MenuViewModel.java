package imd.ufrn.br.purposesong.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import imd.ufrn.br.purposesong.App;
import imd.ufrn.br.purposesong.database.inmemory.InMemorySongRepositoryImpl;
import imd.ufrn.br.purposesong.database.inmemory.InMemoryUserRepositoryImpl;
import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.entity.User;
import imd.ufrn.br.purposesong.use_case.AddSong;
import imd.ufrn.br.purposesong.use_case.GetAllSongsOfUser;
import imd.ufrn.br.purposesong.use_case.LoginUser;
import imd.ufrn.br.purposesong.utils.OpenChooseFileDialog;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class MenuViewModel {

    private ArrayList<String> playlists;
    private ArrayList<Song> musicas = new ArrayList<>();
    private ArrayList<Image> images;
    private String default_song_image = "file:src/main/resources/imd/ufrn/br/purposesong/images/default.png";
    private String current_song = "";

    public final ListProperty<String> songNames = new SimpleListProperty<String>();

    public void updateListSongName() {
        try {
            songNames.clear();
            for (Song song : getMusicas()) {
                songNames.add(song.name);
            }
        } catch (Exception e) {
            System.out.println("Nops");
        }
    }
    /*
     * public void initializeSongs() {
     * /* Simulating a file reader
     * Image imagem = new Image(
     * "file:src/main/resources/imd/ufrn/br/purposesong/images/headphoneLOGO.png");
     * Image imagem2 = new
     * Image("file:src/main/resources/imd/ufrn/br/purposesong/images/retroceder.png"
     * );
     * Image imagem3 = new Image(
     * "file:src/main/resources/imd/ufrn/br/purposesong/images/melevapracasa.jpg");
     * musicas = new ArrayList<>();
     * musicas.add("Sua Graça me Basta");
     * musicas.add("Apena Ti");
     * musicas.add("Me leva pra casa");
     * musicas.add("Only you God");
     * musicas.add("Promises");
     * musicas.add("Esperança");
     * musicas.add("Dandelions");
     * musicas.add("Fortress");
     * musicas.add("I wanna know");
     * musicas.add("Somewhere only we know");
     * musicas.add("You are not the only one");
     * musicas.add("Clocks");
     * musicas.add("Paradise");
     * musicas.add("Counting Stars");
     * musicas.add("Sky full of stars");
     * 
     * images = new ArrayList<>();
     * images.add(imagem);
     * images.add(imagem2);
     * images.add(imagem3);
     * }
     */

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

    // !Only VIP users have acess to this method
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
        var songFile = this.sendNewMusicFile(file);
        this.musicas.add(songFile);
    }

    public File openFileChooser() {
        var stage = this.app.getStage();
        var selectedFile = OpenChooseFileDialog.showChooseFileDialog(stage);
        return selectedFile;
    }

    public void openFolderChooser() {
        this.app.changeToFolderChooser();
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
        // !Call initializeSongs here!!
    }

    public void initializeUserVIP() {
        System.out.println("Initializing user vip...");
    }

    public void initializeUserNormal() {
        System.out.println("Initializing normal user...");

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
