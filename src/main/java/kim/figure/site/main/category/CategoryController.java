package kim.figure.site.main.category;

import kim.figure.site.common.category.Category;
import kim.figure.site.common.content.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * author         : walker
 * date           : 2022. 12. 22.
 * description    :
 */
@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public String categoryList(Model model){

        return "category/category";
    }
    @GetMapping("/category/{id}")
    public String categoryList(Model model, @PathVariable String id){
        Category targetCategory = categoryService.getCategoryById(id);
        List<CategoryDto.Get> allCategory = categoryService.getCategoryListFilteringNoContent();
        List<Content> postList = categoryService.getPostByCategory(targetCategory);
        model.addAttribute("category", targetCategory);
        model.addAttribute("postCount", postList.size());
        model.addAttribute("parentCategory", allCategory.stream().filter(category -> category.getChildCategoryList().contains(targetCategory)).findAny().orElse(null));
        model.addAttribute("postList", postList);
        model.addAttribute("categoryList", allCategory);
        return "category/categoryDetail";
    }

}
