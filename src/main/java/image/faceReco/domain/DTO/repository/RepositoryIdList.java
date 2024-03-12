package image.faceReco.domain.DTO.repository;

import lombok.Data;

import java.util.List;

@Data
public class RepositoryIdList {
    private List<Integer> folderIdList;
    private List<Integer> albumIdList;

    public RepositoryIdList(){}
    public RepositoryIdList(List<Integer> folderIdList, List<Integer> albumIdList) {
        this.folderIdList = folderIdList;
        this.albumIdList = albumIdList;
    }
}
