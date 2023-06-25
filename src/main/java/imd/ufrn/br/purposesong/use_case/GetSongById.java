package imd.ufrn.br.purposesong.use_case;

import imd.ufrn.br.purposesong.database.SongRepository;
import imd.ufrn.br.purposesong.entity.Song;

import java.util.Optional;
import java.util.UUID;

public class GetSongById {
    final SongRepository songRepository;

    public GetSongById(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Optional<Song> execute(UUID id) {
        return this.songRepository.findById(id);
    }
}
