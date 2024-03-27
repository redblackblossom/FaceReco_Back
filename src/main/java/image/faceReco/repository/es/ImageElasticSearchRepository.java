package image.faceReco.repository.es;

import image.faceReco.domain.document.Image;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageElasticSearchRepository extends ElasticsearchRepository<Image,String> {
    List<Image> findByOwnerIdAndOwnerAlbumId(int ownerId, int AlbumId);
    List<Image> findByOwnerId(int ownerId);
}
