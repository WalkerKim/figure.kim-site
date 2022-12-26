package kim.figure.site.main.category;

import kim.figure.site.common.category.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * author         : walker
 * date           : 2022. 12. 22.
 * description    :
 */
@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    CategoryDto.Get categoryToGet(Category category);

    List<CategoryDto.Get> categoryListToGetList(List<Category> categoryList);

}
