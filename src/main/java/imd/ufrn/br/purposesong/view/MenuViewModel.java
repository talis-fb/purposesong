package imd.ufrn.br.purposesong.view;

import java.util.ArrayList;

import imd.ufrn.br.purposesong.App;
import javafx.scene.image.Image;

public class MenuViewModel {
    // Singleton
    private static MenuViewModel instance = new MenuViewModel();

    private MenuViewModel() {
        initializeSongs();
    }

    public static MenuViewModel getInstance() {
        return MenuViewModel.instance;
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
        /* Simulation a file reader */
        Image imagem = new Image(
                "file:src/main/resources/imd/ufrn/br/purposesong/images/headphoneLOGO.png");
        Image imagem2 = new Image("file:src/main/resources/imd/ufrn/br/purposesong/images/retroceder.png");
        musicas = new ArrayList<>();
        musicas.add("Sua Graça me Basta");
        musicas.add("Apena Ti");
        musicas.add("Me leva pra casa");
        musicas.add("Only you God");
        musicas.add("Promises");
        musicas.add("Esperança");

        images = new ArrayList<>();
        images.add(imagem);
        images.add(imagem2);
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
}
