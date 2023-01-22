package kim.figure.site.main.common;

import kim.figure.site.main.category.CategoryService;
import kim.figure.site.main.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Period;

/**
 * author         : walker
 * date           : 2022. 11. 15.
 * description    :
 */
@Controller
public class PageController {


    @Autowired
    CategoryService categoryService;

    @Autowired
    PostService postService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("recommendPost", postService.getRecommendPost());
        model.addAttribute("recommendPostList", postService.getRecommendPostList());
        model.addAttribute("recentPostList", postService.getMostRecentPostList());
        return "index";
    }

}
