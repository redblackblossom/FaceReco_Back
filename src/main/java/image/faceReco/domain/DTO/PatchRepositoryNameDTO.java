package image.faceReco.domain.DTO;

import lombok.Getter;

@Getter
public class PatchRepositoryNameDTO {
    private int repositoryId;
    private String updateRepositoryName;

    public PatchRepositoryNameDTO(){}

    public PatchRepositoryNameDTO(int repositoryId, String updateRepositoryName) {
        this.repositoryId = repositoryId;
        this.updateRepositoryName = updateRepositoryName;
    }
}
