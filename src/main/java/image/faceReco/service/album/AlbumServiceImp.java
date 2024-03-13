package image.faceReco.service.album;

import image.faceReco.domain.DTO.album.AlbumDTO;
import image.faceReco.domain.entity.Album;
import image.faceReco.domain.DTO.repository.RepositoryCreateDTO;
import image.faceReco.domain.updateParam.ParentIdNameListParam;
import image.faceReco.domain.updateParam.RepositoryNameUpdateParam;
import image.faceReco.exception.DuplicateNameException;
import image.faceReco.repository.album.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumServiceImp implements AlbumService {
    private final AlbumRepository albumRepository;
    @Override
    public List<AlbumDTO> findAlbumByOwnerId(int ownerId) {
        List<Album> albumList = albumRepository.selectAlbumByOwnerId(ownerId);
        List<AlbumDTO> albumDTOList = new ArrayList<>();
        for(Album album : albumList){
            albumDTOList.add(AlbumDTO.fromAlbum(album));
        }
        return albumDTOList;
    }

    @Override
    public AlbumDTO createAlbum(RepositoryCreateDTO repositoryCreateDTO) {
        Album album = Album.fromRepositoryCreateDTO(repositoryCreateDTO);
        checkDuplicateFolderName(repositoryCreateDTO.getOwnerId(), repositoryCreateDTO.getParentId(),
                                repositoryCreateDTO.getName());
        List<Album> createAlbumList = albumRepository.createAlbum(album);
        //오류 추가 예정

        return AlbumDTO.fromAlbum(createAlbumList.get(0));
    }
    @Override
    public int updateAlbumNameByAlumId(RepositoryNameUpdateParam repositoryNameUpdateParam) {
        Integer ownerFolderId = albumRepository.selectAlbumByAlbumId(repositoryNameUpdateParam.getRepositoryId()).get(0).getOwnerFolderId();
        checkDuplicateFolderName(repositoryNameUpdateParam.getOwnerId(), ownerFolderId,
                                repositoryNameUpdateParam.getUpdateRepositoryName());
        return albumRepository.updateAlbumNameByAlbumId(repositoryNameUpdateParam);
    }

    private void checkDuplicateFolderName(int ownerId, Integer ownerFolderId, String name) {
        ParentIdNameListParam param = new ParentIdNameListParam(ownerId,ownerFolderId, List.of(name));
        List<String> duplicateNameList = albumRepository.selectAlbumByOwnerFolderIdFolderName(param);
        if(!duplicateNameList.isEmpty()){
            throw new DuplicateNameException(duplicateNameList);
        }
    }
}
