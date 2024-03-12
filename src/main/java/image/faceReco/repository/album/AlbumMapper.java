package image.faceReco.repository.album;

import image.faceReco.domain.entity.Album;
import image.faceReco.domain.updateParam.IdListParam;
import image.faceReco.domain.updateParam.IdListParentIdParam;
import image.faceReco.domain.updateParam.RepositoryNameUpdateParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlbumMapper {
    int createAlbum(Album album);
    List<Album> selectAlbumByParentFolderId(Integer ownerFolder);
    int deleteAlbumByAlbumId(Integer albumId);
    int updateAlbumNameByAlbumId(RepositoryNameUpdateParam repositoryNameUpdateParam);
    List<Album> selectAlbumByAlbumId(Integer albumId);
    List<Album> selectAlbumByOwnerId(int ownerId);
    int deleteAlbumByAlbumIdList(IdListParam idListParam);
    int updateAlbumNameByAlbumIdList(IdListParentIdParam idListParentIdParam);
}
