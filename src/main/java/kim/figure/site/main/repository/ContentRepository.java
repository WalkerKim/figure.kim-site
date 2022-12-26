package kim.figure.site.main.repository;

import kim.figure.site.common.category.Category;
import kim.figure.site.common.content.Content;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * author         : walker
 * date           : 2022. 11. 21.
 * description    :
 */
public interface ContentRepository extends MongoRepository<Content, Long> {

    Long countByCategoryList(Category category);
}
