package image.faceReco.domain.updateParam;

import image.faceReco.domain.DTO.repository.PatchRepositoryNameDTO;
import lombok.Data;

@Data
public class RepositoryNameUpdateParam {
    int ownerId;
    int repositoryId;
    String updateRepositoryName;

    public RepositoryNameUpdateParam(int ownerId, int repositoryId, String updateRepositoryName) {
        this.ownerId = ownerId;
        this.repositoryId = repositoryId;
        this.updateRepositoryName = updateRepositoryName;
    }

    public static RepositoryNameUpdateParam fromPatchRepositoryNameDTO(int ownerId, PatchRepositoryNameDTO patchRepositoryNameDTO){
        return new RepositoryNameUpdateParam(ownerId, patchRepositoryNameDTO.getRepositoryId(), patchRepositoryNameDTO.getUpdateRepositoryName());
    }
}
