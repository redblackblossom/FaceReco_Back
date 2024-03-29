package image.faceReco.repository.album;

import image.faceReco.domain.entity.Album;
import image.faceReco.domain.entity.Folder;
import image.faceReco.domain.updateParam.IdListParam;
import image.faceReco.domain.updateParam.IdListParentIdParam;
import image.faceReco.domain.updateParam.ParentIdNameListParam;
import image.faceReco.domain.updateParam.RepositoryNameUpdateParam;
import image.faceReco.repository.folder.FolderRepository;
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

@SpringBootTest
@Transactional
class MybatisAlbumRepositoryTest {
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private FolderRepository folderRepository;

    private Folder testFolder;
    private Album testAlbum;

    @BeforeEach
    public void before(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String nowDate = format.format(now);

        this.testFolder = new Folder(1,null, "myTestFolder",nowDate);
        folderRepository.createFolder(this.testFolder);
        this.testAlbum = new Album(1, testFolder.getFolderId(), "myTestAlbum", nowDate);
        albumRepository.createAlbum(testAlbum);
    }
    @AfterEach
    public void after(){
        testFolder = null;
        testAlbum = null;
    }

    @Test
    public void createAlbum(){
        //given
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String nowDate = format.format(now);
        Album createdAlbum = new Album(1, testFolder.getFolderId(), "myTestAlbum", nowDate);

        //when
        List<Album> createAlbumList = albumRepository.createAlbum(createdAlbum);

        //then
        Assertions.assertThat(createAlbumList.size()).isEqualTo(1);
    }

    @Test
    public void selectAlbumByParentFolderId(){
        //given
        int parentFolderId = this.testFolder.getFolderId();

        //when
        List<Album> finedAlbum = albumRepository.selectAlbumByParentFolderId(parentFolderId);

        //then
        Assertions.assertThat(finedAlbum.get(0)).isEqualTo(this.testAlbum);
    }

    @Test
    public void deleteAlbumByAlbumId(){
        //given
        int albumId = this.testAlbum.getAlbumId();

        //when
        int deletedCount = albumRepository.deleteAlbumByAlbumId(albumId);

        //then
        Assertions.assertThat(deletedCount).isEqualTo(1);
        Assertions.assertThat(albumRepository.selectAlbumByAlbumId(albumId).isEmpty()).isEqualTo(true);
    }

    @Test
    public void updateFolderName(){
        //given
        int ownerId = 1;
        RepositoryNameUpdateParam repositoryNameUpdateParam = new RepositoryNameUpdateParam(ownerId, testAlbum.getAlbumId(), "changeAlbumName");

        //when
        int updatedCount = albumRepository.updateAlbumNameByAlbumId(repositoryNameUpdateParam);

        //then
        Assertions.assertThat(updatedCount).isEqualTo(1);
        String updatedName = albumRepository.selectAlbumByAlbumId(this.testAlbum.getAlbumId()).get(0).getAlbumName();
        Assertions.assertThat(updatedName).isEqualTo("changeAlbumName");
    }

    @Test
    public void selectAlbumByOwnerId(){
        //given
        int ownerId = 1;
        int beforeCount = albumRepository.selectAlbumByOwnerId(ownerId).size();
        Album savedAlbum1 = new Album(ownerId, testFolder.getFolderId(),
                    "testAlbum1", "2024-03-06");
        Album savedAlbum2 = new Album(ownerId, testFolder.getFolderId(),
                "testAlbum2", "2024-03-06");
        Album savedAlbum3 = new Album(ownerId, testFolder.getFolderId(),
                "testAlbum3", "2024-03-06");
        albumRepository.createAlbum(savedAlbum1);
        albumRepository.createAlbum(savedAlbum2);
        albumRepository.createAlbum(savedAlbum3);

        //when
        int foundCount = albumRepository.selectAlbumByOwnerId(ownerId).size();

        //then
        Assertions.assertThat(foundCount).isEqualTo(beforeCount + 3);
    }

    @Test
    public void deleteAlbumByAlbumIdList(){
        //given
        int ownerId = 1;
        Album createAlbum1 = new Album(ownerId, null, "testAlbum1", "2024-03-10");
        Album createAlbum2 = new Album(ownerId, testFolder.getFolderId(), "testAlbum2", "2024-03-10");
        Album createAlbum3 = new Album(ownerId, testFolder.getFolderId(), "testAlbum3", "2024-03-10");
        albumRepository.createAlbum(createAlbum1);
        albumRepository.createAlbum(createAlbum2);
        albumRepository.createAlbum(createAlbum3);
        List<Integer> deleteAlbumIdList = new ArrayList<>();
        deleteAlbumIdList.add(createAlbum1.getAlbumId());
        deleteAlbumIdList.add(createAlbum2.getAlbumId());
        deleteAlbumIdList.add(createAlbum3.getAlbumId());
        IdListParam param = new IdListParam(ownerId, deleteAlbumIdList);

        //when
        int count = albumRepository.deleteAlbumByAlbumIdList(param);

        //then
        Assertions.assertThat(albumRepository.selectAlbumByAlbumId(createAlbum1.getAlbumId()).isEmpty()).isEqualTo(true);
        Assertions.assertThat(albumRepository.selectAlbumByAlbumId(createAlbum2.getAlbumId()).isEmpty()).isEqualTo(true);
        Assertions.assertThat(albumRepository.selectAlbumByAlbumId(createAlbum3.getAlbumId()).isEmpty()).isEqualTo(true);
    }

    @Test
    public void updateAlbumNameByAlbumIdList(){
        //given
        int ownerId = 1;
        Album createAlbum1 = new Album(ownerId, null, "testAlbum1", "2024-03-10");
        Album createAlbum2 = new Album(ownerId, null, "testAlbum2", "2024-03-10");
        Album createAlbum3 = new Album(ownerId, null, "testAlbum3", "2024-03-10");
        albumRepository.createAlbum(createAlbum1);
        albumRepository.createAlbum(createAlbum2);
        albumRepository.createAlbum(createAlbum3);
        List<Integer> updateAlbum = new ArrayList<>();
        updateAlbum.add(createAlbum1.getAlbumId());
        updateAlbum.add(createAlbum2.getAlbumId());
        updateAlbum.add(createAlbum3.getAlbumId());
        IdListParentIdParam param = new IdListParentIdParam(ownerId, testFolder.getFolderId(), updateAlbum);

        //when
        int count = albumRepository.updateAlbumNameByAlbumIdList(param);
        Integer parent1 = albumRepository.selectAlbumByAlbumId(createAlbum1.getAlbumId()).get(0).getOwnerFolderId();
        Integer parent2 = albumRepository.selectAlbumByAlbumId(createAlbum2.getAlbumId()).get(0).getOwnerFolderId();
        Integer parent3 = albumRepository.selectAlbumByAlbumId(createAlbum3.getAlbumId()).get(0).getOwnerFolderId();

        //then
        Assertions.assertThat(parent1).isEqualTo(testFolder.getFolderId());
        Assertions.assertThat(parent2).isEqualTo(testFolder.getFolderId());
        Assertions.assertThat(parent3).isEqualTo(testFolder.getFolderId());
    }

    @Test
    public void selectAlbumByParentFolderIdFolderName(){
        //given
        int ownerId = 1;
        Album createAlbum1 = new Album(ownerId, null, UUID.randomUUID().toString(), "2024-03-10");
        albumRepository.createAlbum(createAlbum1);
        Album createAlbum2 = new Album(ownerId, testFolder.getFolderId(), UUID.randomUUID().toString(), "2024-03-10");
        Album createAlbum3 = new Album(ownerId, testFolder.getFolderId(), UUID.randomUUID().toString(), "2024-03-10");
        albumRepository.createAlbum(createAlbum2);
        albumRepository.createAlbum(createAlbum3);
        List<String> nameList1 =List.of(createAlbum1.getAlbumName());
        List<String> nameList2 =List.of(createAlbum2.getAlbumName(), createAlbum3.getAlbumName());
        ParentIdNameListParam param1 = new ParentIdNameListParam(ownerId, null, nameList1);
        ParentIdNameListParam param2 = new ParentIdNameListParam(ownerId, testFolder.getFolderId(), nameList2);

        //when
        List<String> findNameList1 = albumRepository.selectAlbumByOwnerFolderIdFolderName(param1);
        List<String> findNameList2 = albumRepository.selectAlbumByOwnerFolderIdFolderName(param2);

        //then
        Assertions.assertThat(findNameList1).contains(createAlbum1.getAlbumName());
        Assertions.assertThat(findNameList2).contains(createAlbum2.getAlbumName(), createAlbum3.getAlbumName());

    }

}