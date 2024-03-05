package image.faceReco.repository.album;

import image.faceReco.domain.entity.Album;
import image.faceReco.domain.entity.Folder;
import image.faceReco.domain.updateParam.AlbumNameUpdateParam;

import java.util.List;

public interface AlbumRepository {
    int createAlbum(Album album);
    List<Album> selectAlbumByParentFolderId(Integer parentFolderId);
    int deleteAlbumByAlbumId(Integer albumId);
    int updateAlbumNameByAlbumId(AlbumNameUpdateParam updateParam);
    List<Album> selectAlbumByAlbumId(Integer albumId);
}
