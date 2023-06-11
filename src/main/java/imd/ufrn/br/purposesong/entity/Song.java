package imd.ufrn.br.purposesong.entity;

import java.util.UUID;

public class Song extends ModelDatabaseEntity {
    String path;
    String name;
    String artist;
    String photo;
    String hashFile;

    UUID userID;
}
