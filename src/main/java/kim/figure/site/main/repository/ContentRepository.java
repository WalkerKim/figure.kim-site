package kim.figure.site.main.repository;

import kim.figure.site.common.category.Category;
import kim.figure.site.common.content.Content;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * author         : walker
 * date           : 2022. 11. 21.
 * description    :
 */
public interface ContentRepository extends MongoRepository<Content, Long> {

    Long countByCategoryListAndIsPublished(Category childCategory, boolean isPublished);

//    @Query(value = "{ 'status' : ?0 }", fields = "{ 'status' : 0, 'inStock' : 0 }")
    @Query( value="{ 'categoryList' :  { '$ref' : 'category', '$id' : ?0 } , 'isPublished' : ?1 }" , fields = "{ 'rawContent' : 0, 'renderedContent' : 0 }")
    List<Content> findByCategoryIdAndIsPublishedExcludeInnerContent(String categoryId, boolean isPublished);

}
