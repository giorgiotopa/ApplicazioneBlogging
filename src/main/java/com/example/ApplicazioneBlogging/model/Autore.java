package com.example.ApplicazioneBlogging.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Data
@Entity
public class Autore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nome;

    private String cognome;

    @Column(unique = true)
    private String email;

    private LocalDate dataDiNascita;

    private String avatar;

    @JsonIgnore
    @OneToMany(mappedBy = "autore")
    private List<Blog> posts;

    public Autore( String nome, String cognome, String email, LocalDate dataDiNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataDiNascita = dataDiNascita;
        this.avatar = "https://ui-avatars.com/api/?name=" + nome + cognome;
    }
    public Autore (){}
}
