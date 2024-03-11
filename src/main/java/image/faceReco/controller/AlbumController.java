package image.faceReco.controller;

import image.faceReco.domain.API.ApiResponseBody;
import image.faceReco.domain.DTO.PatchRepositoryNameDTO;
import image.faceReco.domain.DTO.PostRepositoryDTO;
import image.faceReco.domain.DTO.album.AlbumDTO;
import image.faceReco.domain.DTO.album.DeleteAlbumDTO;
import image.faceReco.domain.DTO.RepositoryCreateDTO;
import image.faceReco.domain.updateParam.RepositoryNameUpdateParam;
import image.faceReco.domain.updateParam.album.AlbumDeleteByAlbumIdListParam;
import image.faceReco.methodArgumentResolver.resolverInterface.UserId;
import image.faceReco.service.album.AlbumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/albums")
@Slf4j
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;
    @GetMapping
    public ResponseEntity<Map<String, List<AlbumDTO>>> findAlbumByOwnerId(@UserId Integer ownerId){
        List<AlbumDTO> albumDTOList = albumService.findAlbumByOwnerId(ownerId);
        Map<String, List<AlbumDTO>> albumMap = new HashMap<>();
        albumMap.put("albums", albumDTOList);
        return ResponseEntity.ok(albumMap);
    }

    @PostMapping
    public ResponseEntity<ApiResponseBody> createNewAlbum(@UserId Integer ownerId, @RequestBody PostRepositoryDTO postRepositoryDTO){
        RepositoryCreateDTO repositoryCreateDTO = RepositoryCreateDTO.fromPostRepositoryDTO(ownerId, postRepositoryDTO);
        albumService.createAlbum(repositoryCreateDTO);
        ApiResponseBody apiResponseBody = new ApiResponseBody(true, "Success to create album!");
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponseBody);
    }


    @PatchMapping("/name")
    public ResponseEntity<ApiResponseBody> updateAlbumName(@UserId Integer ownerId,
                                                           @RequestBody PatchRepositoryNameDTO patchRepositoryNameDTO){
        RepositoryNameUpdateParam repositoryNameUpdateParam = RepositoryNameUpdateParam.fromPatchRepositoryNameDTO(ownerId,
                                                                patchRepositoryNameDTO);
        albumService.updateAlbumNameByAlumId(repositoryNameUpdateParam);
        ApiResponseBody apiResponseBody = new ApiResponseBody(true, "Success to rename album");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponseBody);
    }
}
