package kim.figure.site.main.category;

import kim.figure.site.common.category.Category;
import kim.figure.site.common.content.Content;
import kim.figure.site.common.tag.Tag;
import kim.figure.site.main.common.exception.BadRequestException;
import kim.figure.site.main.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author         : walker
 * date           : 2022. 12. 21.
 * description    :
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PostRepository postRepository;

    public List<CategoryDto.Get> getCategoryListFilteringNoContent(){
        return categoryRepository.findByDepth(0).parallelStream().map(category -> {
            List<CategoryDto.Get> childCategoryList = category.getChildCategoryList().parallelStream().map(childCategory -> {
                CategoryDto.Get categoryDto = CategoryMapper.INSTANCE.categoryToGet(childCategory);
                categoryDto.setContentCount(getPostCountByCategoryAndIsPublished(childCategory, true));
                return categoryDto;
            }).filter(content->content.getContentCount()!=0).toList();
            CategoryDto.Get categoryDto = CategoryMapper.INSTANCE.categoryToGet(category);
            categoryDto.setContentCount(getPostCountByCategoryAndIsPublished(category, true));
            categoryDto.setChildCategoryList(childCategoryList);
            return categoryDto;
        }).filter(i->i.getContentCount()!=0).toList();
    }


    public Long getPostCountByCategoryAndIsPublished(Category category, Boolean isPublished){
        return postRepository.countByCategoryListAndIsPublished(category, isPublished);
    }

    public List<Content> getPostByCategory(Category category){
        List<Content> contentList = postRepository.findByCategoryIdAndIsPublishedExcludeInnerContent(category.getId(), true);
        return contentList;
    }

    public Category getCategoryById(String id) {
        return categoryRepository.findById(id).orElseThrow(()->new BadRequestException("No category [" + id + "]"));
    }

    public List<Content> getPostByTag(Tag targetTag) {
        return postRepository.findByTagIdAndIsPublishedExcludeInnerContent(targetTag.getId(), true);
    }
}
