package image.faceReco.domain.entity;

import lombok.Data;

@Data
public class Folder {
    Integer folderId;
    Integer ownerId;
    Integer parentFolder;
    String folderName;
    String createDate;

    public Folder(Integer folderId, Integer ownerId, Integer parentFolder, String folderName, String createDate) {
        this.folderId = folderId;
        this.ownerId = ownerId;
        this.parentFolder = parentFolder;
        this.folderName = folderName;
        this.createDate = createDate;
    }
    public Folder(Integer ownerId, Integer parentFolder, String folderName, String createDate) {
        this.ownerId = ownerId;
        this.parentFolder = parentFolder;
        this.folderName = folderName;
        this.createDate = createDate;
    }


}
