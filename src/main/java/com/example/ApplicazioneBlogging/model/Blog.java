package com.example.ApplicazioneBlogging.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String contenuto;
    private String titolo;
    private String cover;
    private String categoria;
    private int tempoDiLettura;

    @ManyToOne
    @JoinColumn(name = "autore_id")
    private Autore autore;

    public Blog(String contenuto, String titolo, String categoria, int tempoDiLettura,Autore autore) {
        this.contenuto = contenuto;
        this.titolo = titolo;
        this.categoria = categoria;
        this.tempoDiLettura = tempoDiLettura;
        this.autore = autore;
    }
    public Blog (){}
}
