package image.faceReco.domain.updateParam;

import image.faceReco.domain.DTO.repository.RepositoryCreateDTO;
import lombok.Data;

import java.util.List;

@Data
public class ParentIdNameListParam {
    private int ownerId;
    private Integer parentId;
    private List<String> nameList;

    public ParentIdNameListParam(int ownerId, Integer parentId, List<String> nameList) {
        this.ownerId = ownerId;
        this.parentId = parentId;
        this.nameList = nameList;
    }

}
