package kim.figure.site.main.post;

import kim.figure.site.main.category.CategoryService;
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
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/post/{id}")
    public String post(Model model, @PathVariable("id") Long id){
        PostDto.Get post = postService.getPost(id);
        model.addAttribute("categoryList", categoryService.getCategoryList());
        model.addAttribute("post", post);
        model.addAttribute("recommendPostList", postService.getRecommendPostList(id));
        return "post/post";
    }
}
