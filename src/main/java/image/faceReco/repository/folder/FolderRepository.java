package image.faceReco.repository.folder;


import image.faceReco.domain.entity.Folder;
import image.faceReco.domain.updateParam.FolderNameUpdateParam;

import java.util.List;

public interface FolderRepository {
    int createFolder(Folder folder);
    int updateFolderName(FolderNameUpdateParam updateParam);
    List<Folder> selectFolderByFolderId(Integer folderId);
    int deleteFolderByFolderId (Integer folderId);
    List<Folder> selectFolderByOwnerId(Integer ownerId);
    int deleteFolderByFolderIdArray(int ownerId,List<Integer> folderIdArray);
}
