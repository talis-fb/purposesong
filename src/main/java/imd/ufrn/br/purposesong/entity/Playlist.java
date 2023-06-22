package imd.ufrn.br.purposesong.entity;

import java.util.List;
import java.util.UUID;

public class Playlist extends ModelDatabaseEntity {
    List<Song> songs;
    String name;
    public UUID userID;

    public void setSongsList(List<Song> list) {
        this.songs = list;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<Song> getSongsOfPlaylist() {
        return this.songs;
    }
}
