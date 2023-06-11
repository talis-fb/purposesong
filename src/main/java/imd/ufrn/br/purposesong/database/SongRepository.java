package imd.ufrn.br.purposesong.database;

import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.entity.User;

import java.util.List;
import java.util.Optional;

public interface SongRepository {
    Optional<Song> findById(String id);
    List<Song> findAll();
    void update(String id, Song song);
    void delete(String id);

    Optional<Song> login(String email, String password);
}
