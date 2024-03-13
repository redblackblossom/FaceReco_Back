package image.faceReco.service.folder;

import image.faceReco.domain.DTO.folder.FolderDTO;
import image.faceReco.domain.DTO.repository.RepositoryCreateDTO;
import image.faceReco.domain.entity.Folder;
import image.faceReco.domain.updateParam.RepositoryNameUpdateParam;
import image.faceReco.exception.DuplicateNameException;
import image.faceReco.repository.folder.FolderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
@Transactional
class FolderServiceImpTest {
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private FolderService folderService;

    private Folder testFolder;
    private final int ownerId = 1;
    @BeforeEach
    public void before(){
        //given
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String nowDate = format.format(now);
        this.testFolder = new Folder(1,null, "myTestFolder",nowDate);
        folderRepository.createFolder(this.testFolder);
    }

    @AfterEach
    public void after(){
        this.testFolder = null;
    }

    @Test
    public void createFolder(){
        //given
        String sameName = UUID.randomUUID().toString();
        RepositoryCreateDTO repositoryCreateDTO1 = new RepositoryCreateDTO(ownerId,null, sameName,"2024-03-13");
        RepositoryCreateDTO repositoryCreateDTO2 = new RepositoryCreateDTO(ownerId,null, sameName,"2024-03-13");

        //when
        FolderDTO folderDTO = folderService.createFolder(repositoryCreateDTO1);

        //then
        Assertions.assertThat(folderDTO.getFolderName()).isNotNull();
        Assertions.assertThatThrownBy(()->folderService.createFolder(repositoryCreateDTO2)).isInstanceOf(DuplicateNameException.class);
    }

    @Test
    public void updateFolderName(){
        //given
        RepositoryCreateDTO createDTO = new RepositoryCreateDTO(ownerId,testFolder.getParentFolderId(), UUID.randomUUID().toString(),"2024-03-13");
        FolderDTO folderDTO =folderService.createFolder(createDTO);
        RepositoryNameUpdateParam param = new RepositoryNameUpdateParam(createDTO.getOwnerId(), folderDTO.getFolderId(), UUID.randomUUID().toString());
        RepositoryNameUpdateParam DuplicateParam = new RepositoryNameUpdateParam(createDTO.getOwnerId(), folderDTO.getFolderId(), testFolder.getFolderName());

        //when
        int updateCount = folderService.updateFolderName(param);

        //then
        Assertions.assertThat(updateCount).isEqualTo(1);
        Assertions.assertThatThrownBy(()->folderService.updateFolderName(DuplicateParam)).isInstanceOf(DuplicateNameException.class);
    }
}