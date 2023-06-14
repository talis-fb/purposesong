package imd.ufrn.br.purposesong.utils;

import imd.ufrn.br.purposesong.entity.Song;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class SongCellFactory implements Callback<ListView<Song>, ListCell<Song>> {
    @Override
    public ListCell<Song> call(ListView<Song> param) {
        return new ListCell<>() {
            @Override
            public void updateItem(Song song, boolean empty) {
                super.updateItem(song, empty);
                if (empty || song == null) {

                } else {
                    setText(song.name);
                }
            }
        };
    }
}