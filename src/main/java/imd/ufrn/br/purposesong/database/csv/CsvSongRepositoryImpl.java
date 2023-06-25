package imd.ufrn.br.purposesong.database.csv;

import imd.ufrn.br.purposesong.database.SongRepository;
import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class CsvSongRepositoryImpl
        extends CsvAbstractRepositoryImpl<Song>
        implements SongRepository
{
    private static final String CSV_FILE_PATH = "songs.csv";
    private static final String[] CSV_HEADER = { "id","path","name","hashFile","userID" };

    private static final CsvOperator csvOperator = new CsvOperator(CSV_FILE_PATH, CSV_HEADER);

    public List<Song> findAllSongsOfUser(User user) {
        return this.findAll()
                .stream()
                .filter((it) -> it.userID.equals(user.getId().get()))
                .toList();
    }

    public Optional<Song> findByPath(String path) {
        return this.findAll()
                .stream()
                .filter(it -> it.getPath().equals(path))
                .findFirst();
    }

    @Override
    public Song create(Song value) {
        Optional<Song> songAlreadyInDb = this.findByPath(value.getPath());
        if (songAlreadyInDb.isPresent())
            return songAlreadyInDb.get();

        ArrayList<Song> values = new ArrayList<>(readCsvFile());
        value.setId(UUID.randomUUID());
        values.add(value);
        writeCsvFile(values);
        return value;
    }

    List<Song> readCsvFile() {
        List<String[]> lines = csvOperator.readCsvFile();

        return lines.stream().map(fields -> {
            var song = new Song();
            song.setId(UUID.fromString(fields[0]));
            song.path = fields[1];
            song.name = fields[2];
            song.hashFile = fields[3];
            song.userID = UUID.fromString(fields[4]);
            return song;
        }).toList();
    }

    void writeCsvFile(List<Song> songs) {
        // HEADER = { "id","path","name","hashFile","userID" }
        List<String[]> lines = songs.stream().map(it -> {
            String[] line = {
                    it.getId().get().toString(),
                    it.path,
                    it.name,
                    it.hashFile,
                    it.userID.toString()
            };
            return line;
        }).toList();

        csvOperator.writeCsvFile(lines);
    }

    // Singleton ---------------------
    private static final CsvSongRepositoryImpl instance = new CsvSongRepositoryImpl();
    private CsvSongRepositoryImpl() {}
    public static CsvSongRepositoryImpl getInstance() {
        return CsvSongRepositoryImpl.instance;
    }
}
