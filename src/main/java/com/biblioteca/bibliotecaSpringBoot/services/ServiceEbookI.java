package com.biblioteca.bibliotecaSpringBoot.services;

import com.biblioteca.bibliotecaSpringBoot.dtos.EbookDTO;


import java.util.List;
import java.util.Optional;

public interface ServiceEbookI {

    public Optional<EbookDTO> consultarEbookPorID(String id);
    List<EbookDTO> mostrarTodos();
    public Optional<EbookDTO> getById(String id);
    public EbookDTO agregarEbook(EbookDTO ebookDTO);
    public boolean eliminarEbookPorID(String id);
    public EbookDTO actualizarEbook(EbookDTO ebookDTO);
    public boolean verificarDisponibleEbook(EbookDTO ebookDTO);
    public List<EbookDTO> recomendarPorCategoria(String tema);
    public List<EbookDTO> recomendarPorTipo(String tipo);
    public String ebookPrestado(String id);
    public String ebookRegresado(String id);


}

