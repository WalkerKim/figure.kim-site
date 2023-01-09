package kim.figure.site.main.category;

import kim.figure.site.common.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * author         : walker
 * date           : 2022. 11. 21.
 * description    :
 */
public interface CategoryRepository extends MongoRepository<Category, String> {
    List<Category> findByDepth(int i);
}
