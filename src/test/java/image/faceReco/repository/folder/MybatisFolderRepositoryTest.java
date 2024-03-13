package image.faceReco.repository.folder;

import image.faceReco.domain.entity.Folder;
import image.faceReco.domain.updateParam.IdListParam;
import image.faceReco.domain.updateParam.IdListParentIdParam;
import image.faceReco.domain.updateParam.ParentIdNameListParam;
import image.faceReco.domain.updateParam.RepositoryNameUpdateParam;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


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
        List<Folder> createdFolder = folderRepository.createFolder(folder);

        //Then
        Assertions.assertThat(createdFolder.size()).isEqualTo(1);
    }

    @Test
    public void setFolderRepository(){
        //given
        RepositoryNameUpdateParam updateParam = new RepositoryNameUpdateParam(1, this.testFolder.getFolderId(), "myNewTestFolder");

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

    @Test
    public void deleteFolderByFoldrIdList(){
        //given
        int ownerId = 1;
        Folder savedFolder1 = new Folder(ownerId,null, "testFolder1", "2024-03-06");
        Folder savedFolder2 = new Folder(ownerId,testFolder.getFolderId(), "testFolder1", "2024-03-06");
        Folder savedFolder3 = new Folder(ownerId,null, "testFolder1", "2024-03-06");
        folderRepository.createFolder(savedFolder1);
        folderRepository.createFolder(savedFolder2);
        folderRepository.createFolder(savedFolder3);
        List<Integer> folderIdList = new ArrayList<>();
        folderIdList.add(savedFolder1.getFolderId());
        folderIdList.add(savedFolder2.getFolderId());
        folderIdList.add(savedFolder3.getFolderId());
        IdListParam param = new IdListParam(ownerId, folderIdList);

        //when
        folderRepository.deleteFolderByFoldrIdList(param);

        //then
        Assertions.assertThat(folderRepository.selectFolderByFolderId(savedFolder1.getFolderId()).isEmpty()).isEqualTo(true);
        Assertions.assertThat(folderRepository.selectFolderByFolderId(savedFolder2.getFolderId()).isEmpty()).isEqualTo(true);
        Assertions.assertThat(folderRepository.selectFolderByFolderId(savedFolder3.getFolderId()).isEmpty()).isEqualTo(true);
    }

    @Test
    public void updateFolderParentIdByFolderIdList(){
        //given
        Integer ownerID = 1;
        Folder savedFolder1 = new Folder(ownerID,null, "testFolder1", "2024-03-06");
        Folder savedFolder2 = new Folder(ownerID,null, "testFolder1", "2024-03-06");
        Folder savedFolder3 = new Folder(ownerID,null, "testFolder1", "2024-03-06");
        folderRepository.createFolder(savedFolder1);
        folderRepository.createFolder(savedFolder2);
        folderRepository.createFolder(savedFolder3);
        List<Integer> updateList = new ArrayList<>();
        updateList.add(savedFolder1.getFolderId());
        updateList.add(savedFolder2.getFolderId());
        updateList.add(savedFolder3.getFolderId());
        IdListParentIdParam  param = new IdListParentIdParam(ownerID, testFolder.getFolderId() , updateList);

        //when
        folderRepository.updateFolderParentIdByFolderIdList(param);

        //then
        Assertions.assertThat(folderRepository.selectFolderByFolderId(savedFolder1.getFolderId()).get(0).getParentFolderId()).isEqualTo(testFolder.getFolderId());
        Assertions.assertThat(folderRepository.selectFolderByFolderId(savedFolder2.getFolderId()).get(0).getParentFolderId()).isEqualTo(testFolder.getFolderId());
        Assertions.assertThat(folderRepository.selectFolderByFolderId(savedFolder3.getFolderId()).get(0).getParentFolderId()).isEqualTo(testFolder.getFolderId());
    }

    @Test
    public void selectFolderByParentFolderIdFolderName(){
        //given
        int ownerId = 1;
        Folder savedFolder1 = new Folder(ownerId,null, UUID.randomUUID().toString(), "2024-03-06");
        folderRepository.createFolder(savedFolder1);
        Folder savedFolder2 = new Folder(ownerId,savedFolder1.getFolderId(), UUID.randomUUID().toString(), "2024-03-06");
        Folder savedFolder3 = new Folder(ownerId,savedFolder1.getFolderId(), UUID.randomUUID().toString(), "2024-03-06");
        folderRepository.createFolder(savedFolder2);
        folderRepository.createFolder(savedFolder3);

        List<String> folderNameList1 = List.of(savedFolder1.getFolderName());
        List<String> folderNameList2 = List.of(savedFolder2.getFolderName(),savedFolder3.getFolderName());
        ParentIdNameListParam param1 = new ParentIdNameListParam(ownerId, null, folderNameList1);
        ParentIdNameListParam param2 = new ParentIdNameListParam(ownerId, savedFolder1.getFolderId(), folderNameList2);

        //when
        List<String> returnNameList1 = folderRepository.selectFolderByParentFolderIdFolderName(param1);
        List<String> returnNameList2 = folderRepository.selectFolderByParentFolderIdFolderName(param2);

        Assertions.assertThat(returnNameList1).contains(savedFolder1.getFolderName());
        Assertions.assertThat(returnNameList2).contains(savedFolder2.getFolderName(), savedFolder3.getFolderName());
    }
}