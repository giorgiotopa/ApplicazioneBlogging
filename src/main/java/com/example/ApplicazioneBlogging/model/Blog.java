package com.example.ApplicazioneBlogging.model;

import lombok.Data;

@Data
public class Blog {

    private int id;
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;

}
