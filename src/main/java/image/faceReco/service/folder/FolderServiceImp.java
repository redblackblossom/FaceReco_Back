package image.faceReco.service.folder;

import image.faceReco.domain.DTO.FolderDTO;
import image.faceReco.domain.entity.Folder;
import image.faceReco.repository.folder.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderServiceImp implements FolderService{
    private final FolderRepository folderRepository;
    @Override
    public List<FolderDTO> findAllFolderByOwnerId(int userId) {
        List<Folder> listFolder = folderRepository.selectFolderByOwnerId(userId);
        List<FolderDTO> listFolderDTO = new ArrayList<>();
        for(Folder folder : listFolder){
            listFolderDTO.add(FolderDTO.fromFolder(folder));
        }
        return listFolderDTO;
    }

    @Override
    @Transactional
    public int createFolder(Folder folder) {
        return folderRepository.createFolder(folder);
    }
}
