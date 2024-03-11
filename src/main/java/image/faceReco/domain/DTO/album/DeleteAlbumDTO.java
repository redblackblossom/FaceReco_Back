package image.faceReco.domain.DTO.album;

import lombok.Data;

import java.util.List;

@Data
public class DeleteAlbumDTO {
    private List<Integer> deleteFolderIdList;

    public DeleteAlbumDTO(){}
    public DeleteAlbumDTO(List<Integer> deleteFolderIdList) {
        this.deleteFolderIdList = deleteFolderIdList;
    }
}
