package image.faceReco.controller;

import image.faceReco.domain.API.ApiResponseBody;
import image.faceReco.domain.DTO.FolderDTO;
import image.faceReco.domain.DTO.PostFolderDTO;
import image.faceReco.domain.entity.Folder;
import image.faceReco.methodArgumentResolver.resolverInterface.UserId;
import image.faceReco.service.folder.FolderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/api/folders")
@Slf4j
@RequiredArgsConstructor
public class FolderController {
    private final FolderService folderService;

    @GetMapping
    public ResponseEntity<Map<String,List<FolderDTO>>> findAllFolder(@UserId Integer userId){
        List<FolderDTO> folderDTOList = folderService.findAllFolderByOwnerId(userId);
        Map<String, List<FolderDTO>> folderMap = new HashMap<>();
        folderMap.put("folders", folderDTOList);

        return ResponseEntity.ok(folderMap);
    }

    @PostMapping
    public ResponseEntity<ApiResponseBody> createNewFolder(@UserId Integer ownerId,
                                                           @RequestBody PostFolderDTO postFolderDTO){
        Folder folder = postFolderDTO.toFolder(ownerId);
        int createdCount = folderService.createFolder(folder);
        ApiResponseBody apiResponseBody = new ApiResponseBody();
        if(createdCount==0){
            apiResponseBody.setSuccess(false);
            apiResponseBody.setMessage("Fail to create folder!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponseBody);
        }
        else{
            apiResponseBody.setSuccess(true);
            apiResponseBody.setMessage("Success to create folder!");
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponseBody);
        }
    }
/*
    @DeleteMapping
    public ResponseEntity<ApiResponseBody> deleteFolder(@UserId Integer ownerId, )

 */
}
