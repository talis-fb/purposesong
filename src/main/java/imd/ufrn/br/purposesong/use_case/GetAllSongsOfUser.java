package imd.ufrn.br.purposesong.use_case;

import java.util.List;

import imd.ufrn.br.purposesong.database.SongRepository;
import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.entity.User;

public class GetAllSongsOfUser {
    final SongRepository songRepository;

    public GetAllSongsOfUser(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> execute(User user) {
        return this.songRepository.findAllSongsOfUser(user);
    }
}
