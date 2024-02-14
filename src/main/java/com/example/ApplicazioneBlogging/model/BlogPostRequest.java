package com.example.ApplicazioneBlogging.model;

import lombok.Data;

@Data
public class BlogPostRequest {
    private String categoria;
    private String titolo;
    private String contenuto;
    private int tempoDiLettura;

    private int idAutore;
}
