package image.faceReco.service.image;

import image.faceReco.domain.document.Image;

public interface ImageService {
    Image createImage(Image image, byte[] imageByte);
}
