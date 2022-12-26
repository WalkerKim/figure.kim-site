package kim.figure.site.main.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
