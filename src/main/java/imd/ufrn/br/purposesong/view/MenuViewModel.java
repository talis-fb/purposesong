package imd.ufrn.br.purposesong.view;

import java.util.ArrayList;

import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.player.SongPlayer;
import javafx.scene.image.Image;

public class MenuViewModel extends MenuAbstractModel {

    private ArrayList<String> playlists;
    private ArrayList<Image> images;

    public void playSong(Song song) {
        SongPlayer.getInstance().play(song);
    }

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
        // initializeSongs();
    }

    // Singleton ---------------------------
    private static final MenuViewModel instance = new MenuViewModel();

    private MenuViewModel() {

        // ();
        initializePlaylists();
        // getUserSongs()?
    }

    public static MenuViewModel getInstance() {
        return MenuViewModel.instance;
    }
}
