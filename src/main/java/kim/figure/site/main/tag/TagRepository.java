package kim.figure.site.main.tag;

import kim.figure.site.common.tag.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * author         : walker
 * date           : 2022. 11. 21.
 * description    :
 */
public interface TagRepository extends MongoRepository<Tag, String> {



}
