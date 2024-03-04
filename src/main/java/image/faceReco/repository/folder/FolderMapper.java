package image.faceReco.repository.folder;

import image.faceReco.domain.entity.Folder;
import image.faceReco.domain.updateParam.FolderNameUpdateParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FolderMapper {
    int createFolder(Folder folder);
    int updateFolderName(FolderNameUpdateParam updateParam);
    List<Folder> selectFolderByFolderId(Integer id);
}
