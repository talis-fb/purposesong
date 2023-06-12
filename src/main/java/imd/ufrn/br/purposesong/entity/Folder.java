package imd.ufrn.br.purposesong.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Folder extends ModelDatabaseEntity{
    public String path;
    public UUID userID;

    public List<Song> scanSongFiles() {
        List<Song> songsOfFiles = new ArrayList<>();

        File folder = new File(path);
        if (!folder.isDirectory()) {
            System.out.println("Invalid directory path!");
            return songsOfFiles;
        }

        File[] files = folder.listFiles();
        if (files == null) {
            System.out.println("Error reading files!");
            return songsOfFiles;
        }

        for (File file : files) {
            if (file.isFile() && file.getName().toLowerCase().endsWith(".mp3")) {
                songsOfFiles.add(Song.fromFile(file));
            }
        }

        return songsOfFiles;
    }
}
