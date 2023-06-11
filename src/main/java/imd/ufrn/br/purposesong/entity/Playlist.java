package imd.ufrn.br.purposesong.entity;

import java.util.List;
import java.util.UUID;

public class Playlist extends ModelDatabaseEntity {
    List<Song> songs;
    String name;
    UUID userID;
}
