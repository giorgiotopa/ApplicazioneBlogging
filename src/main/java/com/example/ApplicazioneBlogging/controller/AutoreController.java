package com.example.ApplicazioneBlogging.controller;

import com.cloudinary.Cloudinary;
import com.example.ApplicazioneBlogging.exception.NotFoundException;
import com.example.ApplicazioneBlogging.model.Autore;
import com.example.ApplicazioneBlogging.model.AutoreRequest;
import com.example.ApplicazioneBlogging.model.CustomResponse;
import com.example.ApplicazioneBlogging.service.AutoreService;
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
public class AutoreController {
    @Autowired
    private AutoreService autoreService;
    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("/autori")
    public ResponseEntity<CustomResponse> getAll(Pageable pageable) {
        try {
            return CustomResponse.success(HttpStatus.OK.toString(), autoreService.getAll(pageable), HttpStatus.OK);
        } catch (Exception e) {
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/autori/{id}")
    public ResponseEntity<CustomResponse> getAutoreById(@PathVariable int id) {
        try {
            return CustomResponse.success(HttpStatus.OK.toString(), autoreService.getAutoreById(id), HttpStatus.OK);
        }
        catch (NotFoundException e) {
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/autori")
    public ResponseEntity<CustomResponse> saveAutore(@RequestBody @Validated AutoreRequest autoreRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return CustomResponse.error(bindingResult.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }
        try {
            return CustomResponse.success(HttpStatus.OK.toString(), autoreService.saveAutore(autoreRequest), HttpStatus.OK);
        }
        catch (Exception e) {
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/autori/{id}")
    public ResponseEntity<CustomResponse> updateAutore(@PathVariable int id, @RequestBody @Validated AutoreRequest autoreRequest,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return CustomResponse.error(bindingResult.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }
        try {
            return CustomResponse.success(HttpStatus.OK.toString(), autoreService.updateAutore(id, autoreRequest), HttpStatus.OK);
        }
        catch (NotFoundException e) {
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/autori/{id}")
    public ResponseEntity<CustomResponse> deleteAutore(@PathVariable int id) {
        try {
            autoreService.deleteAutore(id);
            return CustomResponse.emptyResponse("Autore con id=" + id + " cancellato", HttpStatus.OK);
        } catch (NotFoundException e) {
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return CustomResponse.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/autori/{id}/upload")
    public  Autore uploadAvatar(@PathVariable int id, @RequestParam("upload") MultipartFile file) throws IOException {
        return autoreService.uploadAvatar(id,
                (String)cloudinary.uploader().upload(file.getBytes(), new HashMap()).get("url"));


    }
}
