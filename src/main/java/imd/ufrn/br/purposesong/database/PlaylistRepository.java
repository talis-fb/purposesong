package imd.ufrn.br.purposesong.database;

import imd.ufrn.br.purposesong.entity.Playlist;
import imd.ufrn.br.purposesong.entity.User;
import java.util.List;

public interface PlaylistRepository extends AbstractRepository<Playlist> {
    List<Playlist> findAllPlaylistsOfUser(User user);
}
