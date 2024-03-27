package image.faceReco.service.image;

import image.faceReco.aws.ImageDirectory;
import image.faceReco.domain.DTO.image.ImageDTO;
import image.faceReco.domain.document.Image;
import image.faceReco.repository.es.ImageElasticSearchRepository;
import image.faceReco.repository.s3.S3Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageServiceImp implements ImageService{
    private final ImageElasticSearchRepository imageElasticSearchRepository;
    private final S3Repository s3Repository;
    @Override
    public Image createImage(Image image, byte[] imageByte) {
        //image에는 caption, detectedFace가 비어있음
        imageElasticSearchRepository.save(image);
        if(imageByte!=null){
            String imageDir = ImageDirectory.toRawDirectory(image.getAwsName());
            s3Repository.uploadImageByte(imageDir,imageByte);
        }
        return image;
    }

    @Override
    public List<ImageDTO> findImageMetaDataByByAlbumId(int ownerId, Integer ownerAlbumId) {
        List<Image> imageMetaDataList = ownerAlbumId==null ?
                imageElasticSearchRepository.findByOwnerId(ownerId):
                imageElasticSearchRepository.findByOwnerIdAndOwnerAlbumId(ownerId, ownerAlbumId);
        return imageMetaDataList.stream().map(ImageDTO::fromImage).toList();
    }

    @Override
    public byte[] loadImageByte(String imageDirectory) {
        return s3Repository.downloadImageByte(imageDirectory);
    }
}
