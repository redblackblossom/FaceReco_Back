package image.faceReco.controller;

import image.faceReco.domain.API.ApiResponseBody;
import image.faceReco.domain.DTO.RepositoryIdListDTO;
import image.faceReco.domain.DTO.RepositoryIdListOwnerIdDTO;
import image.faceReco.domain.DTO.RepositoryIdListParentIdDTO;
import image.faceReco.domain.DTO.RepositoryIdListParentIdOwnerIdDTO;
import image.faceReco.methodArgumentResolver.resolverInterface.UserId;
import image.faceReco.service.repository.RepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/directories")
@Slf4j
@RequiredArgsConstructor
public class RepositoryController {
    private final RepositoryService repositoryService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> findFolderAlbumByOwnerId(@UserId Integer ownerId){
        Map<String, Object> folderAlbumMap = repositoryService.findAllRepositoryByOwnerId(ownerId);
        return ResponseEntity.ok(folderAlbumMap);
    }


    @DeleteMapping
    public ResponseEntity<ApiResponseBody> deleteRepository(@UserId Integer ownerId, @RequestBody RepositoryIdListDTO repositoryIdListDTO){
        RepositoryIdListOwnerIdDTO repositoryIdListOwnerIdDTO
                = RepositoryIdListOwnerIdDTO.fromRepositoryIdListDTO(ownerId, repositoryIdListDTO);
        repositoryService.deleteRepositoryByIdList(repositoryIdListOwnerIdDTO);
        ApiResponseBody apiResponseBody = new ApiResponseBody(true, "Success to delete repository");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponseBody);
    }

    @PatchMapping("/parentid")
    public ResponseEntity<ApiResponseBody> updateRepositoryParentId(@UserId Integer ownerId,
                                                                    @RequestBody RepositoryIdListParentIdDTO repositoryIdListParentIdDTO){
        RepositoryIdListParentIdOwnerIdDTO repositoryIdListParentIdOwnerIdDTO =
                RepositoryIdListParentIdOwnerIdDTO.fromRepositoryIdListParentIdDTO(ownerId, repositoryIdListParentIdDTO);

        repositoryService.updateParentIdByIdList(repositoryIdListParentIdOwnerIdDTO);
        ApiResponseBody apiResponseBody = new ApiResponseBody(true, "Success to update repository");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponseBody);
    }

}
