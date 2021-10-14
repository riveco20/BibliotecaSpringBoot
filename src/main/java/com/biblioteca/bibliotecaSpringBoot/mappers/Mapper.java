package com.biblioteca.bibliotecaSpringBoot.mappers;

import com.biblioteca.bibliotecaSpringBoot.dtos.BibliotecaDTO;
import com.biblioteca.bibliotecaSpringBoot.models.Biblioteca;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    private ModelMapper mapper;

    public  Mapper(){};
    public Mapper(ModelMapper mapper){
        this.mapper = mapper;
    }

    public BibliotecaDTO convertToDTO(Biblioteca biblioteca){
        BibliotecaDTO bibliotecaDTO = mapper.map(biblioteca, BibliotecaDTO.class);
        return bibliotecaDTO;
    }

    public Biblioteca convertToDocument(BibliotecaDTO bibliotecaDTO){
        Biblioteca biblioteca= mapper.map(bibliotecaDTO, Biblioteca.class);
        return biblioteca;
    }
}
