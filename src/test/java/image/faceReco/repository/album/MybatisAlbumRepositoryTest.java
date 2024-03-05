package image.faceReco.repository.album;

import image.faceReco.domain.entity.Album;
import image.faceReco.domain.entity.Folder;
import image.faceReco.domain.updateParam.AlbumNameUpdateParam;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        int createdCount = albumRepository.createAlbum(createdAlbum);
        List<Album> finedAlbum = albumRepository.selectAlbumByAlbumId(createdAlbum.getAlbumId());

        //then
        Assertions.assertThat(createdCount).isEqualTo(1);
        Assertions.assertThat(createdAlbum).isEqualTo(finedAlbum.get(0));
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
    public void updateAlbumNameByAlbumId(){
        //given
        AlbumNameUpdateParam updateParam = new AlbumNameUpdateParam(this.testAlbum.getAlbumId(), "newAlbumName");

        //when
        int updatedCount = albumRepository.updateAlbumNameByAlbumId(updateParam);

        //then
        Assertions.assertThat(updatedCount).isEqualTo(1);
        String updatedName = albumRepository.selectAlbumByAlbumId(this.testAlbum.getAlbumId()).get(0).getAlbumName();
        Assertions.assertThat(updatedName).isEqualTo("newAlbumName");
    }


}