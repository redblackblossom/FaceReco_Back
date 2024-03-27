package image.faceReco.service.image;

import image.faceReco.domain.DTO.image.ImageDTO;
import image.faceReco.domain.document.Image;

import java.util.List;

public interface ImageService {
    Image createImage(Image image, byte[] imageByte);
    List<ImageDTO> findImageMetaDataByByAlbumId(int ownerId, Integer ownerAlbumId);
    byte[] loadImageByte(String imageDirectory);
}
