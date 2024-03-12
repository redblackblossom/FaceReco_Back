package image.faceReco.domain.DTO.repository;

import lombok.Data;

@Data
public class RepositoryCreateDTO {
    private int ownerId;
    private Integer parentId;
    private String name;
    private String createDate;

    public RepositoryCreateDTO(){}
    public RepositoryCreateDTO(int ownerId, Integer parentId, String name, String createDate) {
        this.ownerId = ownerId;
        this.parentId = parentId;
        this.name = name;
        this.createDate = createDate;
    }

    public static RepositoryCreateDTO fromPostRepositoryDTO(int ownerId, PostRepositoryDTO postRepositoryDTO){
        return new RepositoryCreateDTO(ownerId, postRepositoryDTO.getParentId(), postRepositoryDTO.getName(),
                postRepositoryDTO.getCreateDate());
    }
}
