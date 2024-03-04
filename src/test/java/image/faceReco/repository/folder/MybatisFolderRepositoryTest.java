package image.faceReco.repository.folder;

import image.faceReco.domain.entity.Folder;
import image.faceReco.domain.updateParam.FolderNameUpdateParam;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;


@Transactional
@SpringBootTest
class MybatisFolderRepositoryTest {
    @Autowired
    private FolderRepository folderRepository;
    @Test
    public void createFolder(){
        //given
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String nowDate = format.format(now);
        Folder folder = new Folder(1,null, "myTestFolder",nowDate);

        //when
        int index = folderRepository.createFolder(folder);

        //Then
        Assertions.assertThat(index).isEqualTo(1);
    }

    @Test
    public void setFolderRepository(){
        //given
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String nowDate = format.format(now);
        Folder folder = new Folder(1,null, "myTestFolder",nowDate);
        folderRepository.createFolder(folder);
        FolderNameUpdateParam updateParam = new FolderNameUpdateParam(folder.getFolderId(), "myNewTestFolder");
        //when
        folderRepository.updateFolderName(updateParam);

        //then
        String changedName = folderRepository.selectFolderByUserId(folder.getFolderId()).get(0).getFolderName();
        Assertions.assertThat(changedName).isEqualTo("myNewTestFolder");
    }
}