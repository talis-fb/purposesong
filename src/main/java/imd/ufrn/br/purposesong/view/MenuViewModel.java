package imd.ufrn.br.purposesong.view;

import java.util.ArrayList;

import imd.ufrn.br.purposesong.App;
import javafx.scene.image.Image;

public class MenuViewModel {

    // !private User user;

    private ArrayList<String> playlists;

    public ArrayList<String> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<String> playlists) {
        this.playlists = playlists;
    }

    private ArrayList<String> musicas;
    private ArrayList<Image> images;
    private String default_song_image = "file:src/main/resources/imd/ufrn/br/purposesong/images/default.png";
    private String current_song = "";

    public String getDefaultImage() {
        return default_song_image;
    }

    public void setCurrentSong(String currentSong) {
        this.current_song = currentSong;
    }

    public String getCurrentSong() {
        return this.current_song;
    }

    public void initializeSongs() {
        /* Simulating a file reader */
        Image imagem = new Image(
                "file:src/main/resources/imd/ufrn/br/purposesong/images/headphoneLOGO.png");
        Image imagem2 = new Image("file:src/main/resources/imd/ufrn/br/purposesong/images/retroceder.png");
        Image imagem3 = new Image("file:src/main/resources/imd/ufrn/br/purposesong/images/melevapracasa.jpg");
        musicas = new ArrayList<>();
        musicas.add("Sua Graça me Basta");
        musicas.add("Apena Ti");
        musicas.add("Me leva pra casa");
        musicas.add("Only you God");
        musicas.add("Promises");
        musicas.add("Esperança");
        musicas.add("Dandelions");
        musicas.add("Fortress");
        musicas.add("I wanna know");
        musicas.add("Somewhere only we know");
        musicas.add("You are not the only one");
        musicas.add("Clocks");
        musicas.add("Paradise");
        musicas.add("Counting Stars");
        musicas.add("Sky full of stars");

        images = new ArrayList<>();
        images.add(imagem);
        images.add(imagem2);
        images.add(imagem3);
    }

    public ArrayList<String> getMusicas() {
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

    public void openFileChooser() {
        this.app.changeToFileChooser();
    }

    public void openFolderChooser() {
        this.app.changeToFolderChooser();
    }


    // Singleton ---------------------------
    private static final MenuViewModel instance = new MenuViewModel();

    private MenuViewModel() {
        initializePlaylists();
    }

    public void initializePlaylists() {
        playlists = new ArrayList<>();
        playlists.add("Rock Pesadão");
        playlists.add("MPB");
        playlists.add("Paz que acalma a alma");
        playlists.add("Maranata ora vem!!");
        initializeSongs();
        // !Call initializeSongs here!!
    }

    public static MenuViewModel getInstance() {
        return MenuViewModel.instance;
    }
}
