package image.faceReco.repository.album;

import image.faceReco.domain.entity.Album;
import image.faceReco.domain.updateParam.IdListParam;
import image.faceReco.domain.updateParam.IdListParentIdParam;
import image.faceReco.domain.updateParam.ParentIdNameListParam;
import image.faceReco.domain.updateParam.RepositoryNameUpdateParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MybatisAlbumRepository implements AlbumRepository{
    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public List<Album> createAlbum(Album album) {
        int createCount =  albumMapper.createAlbum(album);
        return albumMapper.selectAlbumByAlbumId(album.getAlbumId());
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
    public int updateAlbumNameByAlbumId(RepositoryNameUpdateParam repositoryNameUpdateParam) {
        return albumMapper.updateAlbumNameByAlbumId(repositoryNameUpdateParam);
    }

    @Override
    public List<Album> selectAlbumByAlbumId(Integer albumId){
        return albumMapper.selectAlbumByAlbumId(albumId);
    }

    @Override
    public List<Album> selectAlbumByOwnerId(int ownerId) {
        return albumMapper.selectAlbumByOwnerId(ownerId);
    }

    @Override
    public int deleteAlbumByAlbumIdList(IdListParam idListParam) {
        return albumMapper.deleteAlbumByAlbumIdList(idListParam);
    }

    @Override
    public int updateAlbumNameByAlbumIdList(IdListParentIdParam idListParentIdParam) {
        return albumMapper.updateAlbumNameByAlbumIdList(idListParentIdParam);
    }

    @Override
    public List<String> selectAlbumByOwnerFolderIdFolderName(ParentIdNameListParam parentIdNameListParam) {
        return albumMapper.selectAlbumByOwnerFolderIdFolderName(parentIdNameListParam);
    }
}
