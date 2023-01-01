package kim.figure.site.main.post;

import kim.figure.site.common.category.Category;
import kim.figure.site.common.content.Content;
import kim.figure.site.common.tag.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * author         : walker
 * date           : 2022. 12. 22.
 * description    :
 */
public interface PostRepository extends MongoRepository<Content, Long> {

    List<Content> findByTagListInAndIdNot(List<Tag> tagList, Pageable pageRequestByTag, Long id);

    List<Content> findByCategoryListInAndTagListNotInAndIdNot(List<Category> categoryList, Pageable pageRequestByCategory, List<Tag> tagList, Long id);
}
