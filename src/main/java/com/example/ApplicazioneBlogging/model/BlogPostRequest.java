package com.example.ApplicazioneBlogging.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BlogPostRequest {

    @NotNull(message = "Contenuto obbligatorio")
    @NotEmpty(message = "Il campo contenuto non può essere vuoto")
    private String contenuto;

    @NotNull(message = "Titolo obbligatorio")
    @NotEmpty(message = "Il campo titolo non può essere vuoto")
    private String titolo;

    @NotNull(message = "Categoria obbligatorio")
    @NotEmpty(message = "Il campo categoria non può essere vuoto")
    private String categoria;

    private int tempoDiLettura;

    @NotNull(message = "Autore obbligatorio")
    private Integer idAutore;
}
