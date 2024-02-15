package com.example.ApplicazioneBlogging.controller;

import com.cloudinary.Cloudinary;
import com.example.ApplicazioneBlogging.exception.NotFoundException;
import com.example.ApplicazioneBlogging.model.Blog;
import com.example.ApplicazioneBlogging.model.BlogPostRequest;
import com.example.ApplicazioneBlogging.model.CustomResponse;
import com.example.ApplicazioneBlogging.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private Cloudinary cloudinary;

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
    public ResponseEntity<CustomResponse> saveBlog(@RequestBody @Validated BlogPostRequest blogPostRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return CustomResponse.error(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).toList().toString(), HttpStatus.BAD_REQUEST);
        }
        try{
            return CustomResponse.success(HttpStatus.OK.toString(), blogService.saveBlog(blogPostRequest), HttpStatus.OK);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/blog/{id}")
    public ResponseEntity<CustomResponse> updateBlog(@PathVariable int id, @RequestBody @Validated BlogPostRequest blogPostRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return CustomResponse.error(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).toList().toString(), HttpStatus.BAD_REQUEST);
        }
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

    @PatchMapping("/blog/{id}/upload")
    public ResponseEntity<CustomResponse> uploadCover(@PathVariable int id,@RequestParam("upload") MultipartFile file) {
        try {
            Blog blog = blogService.uploadCover(id, (String) cloudinary.uploader().upload(file.getBytes(), new HashMap()).get("url"));
            return CustomResponse.success(HttpStatus.OK.toString(), blog, HttpStatus.OK);
        } catch (IOException e) {
            return CustomResponse.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
