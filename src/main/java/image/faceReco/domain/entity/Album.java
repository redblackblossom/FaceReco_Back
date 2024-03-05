package image.faceReco.domain.entity;

import lombok.Data;

@Data
public class Album {
    int albumId;
    int ownerId;
    int ownerFolderId;
    String albumName;
    String createDate;

    public Album(int ownerId, int ownerFolderId, String albumName, String createDate) {
        this.ownerId = ownerId;
        this.ownerFolderId = ownerFolderId;
        this.albumName = albumName;
        this.createDate = createDate;
    }

    public Album(int albumId, int ownerId, int ownerFolderId, String albumName, String createDate) {
        this.albumId = albumId;
        this.ownerId = ownerId;
        this.ownerFolderId = ownerFolderId;
        this.albumName = albumName;
        this.createDate = createDate;
    }
}
