package image.faceReco.service.image;

import image.faceReco.domain.document.Image;
import image.faceReco.repository.es.ImageElasticSearchRepository;
import image.faceReco.repository.s3.S3Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImp implements ImageService{
    private final ImageElasticSearchRepository imageElasticSearchRepository;
    private final S3Repository s3Repository;
    @Override
    public Image createImage(Image image, byte[] imageByte) {
        //image에는 caption, detectedFace가 비어있음
        imageElasticSearchRepository.save(image);
        s3Repository.uploadImageByte(image.getAwsName(),imageByte);
        return image;
    }
}
