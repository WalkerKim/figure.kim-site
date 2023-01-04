package kim.figure.site.main.category;

import kim.figure.site.common.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        model.addAttribute("categoryList", categoryService.getCategoryList());
        return "category/category";
    }
    @GetMapping("/category/{id}")
    public String categoryList(Model model, @PathVariable String id){
        Category targetCategory = categoryService.getCategoryById(id);
        model.addAttribute("category", targetCategory);
        model.addAttribute("parentCategory", categoryService.getCategoryList().stream().filter(category -> category.getChildCategoryList().contains(targetCategory)).findAny().orElse(null));
        model.addAttribute("postList", categoryService.getPostByCategory(targetCategory));
        model.addAttribute("categoryList", categoryService.getCategoryList());
        return "category/categoryDetail";
    }

}
