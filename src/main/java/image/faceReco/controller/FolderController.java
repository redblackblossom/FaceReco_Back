package image.faceReco.controller;

import image.faceReco.domain.API.ApiResponseBody;
import image.faceReco.domain.DTO.repository.PatchRepositoryNameDTO;
import image.faceReco.domain.DTO.repository.PostRepositoryDTO;
import image.faceReco.domain.DTO.folder.*;
import image.faceReco.domain.DTO.repository.RepositoryCreateDTO;
import image.faceReco.domain.updateParam.RepositoryNameUpdateParam;
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
    public ResponseEntity<FolderDTO> createNewFolder(@UserId Integer ownerId,
                                                           @RequestBody PostRepositoryDTO postRepositoryDTO){
        RepositoryCreateDTO repositoryCreateDTO = RepositoryCreateDTO.fromPostRepositoryDTO(ownerId, postRepositoryDTO);
        FolderDTO folderDTO =  folderService.createFolder(repositoryCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(folderDTO);
    }

    @PatchMapping("/name")
    public ResponseEntity<ApiResponseBody> updateFolderName(@UserId Integer ownerId,
                                                            @RequestBody PatchRepositoryNameDTO patchRepositoryNameDTO){
        RepositoryNameUpdateParam repositoryNameUpdateParam = RepositoryNameUpdateParam.fromPatchRepositoryNameDTO(ownerId, patchRepositoryNameDTO);
        int updatedCount = folderService.updateFolderName(repositoryNameUpdateParam);
        ApiResponseBody apiResponseBody = new ApiResponseBody();
        apiResponseBody.setSuccess(true);
        apiResponseBody.setMessage("Success to rename folder!");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponseBody);
    }

}
