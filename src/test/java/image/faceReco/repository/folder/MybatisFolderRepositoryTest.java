package image.faceReco.repository.folder;

import image.faceReco.domain.entity.Folder;
import image.faceReco.domain.updateParam.FolderNameUpdateParam;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Transactional
@SpringBootTest
class MybatisFolderRepositoryTest {
    @Autowired
    private FolderRepository folderRepository;
    private Folder testFolder;

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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String nowDate = format.format(now);
        Folder folder = new Folder(1,null, "myTestFolder2",nowDate);

        //when
        int index = folderRepository.createFolder(folder);

        //Then
        Assertions.assertThat(index).isEqualTo(1);
    }

    @Test
    public void setFolderRepository(){
        //given
        FolderNameUpdateParam updateParam = new FolderNameUpdateParam(this.testFolder.getFolderId(), "myNewTestFolder");

        //when
        int updateCount = folderRepository.updateFolderName(updateParam);

        //then
        String changedName = folderRepository.selectFolderByFolderId(this.testFolder.getFolderId()).get(0).getFolderName();
        Assertions.assertThat(changedName).isEqualTo("myNewTestFolder");
        Assertions.assertThat(updateCount).isEqualTo(1);
    }

    @Test
    public void deleteFolderByFolderId(){
        //given
        Integer folderId = this.testFolder.getFolderId();

        //when
        int deleteCount = folderRepository.deleteFolderByFolderId(folderId);

        //then
        Assertions.assertThat(deleteCount).isEqualTo(1);
        Assertions.assertThat(folderRepository.selectFolderByFolderId(folderId).isEmpty()).isEqualTo(true);
    }

    @Test
    public void selectFolderByOwnerId(){
        //given
        Integer ownerID = 1;
        int beforeCount = folderRepository.selectFolderByOwnerId(ownerID).size();
        Folder savedFolder1 = new Folder(ownerID,null, "testFolder1", "2024-03-06");
        Folder savedFolder2 = new Folder(ownerID,null, "testFolder1", "2024-03-06");
        Folder savedFolder3 = new Folder(ownerID,null, "testFolder1", "2024-03-06");
        folderRepository.createFolder(savedFolder1);
        folderRepository.createFolder(savedFolder2);
        folderRepository.createFolder(savedFolder3);

        //when
        List<Folder> foundFolderList = folderRepository.selectFolderByOwnerId(ownerID);

        //then
        Assertions.assertThat(foundFolderList.size()).isEqualTo(3 + beforeCount);

    }
}