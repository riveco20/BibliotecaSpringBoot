package com.biblioteca.bibliotecaSpringBoot.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
@lombok.Getter
@lombok.Setter
public class BibliotecaDTO {
    private String id;
    private String nombreLibro;
    private String tematicaDelLibro;
    private String tipoDeLibro;
    private LocalDate fechaPrestamo;
    private Integer cantidadDisponible;
    private Integer cantidadPrestada;

    public BibliotecaDTO() {
    }
}

