package image.faceReco.service.album;

import image.faceReco.domain.DTO.AlbumDTO;

import java.util.List;

public interface AlbumService {
    List<AlbumDTO> findAlbumByOwnerId(int ownerId);
}
