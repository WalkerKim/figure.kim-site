package kim.figure.site.main.post;

import kim.figure.site.common.category.Category;
import kim.figure.site.common.content.Content;
import kim.figure.site.common.tag.Tag;
import kim.figure.site.main.tag.TagDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * author         : walker
 * date           : 2022. 12. 22.
 * description    :
 */
public interface PostRepository extends MongoRepository<Content, Long> {

    List<Content> findByTagListInAndIdNot(List<Tag> tagList, Pageable pageRequestByTag, Long id);

    List<Content> findByCategoryListInAndTagListNotInAndIdNot(List<Category> categoryList, Pageable pageRequestByCategory, List<Tag> tagList, Long id);


    @Aggregation(pipeline = {"{$project: {tagList: 1, isPublished: 1}}","{$match: {'isPublished': true}}","{'$unwind': {path: '$tagList'}}", "{'$group': {_id: '$tagList', count: {$sum: 1}}}", "{$sort: {'count':-1}}","{'$project': {_id: 0,'tag': '$_id.$id', count: 1}}"})
    List<TagDto.Get> groupByTagAndSortByCountDecrease();

    List<Content> findByIsPublished(boolean b);


    Long countByCategoryListAndIsPublished(Category childCategory, boolean isPublished);

    //    @Query(value = "{ 'status' : ?0 }", fields = "{ 'status' : 0, 'inStock' : 0 }")
    @Query( value="{ 'categoryList' :  { '$ref' : 'category', '$id' : ?0 } , 'isPublished' : ?1 }" , fields = "{ 'rawContent' : 0, 'renderedContent' : 0 }")
    List<Content> findByCategoryIdAndIsPublishedExcludeInnerContent(String categoryId, boolean isPublished);

    @Query( value="{ 'tagList' :  { '$ref' : 'tag', '$id' : ?0 } , 'isPublished' : ?1 }" , fields = "{ 'rawContent' : 0, 'renderedContent' : 0 }")
    List<Content> findByTagIdAndIsPublishedExcludeInnerContent(String tagId, boolean isPublished);

    Content findTopByOrderByRecommendStatDesc();

    List<Content> findTop8ByOrderByRecommendStatDesc();

    List<Content> findTop8ByOrderByPublishedAtDesc();
}
