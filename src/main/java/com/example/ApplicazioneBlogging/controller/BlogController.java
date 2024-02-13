package com.example.ApplicazioneBlogging.controller;

import com.example.ApplicazioneBlogging.model.Blog;
import com.example.ApplicazioneBlogging.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
    @GetMapping("/blog/{id}")
    public ResponseEntity<Blog> getBlogByIdPathVariable(@PathVariable int id){
        try {
            Blog b = blogService.getBlogById(id);
            return new ResponseEntity<Blog>(b, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/blog")
    public void saveBlog(@RequestBody Blog blog){
        blogService.saveBlog(blog);
    }
    @PutMapping("/blog/{id}")
    public Blog updateBlog(@PathVariable int id, @RequestBody Blog blog){
        return blogService.aggiornaBlog(id, blog);
    }
    @DeleteMapping("/blog/{id}")
    public void deleteBlog(@PathVariable int id){
        blogService.deleteBlog(id);
    }

}
