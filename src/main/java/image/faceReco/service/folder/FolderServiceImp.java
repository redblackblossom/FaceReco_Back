package image.faceReco.service.folder;

import image.faceReco.domain.DTO.folder.FolderDTO;
import image.faceReco.domain.entity.Folder;
import image.faceReco.domain.DTO.repository.RepositoryCreateDTO;
import image.faceReco.domain.updateParam.ParentIdNameListParam;
import image.faceReco.domain.updateParam.RepositoryNameUpdateParam;
import image.faceReco.exception.DuplicateNameException;
import image.faceReco.repository.folder.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FolderServiceImp implements FolderService{
    private final FolderRepository folderRepository;
    @Override
    @Transactional(readOnly = true)
    public List<FolderDTO> findAllFolderByOwnerId(int userId) {
        List<Folder> listFolder = folderRepository.selectFolderByOwnerId(userId);
        List<FolderDTO> listFolderDTO = new ArrayList<>();
        for(Folder folder : listFolder){
            listFolderDTO.add(FolderDTO.fromFolder(folder));
        }
        return listFolderDTO;
    }
    @Override
    public FolderDTO createFolder(RepositoryCreateDTO repositoryCreateDTO) {
        Folder folder = Folder.fromRepositoryCreateParam(repositoryCreateDTO);
        checkDuplicateFolderName(folder.getOwnerId(), folder.getParentFolderId(), folder.getFolderName());
        List<Folder> createdFolder = folderRepository.createFolder(folder);

        //나중에 오류 추가 예정(폴더 못만든 예외)
        return FolderDTO.fromFolder(createdFolder.get(0));
    }
    @Override
    public int updateFolderName(RepositoryNameUpdateParam repositoryNameUpdateParam) {
        Integer parentFolderId = folderRepository.selectFolderByFolderId(repositoryNameUpdateParam.getRepositoryId()).get(0).getParentFolderId();
        checkDuplicateFolderName(repositoryNameUpdateParam.getOwnerId(),parentFolderId,
                repositoryNameUpdateParam.getUpdateRepositoryName());
        return folderRepository.updateFolderName(repositoryNameUpdateParam);
    }

    private void checkDuplicateFolderName(int ownerId, Integer parentFolderId, String name){
        ParentIdNameListParam param = new ParentIdNameListParam(ownerId,parentFolderId, List.of(name));
        List<String> duplicateNameList =   folderRepository.selectFolderByParentFolderIdFolderName(param);
        if(!duplicateNameList.isEmpty()){
            throw new DuplicateNameException(duplicateNameList);
        }
    }
}
