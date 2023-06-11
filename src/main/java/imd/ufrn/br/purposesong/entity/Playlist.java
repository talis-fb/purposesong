package imd.ufrn.br.purposesong.entity;

import java.util.List;

public class Playlist extends ModelDatabaseEntity {
    List<Song> songs;
    String name;
    String userId;
}
