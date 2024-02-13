package com.example.ApplicazioneBlogging.service;





import com.example.ApplicazioneBlogging.model.Autore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class AutoreService {
    private List<Autore> autores = new ArrayList<>();


    public List<Autore> getBlogs() {
        return autores;
    }
    public Autore getBlogById(int id) throws NoSuchElementException {
        Optional<Autore> a = autores.stream().filter(blog -> blog.getId()==id).findAny();

        if (a.isPresent()){
            return a.get();
        }
        else {
            throw new NoSuchElementException("Autore non presente");
        }
    }

    public void saveAutore(Autore autore){
        autores.add(autore);
    }

    public Autore aggiornaBlog(int id, Autore autore) throws NoSuchElementException{
        Autore a = getBlogById(id);

        a.setNome(autore.getNome());
        a.setCognome(autore.getCognome());
        a.setEmail(autore.getEmail());
        a.setAvatar(autore.getAvatar());
        a.setDataDiNascita(autore.getDataDiNascita());

        return a;

    }

    public void deleteBlog(int id) throws NoSuchElementException{
        Autore autore = getBlogById(id);
        autores.remove(autore);
    }
}
