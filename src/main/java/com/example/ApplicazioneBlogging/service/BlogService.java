package com.example.ApplicazioneBlogging.service;

import com.example.ApplicazioneBlogging.model.Blog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BlogService {
    private List<Blog> blogs = new ArrayList<>();


    public List<Blog> getBlogs() {
        return blogs;
    }
    public Blog getBlogById(int id) throws NoSuchElementException{
        Optional<Blog> b = blogs.stream().filter(blog -> blog.getId()==id).findAny();

        if (b.isPresent()){
            return b.get();
        }
        else {
            throw new NoSuchElementException("Blog non presente");
        }
    }

    public void saveBlog(Blog blog){
        blogs.add(blog);
    }

    public Blog aggiornaBlog(int id, Blog blog) throws NoSuchElementException{
        Blog b = getBlogById(id);

        b.setTitolo(blog.getTitolo());
        b.setCategoria(blog.getCategoria());
        b.setContenuto(blog.getContenuto());
        b.setCover(blog.getCover());
        b.setTempoDiLettura(blog.getTempoDiLettura());

        return b;

    }

    public void deleteBlog(int id) throws NoSuchElementException{
        Blog blog = getBlogById(id);
        blogs.remove(blog);
    }
}
