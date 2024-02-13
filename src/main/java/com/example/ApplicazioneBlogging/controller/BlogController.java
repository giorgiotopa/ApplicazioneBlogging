package com.example.ApplicazioneBlogging.controller;

import com.example.ApplicazioneBlogging.model.Blog;
import com.example.ApplicazioneBlogging.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/api")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/blog")
    public List<Blog> getAll(){
        return blogService.getBlogs();
    }
    @GetMapping("/blog/id")
    public Blog getBlogByIdQueryParams(@RequestParam int id){
        return blogService.getBlogById(id);
    }
}
