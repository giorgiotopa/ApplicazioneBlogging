package com.example.ApplicazioneBlogging.service;

import com.example.ApplicazioneBlogging.exception.NotFoundException;
import com.example.ApplicazioneBlogging.model.Autore;
import com.example.ApplicazioneBlogging.model.Blog;
import com.example.ApplicazioneBlogging.model.BlogRequest;
import com.example.ApplicazioneBlogging.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private AutoreService autoreService;

    public Page<Blog> getAll(Pageable pageable){
        return blogRepository.findAll(pageable);
    }

    public Blog getBlogById(int id) throws NotFoundException {
        return blogRepository.findById(id).orElseThrow(() -> new NotFoundException("Blog con id=" + id + " non trovato"));
    }

    public Blog saveBlog(BlogRequest blogRequest) throws NotFoundException {

        Autore autore = autoreService.getAutoreById(blogRequest.getIdAutore());

        Blog blog = new Blog();
        blog.setTitolo(blogRequest.getTitolo());
        blog.setCategoria(blogRequest.getCategoria());
        blog.setContenuto(blogRequest.getContenuto());
        blog.setCover(blogRequest.getCover());
        blog.setTempoDiLettura(blogRequest.getTempoDiLettura());
        blog.setAutore(autore);

        return blogRepository.save(blog);

    }

    public Blog updateBlog(int id, BlogRequest blogRequest) throws NotFoundException {
        Blog blog = getBlogById(id);

        Autore autore = autoreService.getAutoreById(blogRequest.getIdAutore());

        blog.setTitolo(blogRequest.getTitolo());
        blog.setCategoria(blogRequest.getCategoria());
        blog.setContenuto(blogRequest.getContenuto());
        blog.setCover(blogRequest.getCover());
        blog.setTempoDiLettura(blogRequest.getTempoDiLettura());
        blog.setAutore(autore);

        return blogRepository.save(blog);

    }

    public void deleteBlog(int id) throws NotFoundException {
        Blog blog = getBlogById(id);

        blogRepository.delete(blog);
    }
}
