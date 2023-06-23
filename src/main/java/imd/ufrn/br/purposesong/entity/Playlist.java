package imd.ufrn.br.purposesong.entity;

import java.util.List;
import java.util.UUID;

public class Playlist extends ModelDatabaseEntity {
    List<Song> songs;
    String name;
    public UUID userID;

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }
}
