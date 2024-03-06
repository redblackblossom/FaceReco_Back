package image.faceReco.domain.DTO;

import image.faceReco.domain.entity.Folder;
import lombok.Data;

@Data
public class PostFolderDTO {
    int parentFolder;
    String folderName;
    String createDate;

    public PostFolderDTO(){}
    public PostFolderDTO(int parentFolder, String folderName, String createDate) {
        this.parentFolder = parentFolder;
        this.folderName = folderName;
        this.createDate = createDate;
    }

    public Folder toFolder(int ownerId){
        return new Folder(ownerId, this.parentFolder, this.folderName, this.createDate);
    }
}
