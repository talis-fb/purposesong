package imd.ufrn.br.purposesong.database.csv;

import imd.ufrn.br.purposesong.database.PlaylistRepository;
import imd.ufrn.br.purposesong.database.RepositoryFactory;
import imd.ufrn.br.purposesong.entity.Playlist;
import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.entity.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class CsvPlaylistRepositoryImpl
        extends CsvAbstractRepositoryImpl<Playlist>
        implements PlaylistRepository
{
    private static final String CSV_FILE_PATH = "playlists.csv";
    private static final String[] CSV_HEADER = { "id", "name", "userID", "songs" };

    private static final CsvOperator csvOperator = new CsvOperator(CSV_FILE_PATH, CSV_HEADER);


    @Override
    public List<Playlist> findAllPlaylistsOfUser(User user) {
        return readCsvFile()
            .stream()
            .filter(playlist -> playlist.getUserID().equals(user.getId().get()))
            .toList();
    }

    List<Playlist> readCsvFile() {
        // HEADER = { "id", "name", "userID", "songs" }
        List<String[]> lines = csvOperator.readCsvFile();

        return lines.stream().map(fields -> {
            var playlist = new Playlist();
            playlist.setId(UUID.fromString(fields[0]));
            playlist.setName(fields[1]);
            playlist.setUserID(UUID.fromString(fields[2]));

            String[] songIds = fields[3].split(",");
            List<Song> songs = Arrays.stream(songIds)
                    .map(UUID::fromString)
                    .map(RepositoryFactory.getSongRepository()::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();

            playlist.setSongs(songs);
            return playlist;
        }).toList();
    }

    void writeCsvFile(List<Playlist> playlist) {
        // HEADER = { "id", "name", "userID", "songs" }
        List<String[]> lines = playlist.stream().map(it -> {
            String songsIds = it.getSongs()
                    .stream()
                    .map(Song::getId)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map(UUID::toString)
                    .collect(Collectors.joining(","));

            String[] line = {
                    it.getId().get().toString(),
                    it.getName(),
                    it.getUserID().toString(),
                    songsIds,
            };
            return line;
        }).toList();

        csvOperator.writeCsvFile(lines);
    }

    // Singleton ---------------------
    private static final CsvPlaylistRepositoryImpl instance = new CsvPlaylistRepositoryImpl();
    private CsvPlaylistRepositoryImpl() {}
    public static CsvPlaylistRepositoryImpl getInstance() {
        return CsvPlaylistRepositoryImpl.instance;
    }
}
