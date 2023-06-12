package imd.ufrn.br.purposesong.database.inmemory;

import imd.ufrn.br.purposesong.database.FolderRepository;
import imd.ufrn.br.purposesong.entity.Folder;
import imd.ufrn.br.purposesong.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryFolderRepositoryImpl extends InMemoryAbstractRepository<Folder> implements FolderRepository {
    private static final List<Folder> folders = new ArrayList<>();
    @Override
    public List<Folder> findAll() {
        return folders;
    }

    @Override
    public List<Folder> findAllFoldersOfUser(User user) {
        return this.findAll()
                .stream()
                .filter((it) -> it.userID.equals(user.getId().get()))
                .collect(Collectors.toList());
    }


    // Singleton ---------------------
    private static final InMemoryFolderRepositoryImpl instance = new InMemoryFolderRepositoryImpl();
    private InMemoryFolderRepositoryImpl() {}
    public static InMemoryFolderRepositoryImpl getInstance() {
        return InMemoryFolderRepositoryImpl.instance;
    }
}
