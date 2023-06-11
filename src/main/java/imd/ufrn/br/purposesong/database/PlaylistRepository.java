package imd.ufrn.br.purposesong.database;

import imd.ufrn.br.purposesong.entity.Playlist;
import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlaylistRepository extends AbstractRepository<Playlist> {
    List<Playlist> findAllPlaylistsOfUser(User user);
}
