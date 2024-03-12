package image.faceReco.domain.entity;

import image.faceReco.domain.DTO.repository.RepositoryCreateDTO;
import lombok.Data;

@Data
public class Album {
    int albumId;
    int ownerId;
    Integer ownerFolderId;
    String albumName;
    String createDate;

    public Album(int ownerId, Integer ownerFolderId, String albumName, String createDate) {
        this.ownerId = ownerId;
        this.ownerFolderId = ownerFolderId;
        this.albumName = albumName;
        this.createDate = createDate;
    }

    public Album(int albumId, int ownerId, Integer ownerFolderId, String albumName, String createDate) {
        this.albumId = albumId;
        this.ownerId = ownerId;
        this.ownerFolderId = ownerFolderId;
        this.albumName = albumName;
        this.createDate = createDate;
    }

    public static Album fromRepositoryCreateDTO( RepositoryCreateDTO repositoryCreateDTO){
        return new Album(repositoryCreateDTO.getOwnerId(), repositoryCreateDTO.getParentId(),
                            repositoryCreateDTO.getName(), repositoryCreateDTO.getCreateDate());
    }
}
