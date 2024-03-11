package image.faceReco.domain.entity;

import image.faceReco.domain.DTO.RepositoryCreateDTO;
import lombok.Data;

@Data
public class Folder {
    Integer folderId;
    Integer ownerId;
    Integer parentFolderId;
    String folderName;
    String createDate;

    public Folder(Integer folderId, Integer ownerId, Integer parentFolderId, String folderName, String createDate) {
        this.folderId = folderId;
        this.ownerId = ownerId;
        this.parentFolderId = parentFolderId;
        this.folderName = folderName;
        this.createDate = createDate;
    }
    public Folder(Integer ownerId, Integer parentFolderId, String folderName, String createDate) {
        this.ownerId = ownerId;
        this.parentFolderId = parentFolderId;
        this.folderName = folderName;
        this.createDate = createDate;
    }

    public static Folder fromRepositoryCreateParam(RepositoryCreateDTO repositoryCreateDTO){
        return new Folder(repositoryCreateDTO.getOwnerId(), repositoryCreateDTO.getParentId(),
                        repositoryCreateDTO.getName(), repositoryCreateDTO.getCreateDate());
    }
}
