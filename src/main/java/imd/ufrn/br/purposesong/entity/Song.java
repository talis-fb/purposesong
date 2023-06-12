package imd.ufrn.br.purposesong.entity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;

public class Song extends ModelDatabaseEntity {
    public String path;
    public String name;
    public String hashFile;
    public UUID userID;
    Optional<String> artist;
    Optional<String> photo;

    public void setFileHash() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(Files.readAllBytes(Path.of(this.path)));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            this.hashFile = sb.toString();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Song fromFile(File file) {
        var song = new Song();
        song.path = file.getAbsolutePath();
        song.name = file.getName();
        song.setFileHash();
        return song;
    }
}
