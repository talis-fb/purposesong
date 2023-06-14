package imd.ufrn.br.purposesong.view;

import java.util.ArrayList;
import javafx.scene.image.Image;

public class MenuViewNormalModel extends MenuAbstractModel {

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

    // Singleton ---------------------------
    private static final MenuViewNormalModel instance = new MenuViewNormalModel();

    private MenuViewNormalModel() {
        // getUserSongs()?
    }

    public static MenuViewNormalModel getInstance() {
        return MenuViewNormalModel.instance;
    }
}
