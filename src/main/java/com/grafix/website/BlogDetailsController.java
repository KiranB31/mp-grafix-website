package com.grafix.website;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BlogDetailsController {

    private final BlogService blogService;

    public BlogDetailsController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable Long id, Model model) {
        BlogPost post = blogService.getPostById(id);

        if (post == null) {
            return "redirect:/blog";
        }

        model.addAttribute("post", post);
        return "blog-details";
    }
}
