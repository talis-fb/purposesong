package imd.ufrn.br.purposesong.database.inmemory;

import imd.ufrn.br.purposesong.database.PlaylistRepository;
import imd.ufrn.br.purposesong.entity.Playlist;
import imd.ufrn.br.purposesong.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryPlaylistRepositoryImpl extends InMemoryAbstractRepository<Playlist> implements PlaylistRepository {
    private static final List<Playlist> playlists = new ArrayList<>();

    @Override
    public List<Playlist> findAll() {
        return playlists;
    }

    @Override
    public List<Playlist> findAllPlaylistsOfUser(User user) {
        return this.findAll()
                .stream()
                .filter((it) -> it.userID.equals(user.getId().get()))
                .collect(Collectors.toList());
    }


    // Singleton ---------
    private static InMemoryPlaylistRepositoryImpl instance = new InMemoryPlaylistRepositoryImpl();
    private InMemoryPlaylistRepositoryImpl() {}
    public static InMemoryPlaylistRepositoryImpl getInstance() {
        return InMemoryPlaylistRepositoryImpl.instance;
    }
}
