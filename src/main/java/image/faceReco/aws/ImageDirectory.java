package image.faceReco.aws;

public class ImageDirectory {
    private static final String RAW = "raw";
    private static final String COMPACTION = "compaction";
    public static String toRawDirectory(String imageUuid){
        return ImageDirectory.RAW + "/" + imageUuid;
    }
    public static String toCompactionDirectory(String imageUuid){
        return ImageDirectory.COMPACTION +"/" + imageUuid;
    }
}
