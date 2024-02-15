package com.example.ApplicazioneBlogging.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
@Data
public class AutoreRequest {

    @NotNull(message = "nome obbligatorio")
    @NotEmpty(message = "il nome non può essere vuoto")
    private String nome;

    @NotNull(message = "cognome obbligatorio")
    @NotEmpty(message = "il cognome non può essere vuoto")
    private String cognome;

    @Email(message = "Inserire email valida")
    private String email;

    @NotNull(message = "data nascita obbligatorio")
    private LocalDate dataDiNascita;
}
