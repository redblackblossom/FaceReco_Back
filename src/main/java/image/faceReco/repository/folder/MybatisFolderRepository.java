package image.faceReco.repository.folder;

import image.faceReco.domain.entity.Folder;
import image.faceReco.domain.updateParam.FolderNameUpdateParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MybatisFolderRepository implements FolderRepository{
    private final FolderMapper folderMapper;

    @Override
    public int createFolder(Folder folder) {
        return folderMapper.createFolder(folder);
    }

    @Override
    public int updateFolderName(FolderNameUpdateParam updateParam) {
        return folderMapper.updateFolderName(updateParam);
    }

    @Override
    public List<Folder> selectFolderByUserId(Integer id) {
        return folderMapper.selectFolderByFolderId(id);
    }

    @Override
    public int deleteFolderByFolderId(Integer folderId) {
        return folderMapper.deleteFolderByFolderId(folderId);
    }

}
