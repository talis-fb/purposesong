package imd.ufrn.br.purposesong.database.inmemory;

import imd.ufrn.br.purposesong.entity.Folder;
import java.util.ArrayList;
import java.util.List;

public class InMemoryFolderRepositoryImpl extends InMemoryAbstractRepository<Folder> {
    private static final List<Folder> folders = new ArrayList<>();
    @Override
    public List<Folder> findAll() {
        return folders;
    }
}
