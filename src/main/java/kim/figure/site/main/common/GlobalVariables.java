package kim.figure.site.main.common;

import kim.figure.site.common.category.Category;
import kim.figure.site.main.category.CategoryDto;
import kim.figure.site.main.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * author         : walker
 * date           : 2023. 01. 13.
 * description    :
 */
@Component
public class GlobalVariables implements ApplicationListener<ApplicationReadyEvent> {

    public static List<CategoryDto.Get> CATEGORY_LIST;
    @Autowired
    CategoryService categoryService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        categoryService.updateGlobalCategoryList();
    }

}
