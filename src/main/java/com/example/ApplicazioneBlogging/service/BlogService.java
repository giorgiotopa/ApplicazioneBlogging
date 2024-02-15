package com.example.ApplicazioneBlogging.service;

import com.example.ApplicazioneBlogging.exception.NotFoundException;
import com.example.ApplicazioneBlogging.model.Autore;
import com.example.ApplicazioneBlogging.model.Blog;
import com.example.ApplicazioneBlogging.model.BlogPostRequest;
import com.example.ApplicazioneBlogging.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Blog saveBlog(BlogPostRequest blogPostRequest) throws NotFoundException {

        Autore autore = autoreService.getAutoreById(blogPostRequest.getIdAutore());

        Blog blog = new Blog(blogPostRequest.getContenuto(),blogPostRequest.getTitolo(),blogPostRequest.getCategoria(),blogPostRequest.getTempoDiLettura(),autore);


        return blogRepository.save(blog);

    }

    public Blog updateBlog(int id, BlogPostRequest blogPostRequest) throws NotFoundException {
        Blog blog = getBlogById(id);

        Autore autore = autoreService.getAutoreById(blogPostRequest.getIdAutore());

        blog.setTitolo(blogPostRequest.getTitolo());
        blog.setCategoria(blogPostRequest.getCategoria());
        blog.setContenuto(blogPostRequest.getContenuto());
        blog.setTempoDiLettura(blogPostRequest.getTempoDiLettura());
        blog.setAutore(autore);

        return blogRepository.save(blog);

    }

    public void deleteBlog(int id) throws NotFoundException {
        Blog blog = getBlogById(id);

        blogRepository.delete(blog);
    }
    public Blog uploadCover(int id, String url) throws NotFoundException{
        Blog blog = getBlogById(id);

        blog.setCover(url);
        return blogRepository.save(blog);
    }
}
