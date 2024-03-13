package image.faceReco.repository.album;

import image.faceReco.domain.entity.Album;
import image.faceReco.domain.updateParam.IdListParam;
import image.faceReco.domain.updateParam.IdListParentIdParam;
import image.faceReco.domain.updateParam.ParentIdNameListParam;
import image.faceReco.domain.updateParam.RepositoryNameUpdateParam;
import java.util.List;

public interface AlbumRepository {
    List<Album> createAlbum(Album album);
    List<Album> selectAlbumByParentFolderId(Integer parentFolderId);
    int deleteAlbumByAlbumId(Integer albumId);
    int updateAlbumNameByAlbumId(RepositoryNameUpdateParam repositoryNameUpdateParam);
    List<Album> selectAlbumByAlbumId(Integer albumId);
    List<Album> selectAlbumByOwnerId(int ownerId);
    int deleteAlbumByAlbumIdList(IdListParam idListParam);
    int updateAlbumNameByAlbumIdList(IdListParentIdParam idListParentIdParam);
    List<String> selectAlbumByOwnerFolderIdFolderName(ParentIdNameListParam parentIdNameListParam);
}
