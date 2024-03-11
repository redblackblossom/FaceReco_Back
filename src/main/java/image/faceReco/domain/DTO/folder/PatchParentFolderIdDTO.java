package image.faceReco.domain.DTO.folder;

import lombok.Data;

import java.util.List;

@Data
public class PatchParentFolderIdDTO {
    private Integer updateParamParentFolderId;
    private List<Integer> targetFolderIdList;

    public PatchParentFolderIdDTO() {}
    public PatchParentFolderIdDTO(Integer updateParamParentFolderId, List<Integer> targetFolderIdList) {
        this.updateParamParentFolderId = updateParamParentFolderId;
        this.targetFolderIdList = targetFolderIdList;
    }
}
