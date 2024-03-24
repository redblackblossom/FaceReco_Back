package image.faceReco.controller;

import image.faceReco.domain.API.ApiResponseBody;
import image.faceReco.domain.DTO.image.PostImageMetaData;
import image.faceReco.domain.document.Image;
import image.faceReco.methodArgumentResolver.resolverInterface.UserId;
import image.faceReco.repository.s3.S3Repository;
import image.faceReco.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
@Slf4j
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/test")
    public ResponseEntity<String> simpleTest(@RequestPart(name = "test1", required = false) String test1,
                                             @RequestPart(name = "test2" , required = false)MultipartFile test2){
        //용량을 넘게 되면 MaxUploadSizeExceededException발생시킴
        byte[] imageByte = null;
        try{
            imageByte = test2.getBytes();
        } catch(IOException e){

        }
        return ResponseEntity.status(HttpStatus.OK).body("test1 : " + test1 + " test2 : "+ imageByte);
    }

    @PostMapping
    public ResponseEntity<ApiResponseBody> uploadImage(@UserId Integer userId, @RequestPart(name="metaData") PostImageMetaData imageMetaData,
                                                        @RequestPart(name="imageFile") MultipartFile imageFile) throws  IOException{
        Image image = Image.fromPostImageMetaData(userId, imageMetaData);
        imageService.createImage(image,imageFile.getBytes());
        ApiResponseBody apiResponseBody = new ApiResponseBody(true, "create new image!");
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponseBody);
    }
}
