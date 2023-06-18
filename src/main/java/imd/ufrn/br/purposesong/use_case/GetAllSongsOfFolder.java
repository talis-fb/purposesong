package imd.ufrn.br.purposesong.use_case;

import imd.ufrn.br.purposesong.entity.Folder;
import imd.ufrn.br.purposesong.entity.Song;

import java.util.List;

public class GetAllSongsOfFolder {
    public List<Song> execute(Folder folder) {
        return folder.scanSongFiles();
    }
}
