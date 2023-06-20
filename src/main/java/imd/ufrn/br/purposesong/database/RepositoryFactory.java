package imd.ufrn.br.purposesong.database;

import imd.ufrn.br.purposesong.database.csv.CsvUserRepositoryImpl;
import imd.ufrn.br.purposesong.database.inmemory.InMemoryFolderRepositoryImpl;
import imd.ufrn.br.purposesong.database.inmemory.InMemoryUserRepositoryImpl;
import imd.ufrn.br.purposesong.database.inmemory.InMemorySongRepositoryImpl;
import imd.ufrn.br.purposesong.database.inmemory.InMemoryPlaylistRepositoryImpl;

public class RepositoryFactory {
    private static final FolderRepository folderRepository;
    private static final PlaylistRepository playlistRepository;
    private static final SongRepository songRepository;
    private static final UserRepository userRepository;

    static {
        folderRepository = InMemoryFolderRepositoryImpl.getInstance();
        playlistRepository = InMemoryPlaylistRepositoryImpl.getInstance();
        songRepository = InMemorySongRepositoryImpl.getInstance();
        userRepository = CsvUserRepositoryImpl.getInstance();
    }

    public static FolderRepository getFolderRepository() {
        return folderRepository;
    }

    public static PlaylistRepository getPlaylistRepository() {
        return playlistRepository;
    }

    public static SongRepository getSongRepository() {
        return songRepository;
    }

    public static UserRepository getUserRepository() {
        return userRepository;
    }
}
