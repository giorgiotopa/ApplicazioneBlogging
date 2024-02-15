package com.example.ApplicazioneBlogging.service;

import com.example.ApplicazioneBlogging.exception.NotFoundException;
import com.example.ApplicazioneBlogging.model.Autore;
import com.example.ApplicazioneBlogging.model.AutoreRequest;
import com.example.ApplicazioneBlogging.repository.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class AutoreService {
    @Autowired
    private AutoreRepository autoreRepository;
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public Page<Autore> getAll(Pageable pageable){

        return autoreRepository.findAll(pageable);
    }

    public Autore getAutoreById(int id) throws NotFoundException {
        return autoreRepository.findById(id).orElseThrow(()->new NotFoundException("Autore con id=" + id + " non trovato"));
    }

    public Autore saveAutore(AutoreRequest autoreRequest){
        Autore autore = new Autore(autoreRequest.getNome(),autoreRequest.getCognome(),autoreRequest.getEmail(),autoreRequest.getDataDiNascita());
        sendMail(autore.getEmail());
        return autoreRepository.save(autore);
    }

    private void sendMail(String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registrazione Servizio Rest");
        message.setText("Registrazione al servizio rest avvenuta con successo");

        javaMailSender.send(message);
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

    public Autore uploadAvatar(int id, String url){
        Autore autore = getAutoreById(id);
        autore.setAvatar(url);
        return autoreRepository.save(autore);
    }
}
