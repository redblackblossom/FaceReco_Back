package image.faceReco.domain.DTO;

import lombok.Data;

import java.util.List;

@Data
public class RepositoryIdListDTO {
    private RepositoryIdList repositoryIdList;

    public RepositoryIdListDTO(){}
    public RepositoryIdListDTO(RepositoryIdList repositoryIdList) {
        this.repositoryIdList = repositoryIdList;
    }
}
