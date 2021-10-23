package com.biblioteca.bibliotecaSpringBoot.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
@Data
public class EbookDTO {


    private String id;
    private String nombreEbook;
    private String categoriaDeLibro;
    private String tipoDeEbook;
    private LocalDate fechaPrestadoEbook;
    private Integer cantidadDeEbookDisponible;
    private Integer cantidadDeEbookPrestada;


}
