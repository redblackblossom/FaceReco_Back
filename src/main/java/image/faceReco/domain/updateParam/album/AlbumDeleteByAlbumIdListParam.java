package image.faceReco.domain.updateParam.album;

import image.faceReco.domain.DTO.album.DeleteAlbumDTO;
import lombok.Data;

import java.util.List;

@Data
public class AlbumDeleteByAlbumIdListParam {
    private int ownerId;
    private List<Integer> deleteAlbumIdList;

    public AlbumDeleteByAlbumIdListParam(int ownerId, List<Integer> deleteAlbumIdList) {
        this.ownerId = ownerId;
        this.deleteAlbumIdList = deleteAlbumIdList;
    }

    public static AlbumDeleteByAlbumIdListParam fromDeleteAlbumDTO(int ownerId, DeleteAlbumDTO deleteAlbumDTO){
        return new AlbumDeleteByAlbumIdListParam(ownerId, deleteAlbumDTO.getDeleteFolderIdList());
    }
}
