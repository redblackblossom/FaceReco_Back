package image.faceReco.service.folder;

import image.faceReco.domain.DTO.FolderDTO;
import image.faceReco.domain.entity.Folder;

import java.util.List;

public interface FolderService {
    List<FolderDTO> findAllFolderByOwnerId(int userId);
    int createFolder(Folder folder);
}
