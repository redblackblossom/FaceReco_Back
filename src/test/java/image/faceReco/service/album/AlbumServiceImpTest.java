package image.faceReco.service.album;

import image.faceReco.domain.DTO.album.AlbumDTO;
import image.faceReco.domain.DTO.repository.RepositoryCreateDTO;
import image.faceReco.domain.entity.Album;
import image.faceReco.domain.entity.Folder;
import image.faceReco.domain.updateParam.RepositoryNameUpdateParam;
import image.faceReco.exception.DuplicateNameException;
import image.faceReco.repository.album.AlbumRepository;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AlbumServiceImpTest {
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private AlbumService albumService;

    private Folder testFolder;
    private Album testAlbum;
    private final int ownerId = 1;

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
        String name = UUID.randomUUID().toString();
        RepositoryCreateDTO repositoryCreateDTO = new RepositoryCreateDTO(ownerId, testFolder.getFolderId(),name , "2024-03-13");
        RepositoryCreateDTO duplicateName = new RepositoryCreateDTO(ownerId, testFolder.getFolderId(), name, "2024-03-13");

        //when
        AlbumDTO albumDTO = albumService.createAlbum(repositoryCreateDTO);

        //then
        Assertions.assertThat(albumDTO).isNotNull();
        Assertions.assertThatThrownBy(()->albumService.createAlbum(duplicateName)).isInstanceOf(DuplicateNameException.class);
    }

    @Test
    public void updateAlbumNameByAlumId(){
        //given
        String name = UUID.randomUUID().toString();
        RepositoryCreateDTO repositoryCreateDTO = new RepositoryCreateDTO(ownerId, testAlbum.getOwnerFolderId(),name , "2024-03-13");
        AlbumDTO albumDTO = albumService.createAlbum(repositoryCreateDTO);
        RepositoryNameUpdateParam updateParam = new RepositoryNameUpdateParam(ownerId,albumDTO.getAlbumId(), UUID.randomUUID().toString());
        RepositoryNameUpdateParam duplicateParam = new RepositoryNameUpdateParam(ownerId,albumDTO.getAlbumId(), testAlbum.getAlbumName());

        //when
        int updateCount =  albumService.updateAlbumNameByAlumId(updateParam);

        //then
        Assertions.assertThat(updateCount).isEqualTo(1);
        Assertions.assertThatThrownBy(()->albumService.updateAlbumNameByAlumId(duplicateParam)).isInstanceOf(DuplicateNameException.class);

    }

}