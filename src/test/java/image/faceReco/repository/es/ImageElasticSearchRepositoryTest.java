package image.faceReco.repository.es;

import image.faceReco.domain.document.Image;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImageElasticSearchRepositoryTest {
    @Autowired
    private ImageElasticSearchRepository imageElasticSearchRepository;

    @Test
    public void findByOwnerIdAndOwnerAlbumId(){
        //given
        int ownerId = 1;
        int ownerAlbumId = 100;
        Image image1 =  Image.builder()
                .id(null)
                .imageName("simpleName")
                .date("2024-03-02")
                .uploadDate("2024-03-22")
                .FStep(4.0)
                .shutterSpeed(400.0)
                .ISO(200)
                .location(null)
                .camera("a7M4")
                .imageSize(5.0)
                .ownerId(1)
                .ownerAlbumId(ownerAlbumId)
                .caption("simple test image!")
                .awsName("11")
                .detectedFace(null)
                .build();
        Image image2 =  Image.builder()
                .id(null)
                .imageName("simpleName")
                .date("2024-03-02")
                .uploadDate("2024-03-22")
                .FStep(4.0)
                .shutterSpeed(400.0)
                .ISO(200)
                .location(null)
                .camera("a7M4")
                .imageSize(5.0)
                .ownerId(1)
                .ownerAlbumId(ownerAlbumId)
                .caption("simple test image!")
                .awsName("11")
                .detectedFace(null)
                .build();
        imageElasticSearchRepository.save(image1);
        imageElasticSearchRepository.save(image2);

        //when
        List<Image> findImage = imageElasticSearchRepository.findByOwnerIdAndOwnerAlbumId(ownerId, ownerAlbumId);

        //then
        Assertions.assertThat(findImage.size()).isEqualTo(2);

        //after
        //imageElasticSearchRepository.delete(image1);
        //imageElasticSearchRepository.delete(image2);
    }
}