package com.biblioteca.bibliotecaSpringBoot.services;


import com.biblioteca.bibliotecaSpringBoot.dtos.EbookDTO;
import com.biblioteca.bibliotecaSpringBoot.mappers.MapperEbookI;
import com.biblioteca.bibliotecaSpringBoot.models.Ebook;
import com.biblioteca.bibliotecaSpringBoot.repositories.RepositorieEbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceEbook implements ServiceEbookI{


    @Autowired
    private RepositorieEbook repositorieEbook;

    @Autowired
    private MapperEbookI mapperEbook;

    @Override
    public Optional<EbookDTO> consultarEbookPorID(String id) {
        return repositorieEbook.findById(id).map(ebook -> mapperEbook.toEbookDto(ebook));
    }

    @Override
    public List<EbookDTO> mostrarTodos() {

        return mapperEbook.toEbookDto(repositorieEbook.findAll());
    }

    @Override
    public EbookDTO agregarEbook(EbookDTO ebookDTO) {
        Ebook ebook = mapperEbook.toEbook(ebookDTO);
        return mapperEbook.toEbookDto(repositorieEbook.save(ebook));
    }

    @Override
    public boolean eliminarEbookPorID(String id) {

        return getById(id).map(ebookDTO -> {
            repositorieEbook.deleteById(id);
            return true;
        }).orElse(false);

    }

    @Override
    public Optional<EbookDTO> getById(String id) {
        return repositorieEbook.findById(id)
                .map(ebook -> mapperEbook.toEbookDto(ebook));
    }



    @Override
    public EbookDTO actualizarEbook(EbookDTO ebookDTO) {

        Optional<Ebook> ebook = repositorieEbook.findById(ebookDTO.getId());
        if(ebook.isPresent()){

            ebook.get().setId(ebookDTO.getId());
            ebook.get().setNombreEbook(ebookDTO.getNombreEbook() );
            ebook.get().setFechaPrestadoEbook(ebookDTO.getFechaPrestadoEbook());
            ebook.get().setCantidadDeEbookDisponible(ebookDTO.getCantidadDeEbookDisponible());
            ebook.get().setCantidadDeEbookPrestada(ebookDTO.getCantidadDeEbookPrestada());
            ebook.get().setTipoDeEbook(ebookDTO.getTipoDeEbook());
            ebook.get().setCategoriaDeLibro(ebookDTO.getCategoriaDeLibro());

            return mapperEbook.toEbookDto(repositorieEbook.save(ebook.get()));
        }
        throw new RuntimeException("El recurso indicado no existe");
    }


    @Override
    public String verificarDisponibleEbook(EbookDTO ebookDTO) {

        if(ebookDTO.getCantidadDeEbookDisponible()>ebookDTO.getCantidadDeEbookPrestada()){
            System.out.println("Hay libros disponibles" + (ebookDTO.getCantidadDeEbookDisponible() - ebookDTO.getCantidadDeEbookPrestada()));
        }
        return  "no hay libros disponibles"  ;
    }

    @Override
    public List<EbookDTO> recomendarPorCategoria(String categoria) {
        List<EbookDTO> ebookDTOS = new ArrayList<>();
        repositorieEbook.findBycategoriaDeLibro(categoria).forEach(ebook -> {
            ebookDTOS.add(mapperEbook.toEbookDto(ebook));
        });
        return ebookDTOS;

            }

    @Override
    public List<EbookDTO> recomendarPorTipo(String tipo) {
        List<EbookDTO> ebookDTOS = new ArrayList<>();
        repositorieEbook.findBytipoDeEbook(tipo).forEach(ebook -> {
            ebookDTOS.add(mapperEbook.toEbookDto(ebook));
        });
        return ebookDTOS;

    }

}