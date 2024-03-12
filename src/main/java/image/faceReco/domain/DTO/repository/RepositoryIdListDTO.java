package image.faceReco.domain.DTO.repository;

import lombok.Data;

@Data
public class RepositoryIdListDTO {
    private RepositoryIdList repositoryIdList;

    public RepositoryIdListDTO(){}
    public RepositoryIdListDTO(RepositoryIdList repositoryIdList) {
        this.repositoryIdList = repositoryIdList;
    }
}
