package imd.ufrn.br.purposesong.utils;

import imd.ufrn.br.purposesong.entity.Playlist;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class PlaylistCellFactory implements Callback<ListView<Playlist>, ListCell<Playlist>> {
    @Override
    public ListCell<Playlist> call(ListView<Playlist> param) {
        return new ListCell<>() {
            @Override
            public void updateItem(Playlist playlist, boolean empty) {
                super.updateItem(playlist, empty);
                if (empty || playlist == null) {

                } else {
                    setText(playlist.getName());
                }
            }
        };
    }
}
