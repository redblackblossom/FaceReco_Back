package image.faceReco.domain.updateParam.folder;

import image.faceReco.domain.DTO.folder.PatchParentFolderIdDTO;
import lombok.Data;

import java.util.List;

@Data
public class ParentFolderIdUpdateByListParam {
    private int ownerId;
    private Integer updateParamParentFolderId;
    private List<Integer> targetFolderIdList;

    public ParentFolderIdUpdateByListParam(int ownerId, Integer updateParamParentFolderId, List<Integer> targetFolderIdList) {
        this.ownerId = ownerId;
        this.targetFolderIdList = targetFolderIdList;
        this.updateParamParentFolderId = updateParamParentFolderId;
    }

    public static ParentFolderIdUpdateByListParam fromPatchParentFolderDTO(int ownerId, PatchParentFolderIdDTO patchParentFolderIdDTO){
        return new ParentFolderIdUpdateByListParam(ownerId, patchParentFolderIdDTO.getUpdateParamParentFolderId(), patchParentFolderIdDTO.getTargetFolderIdList());
    }
}
