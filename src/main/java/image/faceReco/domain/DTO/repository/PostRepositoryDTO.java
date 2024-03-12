package image.faceReco.domain.DTO.repository;

import image.faceReco.domain.entity.Folder;
import lombok.Data;

@Data
public class PostRepositoryDTO {
    Integer parentId;
    String name;
    String createDate;

    public PostRepositoryDTO(){}
    public PostRepositoryDTO(Integer parentId, String name, String createDate) {
        this.parentId = parentId;
        this.name = name;
        this.createDate = createDate;
    }

    public Folder toFolder(int ownerId){
        return new Folder(ownerId, this.parentId, this.name, this.createDate);
    }
}
