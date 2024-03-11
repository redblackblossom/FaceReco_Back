package image.faceReco.domain.DTO.folder;

import lombok.Data;

import java.util.List;

@Data
public class DeleteFolderFolderIdListDTO {
    private List<Integer> deleteFolderIdList;

    public DeleteFolderFolderIdListDTO(){}
    public DeleteFolderFolderIdListDTO(List<Integer> deleteFolderIdList) {
        this.deleteFolderIdList = deleteFolderIdList;
    }
}
