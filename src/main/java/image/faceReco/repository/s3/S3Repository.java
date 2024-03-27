package image.faceReco.repository.s3;

import image.faceReco.domain.document.Image;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Repository
@Slf4j
@RequiredArgsConstructor
public class S3Repository {
    private final S3Client s3Client;
    @Value("${aws.s3.bucketName}")
    private String bucketName;

    public void uploadImageByte(String imageDir, byte[] imageByte){
        PutObjectRequest objectRequest = PutObjectRequest.builder().
                bucket(this.bucketName)
                .key(imageDir)
                .build();
        s3Client.putObject(objectRequest, RequestBody.fromBytes(imageByte));
    }

    public byte[] downloadImageByte(String imageDir){
        GetObjectRequest objectRequest = GetObjectRequest
                .builder()
                .bucket(this.bucketName)
                .key(imageDir)
                .build();
        ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(objectRequest);
        return objectBytes.asByteArray();
    }
}
