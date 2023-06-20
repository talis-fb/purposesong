package imd.ufrn.br.purposesong.database.csv;

import imd.ufrn.br.purposesong.database.FolderRepository;
import imd.ufrn.br.purposesong.database.SongRepository;
import imd.ufrn.br.purposesong.entity.Folder;
import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.entity.User;

import java.util.List;
import java.util.UUID;

public class CsvFolderRepositoryImpl
        extends CsvAbstractRepositoryImpl<Folder>
        implements FolderRepository
{
    private static final String CSV_FILE_PATH = "folders.csv";
    private static final String[] CSV_HEADER = { "id","path","userID" };

    private static final CsvOperator csvOperator = new CsvOperator(CSV_FILE_PATH, CSV_HEADER);

    public List<Folder> findAllFoldersOfUser(User user) {
        return this.findAll()
                .stream()
                .filter((it) -> it.userID.equals(user.getId().get()))
                .toList();
    }


    List<Folder> readCsvFile() {
        List<String[]> lines = csvOperator.readCsvFile();

        return lines.stream().map(fields -> {
            var folder = new Folder();
            folder.setId(UUID.fromString(fields[0]));
            folder.path = fields[1];
            folder.userID = UUID.fromString(fields[2]);
            return folder;
        }).toList();
    }

    void writeCsvFile(List<Folder> songs) {
        // HEADER = { "id","path","userID" }
        List<String[]> lines = songs.stream().map(it -> {
            String[] line = {
                    it.getId().get().toString(),
                    it.path,
                    it.userID.toString()
            };
            return line;
        }).toList();

        csvOperator.writeCsvFile(lines);
    }

    // Singleton ---------------------
    private static final CsvFolderRepositoryImpl instance = new CsvFolderRepositoryImpl();
    private CsvFolderRepositoryImpl() {}
    public static CsvFolderRepositoryImpl getInstance() {
        return CsvFolderRepositoryImpl.instance;
    }
}
