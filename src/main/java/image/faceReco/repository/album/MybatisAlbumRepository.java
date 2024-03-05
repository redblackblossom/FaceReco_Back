package image.faceReco.repository.album;

import image.faceReco.domain.entity.Album;
import image.faceReco.domain.updateParam.AlbumNameUpdateParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MybatisAlbumRepository implements AlbumRepository{
    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public int createAlbum(Album album) {
        return albumMapper.createAlbum(album);
    }

    @Override
    public List<Album> selectAlbumByParentFolderId(Integer parentFolderId) {
        return albumMapper.selectAlbumByParentFolderId(parentFolderId);
    }

    @Override
    public int deleteAlbumByAlbumId(Integer id) {
        return albumMapper.deleteAlbumByAlbumId(id);
    }

    @Override
    public int updateAlbumNameByAlbumId(AlbumNameUpdateParam updateParam) {
        return albumMapper.updateAlbumNameByAlbumId(updateParam);
    }

    @Override
    public List<Album> selectAlbumByAlbumId(Integer albumId){
        return albumMapper.selectAlbumByAlbumId(albumId);
    }
}
