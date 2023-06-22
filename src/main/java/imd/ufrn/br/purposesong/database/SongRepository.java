package imd.ufrn.br.purposesong.database;

import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SongRepository extends AbstractRepository<Song> {
    List<Song> findAllSongsOfUser(User user);

    Optional<Song> findById(UUID id);
}
