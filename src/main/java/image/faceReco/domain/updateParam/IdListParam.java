package image.faceReco.domain.updateParam;

import image.faceReco.domain.DTO.repository.RepositoryIdListOwnerIdDTO;
import lombok.Data;

import java.util.List;

@Data
public class IdListParam {
    int ownerId;
    List<Integer> idList;

    public IdListParam(int ownerId, List<Integer> idList) {
        this.ownerId = ownerId;
        this.idList = idList;
    }

    public static IdListParam fromRepositoryIdListOwnerIdDTOToFolderIdList(RepositoryIdListOwnerIdDTO repositoryIdListOwnerIdDTO){
        return new IdListParam(repositoryIdListOwnerIdDTO.getOwnerId(), repositoryIdListOwnerIdDTO.getRepositoryIdList().getFolderIdList());
    }

    public static IdListParam fromRepositoryIdListOwnerIdDTOToAlbumIdList(RepositoryIdListOwnerIdDTO repositoryIdListOwnerIdDTO){
        return new IdListParam(repositoryIdListOwnerIdDTO.getOwnerId(), repositoryIdListOwnerIdDTO.getRepositoryIdList().getAlbumIdList());
    }
}
