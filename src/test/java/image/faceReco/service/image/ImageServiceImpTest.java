package image.faceReco.service.image;

import image.faceReco.domain.document.Image;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImageServiceImpTest {
    @Autowired
    private ImageService imageService;

    @Test
    public void createImage(){
        Image image =  Image.builder()
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
                .ownerAlbumId(11)
                .caption("simple test image!")
                .awsName("asd/11")
                .detectedFace(null)
                .build();
        imageService.createImage(image, null);
        Assertions.assertThat(image.getId()).isNotNull();
        System.out.println(image.getId());
    }
}