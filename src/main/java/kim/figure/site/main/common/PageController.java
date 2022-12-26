package kim.figure.site.main.common;

import kim.figure.site.main.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * author         : walker
 * date           : 2022. 11. 15.
 * description    :
 */
@Controller
public class PageController {


    @Autowired
    CategoryService categoryService;



    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("categoryList", categoryService.getCategoryList());
        return "pages/index";
    }
}
