package com.example.ApplicazioneBlogging.controller;

import com.example.ApplicazioneBlogging.exception.NotFoundException;
import com.example.ApplicazioneBlogging.model.BlogPostRequest;
import com.example.ApplicazioneBlogging.model.CustomResponse;
import com.example.ApplicazioneBlogging.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;
    @GetMapping("/blog")
    public ResponseEntity<CustomResponse> getAll(Pageable pageable){
        try {
            return CustomResponse.success(HttpStatus.OK.toString(), blogService.getAll(pageable), HttpStatus.OK);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/blog/{id}")
    public ResponseEntity<CustomResponse> getAutoById(@PathVariable int id){
        try {
            return CustomResponse.success(HttpStatus.OK.toString(), blogService.getBlogById(id), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/blog")
    public ResponseEntity<CustomResponse> saveBlog(@RequestBody BlogPostRequest blogPostRequest){
        try{
            return CustomResponse.success(HttpStatus.OK.toString(), blogService.saveBlog(blogPostRequest), HttpStatus.OK);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/blog/{id}")
    public ResponseEntity<CustomResponse> updateBlog(@PathVariable int id, @RequestBody BlogPostRequest blogPostRequest){
        try {
            return CustomResponse.success(HttpStatus.OK.toString(), blogService.updateBlog(id, blogPostRequest), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/blog/{id}")
    public ResponseEntity<CustomResponse> deleteBlog(@PathVariable int id){
        try {
            blogService.deleteBlog(id);
            return CustomResponse.emptyResponse("Blog con id=" + id + " cancellato", HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
