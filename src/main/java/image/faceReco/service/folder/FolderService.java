package image.faceReco.service.folder;

import image.faceReco.domain.DTO.folder.FolderDTO;
import image.faceReco.domain.DTO.RepositoryCreateDTO;
import image.faceReco.domain.updateParam.IdListParam;
import image.faceReco.domain.updateParam.RepositoryNameUpdateParam;
import image.faceReco.domain.updateParam.folder.ParentFolderIdUpdateByListParam;

import java.util.List;

public interface FolderService {
    List<FolderDTO> findAllFolderByOwnerId(int userId);
    int createFolder(RepositoryCreateDTO repositoryCreateDTO);
    int updateFolderName(RepositoryNameUpdateParam repositoryNameUpdateParam);
    //int updateFolderByParentFolderIdList(ParentFolderIdUpdateByListParam parentFolderIdUpdateByListParam);
}
