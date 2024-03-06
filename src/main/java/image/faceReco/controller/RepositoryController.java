package image.faceReco.controller;

import image.faceReco.domain.DTO.AlbumDTO;
import image.faceReco.domain.DTO.FolderDTO;
import image.faceReco.methodArgumentResolver.resolverInterface.UserId;
import image.faceReco.service.album.AlbumService;
import image.faceReco.service.folder.FolderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/directories")
@Slf4j
@RequiredArgsConstructor
public class RepositoryController {
    private final FolderService folderService;
    private final AlbumService albumService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> findFolderAlbumByOwnerId(@UserId Integer ownerId){
        List<FolderDTO> folderDTOList = folderService.findAllFolderByOwnerId(ownerId);
        List<AlbumDTO> albumDTOList = albumService.findAlbumByOwnerId(ownerId);
        Map<String,Object> folderAlbumMap = new HashMap<>();
        folderAlbumMap.put("folders", folderDTOList);
        folderAlbumMap.put("albums", albumDTOList);

        return ResponseEntity.ok(folderAlbumMap);
    }

}
