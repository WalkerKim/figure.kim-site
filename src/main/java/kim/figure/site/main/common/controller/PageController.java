package kim.figure.site.main.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * author         : walker
 * date           : 2022. 11. 15.
 * description    :
 */
@Controller
public class PageController {

    @GetMapping("/")
    public String index(){
        return "pages/index";
    }
}
