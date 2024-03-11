package image.faceReco.domain.DTO;

import lombok.Data;

@Data
public class RepositoryIdListOwnerIdDTO {
    private int ownerId;
    private RepositoryIdList repositoryIdList;

    public RepositoryIdListOwnerIdDTO(int ownerId, RepositoryIdList repositoryIdList) {
        this.ownerId = ownerId;
        this.repositoryIdList = repositoryIdList;
    }

    public static RepositoryIdListOwnerIdDTO fromRepositoryIdListDTO(int ownerId, RepositoryIdListDTO repositoryIdListDTO){
        return new RepositoryIdListOwnerIdDTO(ownerId, repositoryIdListDTO.getRepositoryIdList());
    }
}
