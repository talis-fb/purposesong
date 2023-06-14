package imd.ufrn.br.purposesong.use_case;

import imd.ufrn.br.purposesong.database.SongRepository;
import imd.ufrn.br.purposesong.entity.Song;

public class AddSong {
    final SongRepository songRepository;

    public AddSong(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song execute(Song song) {
        return this.songRepository.create(song);
    }
}
