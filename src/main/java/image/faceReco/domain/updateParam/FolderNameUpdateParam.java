package image.faceReco.domain.updateParam;

import lombok.Data;

@Data
public class FolderNameUpdateParam {
    int folderId;
    String updateFolderName;

    public FolderNameUpdateParam(int folderId, String updateFolderName) {
        this.folderId = folderId;
        this.updateFolderName = updateFolderName;
    }
}
