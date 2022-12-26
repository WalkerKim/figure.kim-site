package kim.figure.site.main.post;

import kim.figure.site.common.content.Content;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * author         : walker
 * date           : 2022. 12. 22.
 * description    :
 */
public interface PostRepository extends MongoRepository<Content, Long> {
}
