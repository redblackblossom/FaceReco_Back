package image.faceReco.domain.document;

import image.faceReco.aws.ImageDirectory;
import image.faceReco.domain.DTO.image.PostImageMetaData;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Document(indexName = "image")
public class Image {
    @Id
    private String id;
    @Field(type = FieldType.Keyword)
    private String imageName;
    @Field(type = FieldType.Date, format =DateFormat.year_month_day)
    private String date;
    @Field(type = FieldType.Date, format =DateFormat.year_month_day)
    private String uploadDate;
    @Field(type=FieldType.Scaled_Float, scalingFactor = 10)
    private Double FStep;
    @Field(type=FieldType.Double)
    private Double shutterSpeed;
    @Field(type=FieldType.Integer)
    private Integer ISO;
    @GeoPointField
    private GeoPoint location;
    @Field(type=FieldType.Keyword)
    private String camera;
    @Field(type=FieldType.Double)
    private Double imageSize;
    @Field(type=FieldType.Integer)
    private Integer ownerId;
    @Field(type=FieldType.Integer)
    private Integer ownerAlbumId;
    @Field(type=FieldType.Text)
    private String caption;
    @Field(type=FieldType.Keyword)
    private String awsName;
    @Field(type=FieldType.Integer)
    private List<Integer> detectedFace;

    public static Image fromPostImageMetaData(int userId, PostImageMetaData imageMetaData){
        String uuid = UUID.randomUUID().toString();
        String awsDir = imageMetaData.getOwnerAlbumId().toString() + "/" + ImageDirectory.RAW + "/" + uuid +imageMetaData.getImageName();
        GeoPoint geoPoint;
        if(imageMetaData.getLocation()==null)
            geoPoint =null;
        else{
            geoPoint = new GeoPoint(imageMetaData.getLocation().get(0), imageMetaData.getLocation().get(1));
        }
        return Image.builder()
                .ownerId(userId)
                .imageName(imageMetaData.getImageName())
                .date(imageMetaData.getDate())
                .uploadDate(imageMetaData.getUploadDate())
                .FStep(imageMetaData.getFStep())
                .shutterSpeed(imageMetaData.getShutterSpeed())
                .ISO(imageMetaData.getISO())
                .location(geoPoint)
                .camera(imageMetaData.getCamera())
                .imageSize(imageMetaData.getImageSize())
                .ownerAlbumId(imageMetaData.getOwnerAlbumId())
                .awsName(awsDir)
                .caption(null)
                .detectedFace(null)
                .build();
    }
}
