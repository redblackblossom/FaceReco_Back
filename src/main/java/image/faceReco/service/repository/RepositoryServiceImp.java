package image.faceReco.service.repository;

import image.faceReco.domain.DTO.repository.RepositoryIdListOwnerIdDTO;
import image.faceReco.domain.DTO.album.AlbumDTO;
import image.faceReco.domain.DTO.folder.FolderDTO;
import image.faceReco.domain.DTO.repository.RepositoryIdListParentIdOwnerIdDTO;
import image.faceReco.domain.entity.Album;
import image.faceReco.domain.entity.Folder;
import image.faceReco.domain.updateParam.IdListParam;
import image.faceReco.domain.updateParam.IdListParentIdParam;
import image.faceReco.repository.album.AlbumRepository;
import image.faceReco.repository.folder.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class RepositoryServiceImp implements RepositoryService {
    private final FolderRepository folderRepository;
    private final AlbumRepository albumRepository;

    @Override
    public Map<String, Object> findAllRepositoryByOwnerId(int ownerId) {
        List<Folder> folderList = folderRepository.selectFolderByOwnerId(ownerId);
        List<Album> albumList = albumRepository.selectAlbumByOwnerId(ownerId);
        List<FolderDTO> folderDTOList = folderList.stream().map(FolderDTO::fromFolder).toList();
        List<AlbumDTO> albumDTOList = albumList.stream().map(AlbumDTO::fromAlbum).toList();
        Map<String, Object> repository = new HashMap<>();
        repository.put("Folders", folderDTOList);
        repository.put("Albums", albumDTOList);
        return repository;
    }

    @Override
    public int deleteRepositoryByIdList(RepositoryIdListOwnerIdDTO repositoryIdListOwnerIdDTO) {
        IdListParam folderIdList = IdListParam.fromRepositoryIdListOwnerIdDTOToFolderIdList(repositoryIdListOwnerIdDTO);
        IdListParam albumIdList = IdListParam.fromRepositoryIdListOwnerIdDTOToAlbumIdList(repositoryIdListOwnerIdDTO);
        int deleteFolderCount = folderRepository.deleteFolderByFoldrIdList(folderIdList);
        int deleteAlbumCount = albumRepository.deleteAlbumByAlbumIdList(albumIdList);

        return deleteFolderCount + deleteAlbumCount;
    }

    @Override
    public int updateParentIdByIdList(RepositoryIdListParentIdOwnerIdDTO repositoryIdListParentIdOwnerIdDTO) {
        IdListParentIdParam folderIdList = IdListParentIdParam.fromRepositoryIdListParentIdOwnerIdDTOToFolder(repositoryIdListParentIdOwnerIdDTO);
        IdListParentIdParam albumIdList = IdListParentIdParam.fromRepositoryIdListParentIdOwnerIdDTOToAlbum(repositoryIdListParentIdOwnerIdDTO);

        int updateFolderCount = folderRepository.updateFolderParentIdByFolderIdList(folderIdList);
        int updateAlbumCount = albumRepository.updateAlbumNameByAlbumIdList(albumIdList);

        return updateFolderCount + updateAlbumCount;
    }
}
