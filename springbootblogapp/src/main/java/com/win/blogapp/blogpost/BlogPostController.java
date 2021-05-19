package com.win.blogapp.blogpost;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogPostController {

    @Autowired
    private BlogPostRepo repo; 
    
    @GetMapping("/")
    public String index(BlogPost post, Model model) {
        model.addAttribute("posts", repo.findAll());
        return "blogpost/index";
    }

    @PostMapping("/blogpost/result")
    public String addNew(BlogPost post, Model model) {
        repo.save(post);
        model.addAttribute("post", post);

        return "blogpost/result";
    }

    @GetMapping("/blogpost/addnew")
    public String addNewBlogPost(BlogPost post, Model model){
        return "blogpost/addnew";
    }

    @GetMapping("/blogpost/edit/{id}")
    public String editBlogPost(@PathVariable("id") long id, Model model) {
        BlogPost post = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post id: " + id));

        model.addAttribute("post", post);
        return "blogpost/edit";
    }

    @PostMapping("/blogpost/update/{id}")
    public String updatePost(@PathVariable("id") long id, @Valid BlogPost post, BindingResult result, Model model) {
        if(result.hasErrors()){
            post.setId(id);
            return "blogpost/edit";
        }

        repo.save(post);
        model.addAttribute("post", post);
        return "blogpost/result";
    }

    @GetMapping("/blogpost/delete/{id}")
    public String deletePost(@PathVariable("id") long id, Model model) {
        repo.deleteById(id);
        return "blogpost/delete";
    }
}