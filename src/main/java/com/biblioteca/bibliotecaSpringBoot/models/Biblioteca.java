package com.biblioteca.bibliotecaSpringBoot.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Document
@lombok.Getter
@lombok.Setter
public class Biblioteca {

    @Id
    private String id;
    private String nombreLibro;
    private String tematicaDeLibro;
    private String tipoDeLibro;
    private LocalDate fechaPrestamo;
    private Integer cantidadDisponible;
    private Integer cantidadPrestada;


        public Biblioteca() {
        }

    }
