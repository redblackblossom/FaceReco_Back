package image.faceReco.domain.DTO;

import lombok.Data;

import java.util.List;

@Data
public class RepositoryIdListParentIdDTO {
    private Integer parentId;
    private RepositoryIdList repositoryIdList;

    public RepositoryIdListParentIdDTO(){}
    public RepositoryIdListParentIdDTO(Integer parentId, RepositoryIdList repositoryIdList) {
        this.parentId = parentId;
        this.repositoryIdList = repositoryIdList;
    }
}
