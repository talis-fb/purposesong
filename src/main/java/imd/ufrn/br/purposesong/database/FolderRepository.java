package imd.ufrn.br.purposesong.database;

import imd.ufrn.br.purposesong.entity.Folder;
import imd.ufrn.br.purposesong.entity.User;
import java.util.List;

public interface FolderRepository extends AbstractRepository<Folder>{
    List<Folder> findAllFoldersOfUser(User user);
}
