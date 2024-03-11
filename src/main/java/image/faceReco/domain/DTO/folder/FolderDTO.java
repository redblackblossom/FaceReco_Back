package image.faceReco.domain.DTO.folder;

import image.faceReco.domain.entity.Folder;
import lombok.Data;

@Data
public class FolderDTO {
    Integer folderId;
    Integer ownerId;
    Integer parentFolder;
    String folderName;
    String createDate;

    public FolderDTO(Integer folderId, Integer ownerId, Integer parentFolder, String folderName, String createDate) {
        this.folderId = folderId;
        this.ownerId = ownerId;
        this.parentFolder = parentFolder;
        this.folderName = folderName;
        this.createDate = createDate;
    }

    public static FolderDTO fromFolder(Folder folder){
        return new FolderDTO(folder.getFolderId(), folder.getOwnerId(), folder.getParentFolderId()
                             , folder.getFolderName(), folder.getCreateDate());
    }


}
