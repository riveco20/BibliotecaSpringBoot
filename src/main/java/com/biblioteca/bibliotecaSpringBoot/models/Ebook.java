package com.biblioteca.bibliotecaSpringBoot.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
@Document
public class Ebook {

    @Id
    private String id;
    private String nombreEbook;
    private String categoriaDeLibro;
    private String tipoDeEbook;
    private LocalDate fechaPrestadoEbook;
    private Integer cantidadDeEbookDisponible;
    private Integer cantidadDeEbookPrestada;




}
