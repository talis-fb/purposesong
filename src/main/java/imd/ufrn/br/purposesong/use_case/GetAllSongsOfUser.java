package imd.ufrn.br.purposesong.use_case;

import imd.ufrn.br.purposesong.database.SongRepository;
import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.entity.User;

public class GetAllSongsOfUser {
    final SongRepository songRepository;
    public GetAllSongsOfUser(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public void execute(User user) {
        this.songRepository.findAllSongsOfUser(user);
    }
}
