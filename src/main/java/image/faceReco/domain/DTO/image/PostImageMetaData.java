package image.faceReco.domain.DTO.image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostImageMetaData {
    private String imageName;
    private String date;
    private String uploadDate;
    private Double FStep;
    private Double shutterSpeed;
    private Integer ISO;
    private List<Double> location;
    private String camera;
    private Double imageSize;
    private Integer ownerAlbumId;
}
