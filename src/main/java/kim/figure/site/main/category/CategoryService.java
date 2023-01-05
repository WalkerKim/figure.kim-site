package kim.figure.site.main.category;

import kim.figure.site.common.category.Category;
import kim.figure.site.common.content.Content;
import kim.figure.site.main.common.exception.BadRequestException;
import kim.figure.site.main.repository.CategoryRepository;
import kim.figure.site.main.repository.ContentRepository;
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
    private ContentRepository contentRepository;

    public List<CategoryDto.Get> getCategoryList() {
        return categoryRepository.findByDepth(0).parallelStream().map(category -> {
            List<CategoryDto.Get> childCategoryList = category.getChildCategoryList().parallelStream().map(childCategory -> {
                CategoryDto.Get categoryDto = CategoryMapper.INSTANCE.categoryToGet(childCategory);
                categoryDto.setContentCount(getPostCountByCategoryAndIsPublished(childCategory, true));
                return categoryDto;
            }).toList();
            CategoryDto.Get categoryDto = CategoryMapper.INSTANCE.categoryToGet(category);
            categoryDto.setContentCount(getPostCountByCategoryAndIsPublished(category, true));
            categoryDto.setChildCategoryList(childCategoryList);
            return categoryDto;
        }).toList();

//        return categoryRepository.findByDepth(0);
    }

    public Long getPostCountByCategoryAndIsPublished(Category category, Boolean isPublished){
        return contentRepository.countByCategoryListAndIsPublished(category, isPublished);
    }

    public List<Content> getPostByCategory(Category category){
        List<Content> contentList = contentRepository.findByCategoryIdAndIsPublishedExcludeInnerContent(category.getId(), true);
        return contentList;
    }

    public Category getCategoryById(String id) {
        return categoryRepository.findById(id).orElseThrow(()->new BadRequestException("No category [" + id + "]"));
    }
}
