package image.faceReco.domain.DTO.album;

import image.faceReco.domain.entity.Album;
import lombok.Data;

@Data
public class AlbumDTO {
    private int albumId;
    private int ownerId;
    private Integer ownerFolderId;
    private String albumName;
    private String createDate;

    public AlbumDTO(int albumId, int ownerId, Integer ownerFolderId, String albumName, String createDate) {
        this.albumId = albumId;
        this.ownerId = ownerId;
        this.ownerFolderId = ownerFolderId;
        this.albumName = albumName;
        this.createDate = createDate;
    }

    public static AlbumDTO fromAlbum(Album album){
        return new AlbumDTO(album.getAlbumId(), album.getOwnerId(), album.getOwnerFolderId()
                            , album.getAlbumName(), album.getCreateDate());
    }

}
