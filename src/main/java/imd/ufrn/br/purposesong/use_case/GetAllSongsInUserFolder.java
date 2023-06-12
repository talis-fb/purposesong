package imd.ufrn.br.purposesong.use_case;

import imd.ufrn.br.purposesong.database.FolderRepository;
import imd.ufrn.br.purposesong.entity.Song;
import imd.ufrn.br.purposesong.entity.User;
import java.util.List;

public class GetAllSongsInUserFolder {
    final FolderRepository folderRepository;
    public GetAllSongsInUserFolder(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public List<Song> execute(User user) {
        return this.folderRepository
            .findAllFoldersOfUser(user)
            .stream()
            .flatMap(folder -> new GetAllSongsOfFolder().execute(folder).stream())
            .toList();
    }
}
