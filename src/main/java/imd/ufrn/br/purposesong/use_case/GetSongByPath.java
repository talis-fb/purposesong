package imd.ufrn.br.purposesong.use_case;

import imd.ufrn.br.purposesong.database.SongRepository;
import imd.ufrn.br.purposesong.entity.Song;

import java.util.Optional;
import java.util.UUID;

public class GetSongByPath {
    final SongRepository songRepository;

    public GetSongByPath(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Optional<Song> execute(String path) {
        return this.songRepository.findByPath(path);
    }
}
