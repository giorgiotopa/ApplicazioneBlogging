package com.example.ApplicazioneBlogging.service;

import com.example.ApplicazioneBlogging.exception.NotFoundException;
import com.example.ApplicazioneBlogging.model.Autore;
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
    private AutoreRepository personaRepository;

    public Page<Autore> getAll(Pageable pageable){
        return personaRepository.findAll(pageable);
    }

    public Autore getAutoreById(int id) throws NotFoundException {
        return personaRepository.findById(id).orElseThrow(()->new NotFoundException("Persona con id=" + id + " non trovata"));
    }

    public Autore saveAutore(Autore autore){
        return personaRepository.save(autore);
    }

    public Autore updateAutore(int id, Autore autore) throws NotFoundException {
        Autore a = getAutoreById(id);

        a.setNome(autore.getNome());
        a.setCognome(autore.getCognome());
        a.setEmail(autore.getEmail());
        a.setDataDiNascita(autore.getDataDiNascita());

        return personaRepository.save(a);
    }

    public void deleteAutore(int id) throws NotFoundException {
        Autore autore = getAutoreById(id);
        personaRepository.delete(autore);
    }
}
