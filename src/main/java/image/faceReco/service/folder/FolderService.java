package image.faceReco.service.folder;

import image.faceReco.domain.DTO.folder.FolderDTO;
import image.faceReco.domain.DTO.repository.RepositoryCreateDTO;
import image.faceReco.domain.updateParam.RepositoryNameUpdateParam;

import java.util.List;

public interface FolderService {
    List<FolderDTO> findAllFolderByOwnerId(int userId);
    FolderDTO createFolder(RepositoryCreateDTO repositoryCreateDTO);
    int updateFolderName(RepositoryNameUpdateParam repositoryNameUpdateParam);
}
