package image.faceReco.domain.entity;

import image.faceReco.domain.DTO.repository.RepositoryCreateDTO;
import lombok.Data;

@Data
public class Folder {
    int folderId;
    int ownerId;
    Integer parentFolderId;
    String folderName;
    String createDate;

    public Folder(int folderId, int ownerId, Integer parentFolderId, String folderName, String createDate) {
        this.folderId = folderId;
        this.ownerId = ownerId;
        this.parentFolderId = parentFolderId;
        this.folderName = folderName;
        this.createDate = createDate;
    }
    public Folder(int ownerId, Integer parentFolderId, String folderName, String createDate) {
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
