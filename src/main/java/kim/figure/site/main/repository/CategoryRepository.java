package kim.figure.site.main.repository;

import kim.figure.site.common.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * author         : walker
 * date           : 2022. 11. 21.
 * description    :
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
