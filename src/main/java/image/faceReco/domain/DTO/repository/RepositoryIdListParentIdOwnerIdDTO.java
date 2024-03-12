package image.faceReco.domain.DTO.repository;

import lombok.Data;

@Data
public class RepositoryIdListParentIdOwnerIdDTO {
    private int ownerId;
    private Integer parentId;
    private RepositoryIdList repositoryIdList;

    public RepositoryIdListParentIdOwnerIdDTO(int ownerId, Integer parentId, RepositoryIdList repositoryIdList) {
        this.ownerId = ownerId;
        this.parentId = parentId;
        this.repositoryIdList = repositoryIdList;
    }
    public static RepositoryIdListParentIdOwnerIdDTO fromRepositoryIdListParentIdDTO(int ownerId,
                                                                              RepositoryIdListParentIdDTO repositoryIdListParentIdDTO){
        return new RepositoryIdListParentIdOwnerIdDTO(ownerId, repositoryIdListParentIdDTO.getParentId(),
                repositoryIdListParentIdDTO.getRepositoryIdList());
    }
}
