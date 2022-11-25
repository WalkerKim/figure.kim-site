package kim.figure.site.main.repository;

import kim.figure.site.common.content.Content;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * author         : walker
 * date           : 2022. 11. 21.
 * description    :
 */
public interface ContentRepository extends JpaRepository<Content, Long> {
}
