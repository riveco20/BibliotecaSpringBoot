package com.biblioteca.bibliotecaSpringBoot.services;


import com.biblioteca.bibliotecaSpringBoot.dtos.BibliotecaDTO;
import com.biblioteca.bibliotecaSpringBoot.mappers.Mapper;
import com.biblioteca.bibliotecaSpringBoot.models.Biblioteca;
import com.biblioteca.bibliotecaSpringBoot.repositories.RepositorieBiblioteca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ServiceBiblioteca {

    private RepositorieBiblioteca repository;
    private Mapper mapper;

    @Autowired
    public ServiceBiblioteca(RepositorieBiblioteca repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<BibliotecaDTO> obtenerElementos() {
        List<BibliotecaDTO> bibliotecaDTOS = new ArrayList<>();
        repository.findAll().forEach(biblioteca -> bibliotecaDTOS.add(mapper.convertToDTO(biblioteca)));
        return bibliotecaDTOS;
    }

    public BibliotecaDTO guardar(BibliotecaDTO bibliotecaDTO){
        if (bibliotecaDTO.getNombreLibro().isEmpty()){
            throw new IllegalArgumentException("Debe Ingresar un nombre");
        }
        Biblioteca biblioteca = mapper.convertToDocument(bibliotecaDTO);
        return mapper.convertToDTO(repository.save(biblioteca));
    }

    public BibliotecaDTO obtenerPorId(String id){
        Optional<Biblioteca> bibliotecas = repository.findById(id);
        if (bibliotecas.isEmpty()){
            throw new NoSuchElementException("El id ingresado no existe ");
        }
        return mapper.convertToDTO(bibliotecas.get());
    }

    public void eliminar(String id){
        repository.delete(mapper.convertToDocument(obtenerPorId(id)));
    }

    public  BibliotecaDTO actualizar(BibliotecaDTO bibliotecaDTO){
        Biblioteca biblioteca = mapper.convertToDocument(bibliotecaDTO);
        obtenerPorId(biblioteca.getId());
        return mapper.convertToDTO(repository.save(biblioteca));
    }

    public String comprobarDisponibilidad(String id){
        BibliotecaDTO bibliotecaDTO = obtenerPorId(id);
        if (disponible(bibliotecaDTO)){
            return  "El libro esta disponible y quedan: " + (bibliotecaDTO.getCantidadDisponible()-bibliotecaDTO.getCantidadPrestada() + " unidades disponibles");
        }
        return  "El libro no esta disponible o esta prestado " + bibliotecaDTO.getFechaPrestamo();
    }

    private  boolean disponible(BibliotecaDTO bibliotecaDTO){
        return bibliotecaDTO.getCantidadDisponible() > bibliotecaDTO.getCantidadPrestada();
    }

    public String prestar(String id){
        BibliotecaDTO bibliotecaDTO = obtenerPorId(id);
        if (disponible(bibliotecaDTO)){
            bibliotecaDTO.setCantidadPrestada(bibliotecaDTO.getCantidadPrestada()+1);
            bibliotecaDTO.setFechaPrestamo(LocalDate.now());
            actualizar(bibliotecaDTO);
            return "El libro se encuentra prestado";
        }
        return "No hay unidades del libro consultado disponible";
    }

    public List<BibliotecaDTO> recomendarPorTema(String tema){
        List<BibliotecaDTO> bibliotecaDTOS = new ArrayList<>();
        repository.findBytematicaDeLibro(tema).forEach(recurso -> bibliotecaDTOS.add(mapper.convertToDTO(recurso)));
        return bibliotecaDTOS;
    }

    public List<BibliotecaDTO> recomendarPorTipo(String tipo){
        List<BibliotecaDTO> bibliotecaDTOS = new ArrayList<>();
        repository.findBytipoDeLibro(tipo).forEach(recurso -> bibliotecaDTOS.add(mapper.convertToDTO(recurso)));
        return bibliotecaDTOS;
    }

    public List<BibliotecaDTO> recomendarPorTemaYTipo(String tema, String tipo){
        List<BibliotecaDTO> bibliotecaDTOS = new ArrayList<>();
        List<BibliotecaDTO> bibliotecaDTOS1 = new ArrayList<>();
        bibliotecaDTOS1.addAll(recomendarPorTipo(tipo));
        bibliotecaDTOS1.addAll(recomendarPorTema(tema));
        bibliotecaDTOS1.stream().distinct().forEach(bibliotecaDTO -> bibliotecaDTOS.add(bibliotecaDTO));
        return bibliotecaDTOS;
    }

    public  String regresarRecurso(String id){
        BibliotecaDTO bibliotecaDTO = obtenerPorId(id);
        if (bibliotecaDTO.getCantidadPrestada() > 0){
            bibliotecaDTO.setCantidadPrestada(bibliotecaDTO.getCantidadPrestada() - 1);
            actualizar(bibliotecaDTO);
            return "Se ha entregado el libro ";
        }
        return "El libro no se encuentra prestado";
    }


}
