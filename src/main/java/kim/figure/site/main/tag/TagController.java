package kim.figure.site.main.tag;

import kim.figure.site.common.content.Content;
import kim.figure.site.common.tag.Tag;
import kim.figure.site.main.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * author         : walker
 * date           : 2023. 01. 05.
 * description    :
 */
@Controller
public class TagController {
    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/tag")
    public String tagList(Model model){
        model.addAttribute("tagList", tagService.aggregateTagPostCount());
        model.addAttribute("categoryList", categoryService.getCategoryListFilteringNoContent());
        return "tag/tag";
    }


    @GetMapping("/tag/{id}")
    public String tagList(Model model, @PathVariable String id){
        Tag targetTag = tagService.getTagById(id);
        List<Content> postList = categoryService.getPostByTag(targetTag);
        model.addAttribute("tag", targetTag.getId());
        model.addAttribute("postCount", postList.size());
        model.addAttribute("postList", postList);
        model.addAttribute("categoryList", categoryService.getCategoryListFilteringNoContent());
        return "tag/tagDetail";
    }
}
