package image.faceReco.domain.DTO.album;

import image.faceReco.domain.entity.Album;
import lombok.Data;

@Data
public class PostAlbumDTO {
    private Integer ownerFolderId;
    String albumName;
    String createData;

    public PostAlbumDTO(){}

    public PostAlbumDTO(Integer ownerFolderId, String albumName, String createData) {
        this.ownerFolderId = ownerFolderId;
        this.albumName = albumName;
        this.createData = createData;
    }
}
