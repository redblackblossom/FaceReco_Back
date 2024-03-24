package image.faceReco.repository.es;

import image.faceReco.domain.document.Image;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageElasticSearchRepository extends ElasticsearchRepository<Image,String> {
}
