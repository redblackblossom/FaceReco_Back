package image.faceReco.domain.updateParam.album;

public class AlbumNameUpdateParam {
    int albumId;
    String updateAlbumName;

    public AlbumNameUpdateParam(int albumId, String updateAlbumName) {
        this.albumId = albumId;
        this.updateAlbumName = updateAlbumName;
    }
}
