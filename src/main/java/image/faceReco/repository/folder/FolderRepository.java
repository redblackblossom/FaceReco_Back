package image.faceReco.repository.folder;


import image.faceReco.domain.entity.Folder;
import image.faceReco.domain.updateParam.IdListParam;
import image.faceReco.domain.updateParam.IdListParentIdParam;
import image.faceReco.domain.updateParam.ParentIdNameListParam;
import image.faceReco.domain.updateParam.RepositoryNameUpdateParam;

import java.util.List;

public interface FolderRepository {
    List<Folder> createFolder(Folder folder);
    int updateFolderName(RepositoryNameUpdateParam updateParam);
    List<Folder> selectFolderByFolderId(Integer folderId);
    int deleteFolderByFolderId (Integer folderId);
    List<Folder> selectFolderByOwnerId(Integer ownerId);
    int deleteFolderByFoldrIdList(IdListParam folderListOwnerIdParam);
    int updateFolderParentIdByFolderIdList(IdListParentIdParam idListParentIdParam);
    List<String> selectFolderByParentFolderIdFolderName(ParentIdNameListParam parentIdNameListParam);
}
