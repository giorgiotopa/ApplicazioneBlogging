package com.example.ApplicazioneBlogging.service;

import com.example.ApplicazioneBlogging.exception.NotFoundException;
import com.example.ApplicazioneBlogging.model.Autore;
import com.example.ApplicazioneBlogging.model.AutoreRequest;
import com.example.ApplicazioneBlogging.repository.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class AutoreService {
    @Autowired
    private AutoreRepository autoreRepository;

    public Page<Autore> getAll(Pageable pageable){
        return autoreRepository.findAll(pageable);
    }

    public Autore getAutoreById(int id) throws NotFoundException {
        return autoreRepository.findById(id).orElseThrow(()->new NotFoundException("Autore con id=" + id + " non trovato"));
    }

    public Autore saveAutore(AutoreRequest autoreRequest){
        Autore autore = new Autore(autoreRequest.getNome(),autoreRequest.getCognome(),autoreRequest.getEmail(),autoreRequest.getDataDiNascita());
        return autoreRepository.save(autore);
    }

    public Autore updateAutore(int id, AutoreRequest autoreRequest) throws NotFoundException {
        Autore a = getAutoreById(id);

        a.setNome(autoreRequest.getNome());
        a.setCognome(autoreRequest.getCognome());
        a.setEmail(autoreRequest.getEmail());
        a.setDataDiNascita(autoreRequest.getDataDiNascita());

        return autoreRepository.save(a);
    }

    public void deleteAutore(int id) throws NotFoundException {
        Autore autore = getAutoreById(id);
        autoreRepository.delete(autore);
    }
}
