package image.faceReco.service.album;

import image.faceReco.domain.DTO.album.AlbumDTO;
import image.faceReco.domain.DTO.RepositoryCreateDTO;
import image.faceReco.domain.updateParam.RepositoryNameUpdateParam;
import image.faceReco.domain.updateParam.album.AlbumDeleteByAlbumIdListParam;

import java.util.List;

public interface AlbumService {
    List<AlbumDTO> findAlbumByOwnerId(int ownerId);
    int createAlbum(RepositoryCreateDTO  repositoryCreateDTO);
    int updateAlbumNameByAlumId(RepositoryNameUpdateParam repositoryNameUpdateParam);
}
