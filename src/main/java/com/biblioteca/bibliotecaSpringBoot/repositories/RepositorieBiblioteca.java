package com.biblioteca.bibliotecaSpringBoot.repositories;


import com.biblioteca.bibliotecaSpringBoot.models.Biblioteca;
import org.springframework.data.mongodb.core.MongoAdminOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorieBiblioteca extends MongoRepository<Biblioteca, String> {
    List<Biblioteca> findBytipoDeLibro(final String tipo);
    List<Biblioteca> findBytematicaDeLibro(final String tematica);
}
