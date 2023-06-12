package imd.ufrn.br.purposesong.database.inmemory;

import imd.ufrn.br.purposesong.database.SongRepository;
import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemorySongRepositoryImpl extends InMemoryAbstractRepository<Song> implements SongRepository {
    private static final List<Song> songs = new ArrayList<>();
    @Override
    public List<Song> findAll() {
        return InMemorySongRepositoryImpl.songs;
    }

    @Override
    public List<Song> findAllSongsOfUser(User user) {
        return this.findAll()
                .stream()
                .filter((it) -> it.userID.equals(user.getId().get()))
                .collect(Collectors.toList());
    }


    // Singleton ---------
    private static final InMemorySongRepositoryImpl instance = new InMemorySongRepositoryImpl();
    private InMemorySongRepositoryImpl() {}
    public static InMemorySongRepositoryImpl getInstance() {
        return InMemorySongRepositoryImpl.instance;
    }
}
