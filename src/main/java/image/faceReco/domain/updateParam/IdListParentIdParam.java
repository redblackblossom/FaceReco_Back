package image.faceReco.domain.updateParam;

import image.faceReco.domain.DTO.repository.RepositoryIdListParentIdOwnerIdDTO;
import lombok.Data;

import java.util.List;

@Data
public class IdListParentIdParam {
    int ownerId;
    Integer parentId;
    List<Integer> idList;

    public IdListParentIdParam(int ownerId, Integer parentId, List<Integer> idList) {
        this.ownerId = ownerId;
        this.parentId = parentId;
        this.idList = idList;
    }

    public static IdListParentIdParam fromRepositoryIdListParentIdOwnerIdDTOToFolder(RepositoryIdListParentIdOwnerIdDTO repositoryIdListParentIdOwnerIdDTO){
        return new IdListParentIdParam(repositoryIdListParentIdOwnerIdDTO.getOwnerId(), repositoryIdListParentIdOwnerIdDTO.getParentId(),
                repositoryIdListParentIdOwnerIdDTO.getRepositoryIdList().getFolderIdList());
    }
    public static IdListParentIdParam fromRepositoryIdListParentIdOwnerIdDTOToAlbum(RepositoryIdListParentIdOwnerIdDTO repositoryIdListParentIdOwnerIdDTO){
        return new IdListParentIdParam(repositoryIdListParentIdOwnerIdDTO.getOwnerId(), repositoryIdListParentIdOwnerIdDTO.getParentId(),
                repositoryIdListParentIdOwnerIdDTO.getRepositoryIdList().getAlbumIdList());
    }
}
