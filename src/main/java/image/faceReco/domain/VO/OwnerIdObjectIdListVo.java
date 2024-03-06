package image.faceReco.domain.VO;

import lombok.Getter;

import java.util.List;

@Getter
public class OwnerIdObjectIdListVo {
    private final int ownerId;
    private final List<Integer> objectIdList;

    public OwnerIdObjectIdListVo(int ownerId, List<Integer> objectIdList) {
        this.ownerId = ownerId;
        this.objectIdList = objectIdList;
    }
}