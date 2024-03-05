package image.faceReco.repository.album;

import image.faceReco.domain.entity.Album;
import image.faceReco.domain.updateParam.AlbumNameUpdateParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlbumMapper {
    int createAlbum(Album album);
    List<Album> selectAlbumByParentFolderId(Integer ownerFolder);
    int deleteAlbumByAlbumId(Integer albumId);
    int updateAlbumNameByAlbumId(AlbumNameUpdateParam updateParam);
    List<Album> selectAlbumByAlbumId(Integer albumId);
}
