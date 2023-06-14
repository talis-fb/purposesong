package imd.ufrn.br.purposesong.use_case;

import imd.ufrn.br.purposesong.database.FolderRepository;
import imd.ufrn.br.purposesong.entity.Folder;

public class AddFolder {
    final FolderRepository folderRepository;

    public AddFolder(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public void execute(Folder folder) {
        this.folderRepository.create(folder);
    }
}
