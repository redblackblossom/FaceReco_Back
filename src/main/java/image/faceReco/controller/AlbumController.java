package image.faceReco.controller;

import image.faceReco.domain.DTO.AlbumDTO;
import image.faceReco.methodArgumentResolver.resolverInterface.UserId;
import image.faceReco.service.album.AlbumService;
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
}
