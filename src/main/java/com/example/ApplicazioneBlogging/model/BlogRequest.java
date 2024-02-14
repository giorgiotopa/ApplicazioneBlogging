package com.example.ApplicazioneBlogging.model;

import lombok.Data;

@Data
public class BlogRequest {
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;


    private int idAutore;
}
