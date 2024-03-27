package image.faceReco.domain.DTO.image;

import image.faceReco.domain.document.Image;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
    private String imageName;
    private String date;
    private String uploadDate;
    private Double FStep;
    private Double shutterSpeed;
    private Integer ISO;
    private List<Double> location;
    private String camera;
    private Double imageSize;
    private Integer ownerId;
    private Integer ownerAlbumId;
    private String caption;
    private String imageUuid;
    private List<Integer> detectedFace;

    public static ImageDTO fromImage(Image image){
        List<Double> location = new ArrayList<>();
        if(image.getLocation()!=null){
            //경도 먼저 추가
            location.add(image.getLocation().getLon());
            location.add(image.getLocation().getLat());
        }
        return ImageDTO.builder()
                .imageName(image.getImageName())
                .date(image.getDate())
                .uploadDate(image.getUploadDate())
                .FStep(image.getFStep())
                .shutterSpeed(image.getShutterSpeed())
                .ISO(image.getISO())
                .location(location)
                .camera(image.getCamera())
                .imageSize(image.getImageSize())
                .ownerId(image.getOwnerId())
                .ownerAlbumId(image.getOwnerAlbumId())
                .caption(image.getCaption())
                .imageUuid(image.getAwsName())
                .detectedFace(image.getDetectedFace())
                .build();
    }
}