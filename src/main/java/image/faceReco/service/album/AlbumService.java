package image.faceReco.service.album;

import image.faceReco.domain.DTO.album.AlbumDTO;
import image.faceReco.domain.DTO.repository.RepositoryCreateDTO;
import image.faceReco.domain.updateParam.RepositoryNameUpdateParam;

import java.util.List;

public interface AlbumService {
    List<AlbumDTO> findAlbumByOwnerId(int ownerId);
    AlbumDTO createAlbum(RepositoryCreateDTO  repositoryCreateDTO);
    int updateAlbumNameByAlumId(RepositoryNameUpdateParam repositoryNameUpdateParam);
}
