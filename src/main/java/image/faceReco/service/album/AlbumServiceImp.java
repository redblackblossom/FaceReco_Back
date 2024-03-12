package image.faceReco.service.album;

import image.faceReco.domain.DTO.album.AlbumDTO;
import image.faceReco.domain.entity.Album;
import image.faceReco.domain.DTO.repository.RepositoryCreateDTO;
import image.faceReco.domain.updateParam.RepositoryNameUpdateParam;
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
    public  int createAlbum(RepositoryCreateDTO repositoryCreateDTO) {
        Album album = Album.fromRepositoryCreateDTO(repositoryCreateDTO);
        return albumRepository.createAlbum(album);
    }
    @Override
    public int updateAlbumNameByAlumId(RepositoryNameUpdateParam repositoryNameUpdateParam) {
        return albumRepository.updateAlbumNameByAlbumId(repositoryNameUpdateParam);
    }
}
