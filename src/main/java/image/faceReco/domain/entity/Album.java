package image.faceReco.domain.entity;

import lombok.Data;

@Data
public class Album {
    int albumId;
    int ownerId;
    int ownerFolder;
    String folderName;
    String createDate;
}
